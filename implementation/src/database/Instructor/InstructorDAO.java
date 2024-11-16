package database.Instructor;

import database.DatabaseConnection;
import database.offering.OfferingDAO;
import organisation.Locations.City;
import organisation.offering.Offering;
import organisation.offering.OfferingItem;
import organisation.user.Instructor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class InstructorDAO {

    public static void insertInstructors(ArrayList<Instructor> instructors){
        // insert instructors into database

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            for (Instructor instructor : instructors) {

                String sql = "INSERT INTO Instructor (name, phone, password, specialization) VALUES ('" + instructor.getName() + "', '" + instructor.getPhone() + "', '" + instructor.getPassword() + "', '" + instructor.getSpeciality() + "')";

                int affectedRows = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

                if (affectedRows > 0) {
                    System.out.println("Insert successful Instructor");
                    try (ResultSet rs = stmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            int id = rs.getInt(1); // Get the auto-generated ID
                            instructor.setId(id);
                            System.out.println("Instructor ID: " + id);
                        }
                    }
                } else {
                    System.out.println("Insert failed, no rows affected.");
                }



                // get the id of the instructo
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void insertCities(List<City> cities){
        // insert cities into database

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            System.out.println("Cities: " + cities.size());
            for (City city : cities) {
                String sql = "INSERT INTO City (name, state, country) VALUES ('" + city.getName() + "', '" + city.getState() + "', '" + city.getCountry() + "')";

                int affectedRows = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

                if (affectedRows > 0) {
                    System.out.println("Insert successful City");
                    try (ResultSet rs = stmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            int id = rs.getInt(1); // Get the auto-generated ID
                            city.setId(id);
                            System.out.println("City ID: " + id);
                        }
                    }
                } else {
                    System.out.println("Insert failed, no rows affected.");
                }

                // get the id of the instructo
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



    }

    public static void insertInstructorAvailableCity(List<Instructor> instructors){
        // insert cities into database

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            for (Instructor instructor : instructors) {


                for (City city : instructor.geAvailableCities()) {
                    String sql = "INSERT INTO Instr_availability (instructor_Id, city_Id) VALUES ('" + instructor.getId() + "', '" + city.getId() + "')";

                    int affectedRows = stmt.executeUpdate(sql);

                    if (affectedRows > 0) {
                        System.out.println("Insert successful Instructor availability");
                    }
                    else{
                        System.out.println("Insert failed, no rows affected.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static ArrayList<City> getInstructorCities(int instructorID ){
        ArrayList<City> cities = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT DISTINCT * FROM City INNER JOIN Instr_availability ON City.id = Instr_availability.city_Id WHERE Instr_availability.instructor_Id = " + instructorID;

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String state = rs.getString("state");
                String country = rs.getString("country");
                City city = new City(name, state, country);
                city.setId(id);
                cities.add(city);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cities;
    }

    public static ArrayList<OfferingItem> getInstructorOfferingItems(Instructor instructor){

        ArrayList<OfferingItem> offeringItems = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM OfferingItem WHERE instructor_Id = " + instructor.getId();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                int offeringId = rs.getInt("offering_Id");

                boolean isPrivate = rs.getBoolean("isPrivate");

                String startTimeString = rs.getString("startTime");
                String endTimeString = rs.getString("endTime");


                LocalTime startTime = LocalTime.parse(startTimeString);
                LocalTime endTime = LocalTime.parse(endTimeString);
                boolean isAvailable = rs.getBoolean("isAvailable");
                OfferingItem offeringItem = new OfferingItem(isPrivate, startTime, endTime, isAvailable);

                offeringItem.setId(id);

                Offering offering = OfferingDAO.getOfferingById(offeringId);


                offeringItem.setOffering(offering);

                offering.addOfferingItem(offeringItem);

                offeringItems.add(offeringItem);

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return offeringItems;
    }

    public static ArrayList<Instructor> getInstructors(){
        ArrayList<Instructor> instructors = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM Instructor";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String password = rs.getString("password");
                String specialization = rs.getString("specialization");

                ArrayList<City> cities = getInstructorCities(id);

                Instructor instructor = new Instructor(name, password,phone, specialization,cities);
                instructor.setId(id);

                ArrayList<OfferingItem> offeringItems = getInstructorOfferingItems(instructor);



                instructor.setOfferingItems(null);
                instructors.add(instructor);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return instructors;
    }

    public static Instructor getInstructorById(int instructorId){


        Instructor instructor = null;
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM Instructor WHERE id = " + instructorId;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String password = rs.getString("password");
                String specialization = rs.getString("specialization");

                ArrayList<City> cities = getInstructorCities(id);
                instructor = new Instructor(name, password,phone, specialization,cities);
                instructor.setId(id);
                ArrayList<OfferingItem> offeringItems = getInstructorOfferingItems(instructor);
                instructor.setOfferingItems(offeringItems);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return instructor;
    }

    public static void insertInstructor(Instructor instructor) {

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            String sql = "INSERT INTO Instructor (name, phone, password, specialization) VALUES ('" + instructor.getName() + "', '" + instructor.getPhone() + "', '" + instructor.getPassword() + "', '" + instructor.getSpeciality() + "')";

            int affectedRows = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

            if (affectedRows > 0) {
                System.out.println("Insert successful Instructor");
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id = rs.getInt(1); // Get the auto-generated ID
                        instructor.setId(id);
                        System.out.println("Instructor ID: " + id);
                        // insert cities into instructor_availability
                        for (City city : instructor.geAvailableCities()) {
                            String sql2 = "INSERT INTO Instr_availability (instructor_Id, city_Id) VALUES ('" + instructor.getId() + "', '" + city.getId() + "')";

                            int affectedRows2 = stmt.executeUpdate(sql2);

                            if (affectedRows2 > 0) {
                                System.out.println("Insert successful Instructor availability");
                            }
                            else{
                                System.out.println("Insert failed, no rows affected.");
                            }
                        }
                    }
                }
            } else {
                System.out.println("Insert failed, no rows affected.");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
