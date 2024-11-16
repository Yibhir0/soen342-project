package database.offering;

import database.DatabaseConnection;
import database.Instructor.InstructorDAO;
import organisation.offering.Offering;
import organisation.offering.OfferingItem;
import organisation.user.Instructor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;

public class OfferingItemDAO {

    public static void insertOfferingItem(Offering offering){

        for(var o:offering.getOfferingItemList()){

            int instructorId = 0;

            if(o.getInstructor() != null){
                instructorId = o.getInstructor().getId();
            }

            String query = "INSERT INTO OfferingItem (offering_Id, instructor_Id, isPrivate, startTime, endTime, isAvailable) VALUES ("
                    + offering.getId() + ", "
                    + instructorId + ", "
                    + o.isPrivate() + " ,'"
                    + o.getStartTime() + "', '"
                    + o.getEndTime() + "', '"
                    + o.isAvailable() + "');";
            try (Connection conn = DatabaseConnection.connect();
                 Statement stmt = conn.createStatement()) {
                stmt.execute(query);
                // Get the id of the inserted offering
                ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()");
                int id = rs.getInt(1);
                o.setId(id);
                System.out.println("offering item inserted id : "+ id);
            } catch (Exception e) {
                System.out.println(e);
                System.out.println(e.getMessage());
            }
        }

    }

    public static ArrayList<OfferingItem> getOfferingItemsByOfferingId(Offering offering) {
        ArrayList<OfferingItem> offeringItems = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM OfferingItem WHERE offering_Id = " + offering.getId();
            ResultSet rs = stmt.executeQuery(sql);
            int k = 0;
            while (rs.next()) {

                System.out.print(k);
                int id = rs.getInt("id");
                int instructorId = rs.getInt("instructor_Id");
                boolean isPrivate = rs.getBoolean("isPrivate");
                String startTimeString = rs.getString("startTime");
                String endTimeString = rs.getString("endTime");

                LocalTime startTime = LocalTime.parse(startTimeString);
                LocalTime endTime = LocalTime.parse(endTimeString);
                boolean isAvailable = rs.getBoolean("isAvailable");


                OfferingItem offeringItem = new OfferingItem(id,isPrivate, startTime, endTime, isAvailable);



                Instructor instructor = InstructorDAO.getInstructorById(instructorId);
                if(instructor != null){
                    offeringItem.addInstructor(instructor);
                }
                else{
                    System.out.println("Instructor not found");
                }

                offeringItems.add(offeringItem);
                k++;
                System.out.print("fot");

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return offeringItems;
    }

    public static OfferingItem getOfferingItemById(int offeringId) {
        OfferingItem offeringItem = null;
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM OfferingItem WHERE id = " + offeringId;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int instructorId = rs.getInt("instructor_Id");
                boolean isPrivate = rs.getBoolean("isPrivate");
                String startTimeString = rs.getString("startTime");
                String endTimeString = rs.getString("endTime");

                LocalTime startTime = LocalTime.parse(startTimeString);
                LocalTime endTime = LocalTime.parse(endTimeString);
                boolean isAvailable = rs.getBoolean("isAvailable");
                offeringItem = new OfferingItem(id,isPrivate, startTime, endTime, isAvailable);
                Instructor instructor = InstructorDAO.getInstructorById(instructorId);
                offeringItem.addInstructor(instructor);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return offeringItem;
    }
}
