package database.offering;

import database.DatabaseConnection;
import database.location.LocationDAO;
import organisation.Locations.Space;
import organisation.offering.Offering;
import organisation.offering.OfferingItem;
import organisation.schedule.DayOfWeek;
import organisation.schedule.Schedule;

import java.sql.Date;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class OfferingDAO {

    public static void insertOffering(Offering offering, int spaceId) {
     String query = "INSERT INTO Offering (space_Id, lessonType, startDay, endDay, dayOfWeek, startTime, endTime) VALUES ("
             + spaceId + ", '"
             + offering.getLessonType() + "' ,'"
                + Date.valueOf(offering.getSchedule().getStartDate()) + "', '"
                +  Date.valueOf(offering.getSchedule().getEndDate()) + "', '"
                + offering.getSchedule().getDaysOfWeek() + "', '"
                + offering.getSchedule().getStartTime() + "', '"
                + offering.getSchedule().getEndTime() + "');";

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
             stmt.execute(query);
             // Get the id of the inserted offering
                ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()");
                int id = rs.getInt(1);
                offering.setId(id);
                System.out.println("offering inserted id : "+ id);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(e.getMessage());
        }
    }

    public static Offering getOfferingById(int offeringId){
        Offering offering = null;
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM Offering WHERE id = " + offeringId;
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("id");
                int spaceId = rs.getInt("space_Id");
                String lessonType = rs.getString("lessonType");
                String sd = rs.getString("startDay");
                String ed = rs.getString("endDay");
                LocalDate startDay = LocalDate.parse(sd);
                LocalDate endDay = LocalDate.parse(ed);

                String weekDays = rs.getString("dayOfWeek");
                String [] daysOfWeek = weekDays.split(",");

                ArrayList<DayOfWeek> dayOfWeek = new ArrayList<>();

                for (int i=0; i<daysOfWeek.length-1; i++){
                    dayOfWeek.add(mapDayOfWeek(daysOfWeek[i].trim()));
                }

                String startTimeString = rs.getString("startTime");
                String endTimeString = rs.getString("endTime");

                LocalTime startTime = LocalTime.parse(startTimeString);
                LocalTime endTime = LocalTime.parse(endTimeString);

                Schedule schedule = new Schedule(startDay, endDay, dayOfWeek, startTime, endTime);

                Space space = LocationDAO.getSpaceByById(spaceId);


                offering = new Offering(id, lessonType, space, schedule);

                ArrayList<OfferingItem> offeringItems = OfferingItemDAO.getOfferingItemsByOfferingId(offering);
//
                for (OfferingItem offeringItem : offeringItems) {
                    offering.addOfferingItem(offeringItem);
                }

            }
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return offering;
    }

    public static ArrayList<Offering> getAllOfferings(){
        ArrayList<Offering> offerings = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM Offering";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int spaceId = rs.getInt("space_Id");
                String lessonType = rs.getString("lessonType");
                String sd = rs.getString("startDay");
                String ed = rs.getString("endDay");
                LocalDate startDay = LocalDate.parse(sd);
                LocalDate endDay = LocalDate.parse(ed);
                String weekDays = rs.getString("dayOfWeek");
                String [] daysOfWeek = weekDays.split(",");

                ArrayList<DayOfWeek> dayOfWeek = new ArrayList<>();
                for (int i=0; i<daysOfWeek.length-1; i++){
                    dayOfWeek.add(mapDayOfWeek(daysOfWeek[i].trim()));
                }

                String startTimeString = rs.getString("startTime");
                String endTimeString = rs.getString("endTime");

                LocalTime startTime = LocalTime.parse(startTimeString);
                LocalTime endTime = LocalTime.parse(endTimeString);


                Schedule schedule = new Schedule(startDay, endDay, dayOfWeek, startTime, endTime);

                Space space = LocationDAO.getSpaceByById(spaceId);
                Offering offering = new Offering(id, lessonType, space, schedule);

                ArrayList<OfferingItem> offeringItems = OfferingItemDAO.getOfferingItemsByOfferingId(offering);

                for (OfferingItem offeringItem : offeringItems) {
                    offering.addOfferingItem(offeringItem);
                }
                offerings.add(offering);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return offerings;
    }

    private static DayOfWeek mapDayOfWeek(String day) {

        System.out.println(day);
        switch (day.toUpperCase()) {
            case "MONDAY": return DayOfWeek.MONDAY;
            case "TUESDAY": return DayOfWeek.TUESDAY;
            case "WEDNESDAY": return DayOfWeek.WEDNESDAY;
            case "THURSDAY": return DayOfWeek.THURSDAY;
            case "FRIDAY": return DayOfWeek.FRIDAY;
            case "SATURDAY": return DayOfWeek.SATURDAY;
            case "SUNDAY": return DayOfWeek.SUNDAY;
            default: throw new IllegalArgumentException("Invalid day: " + day);
        }
    }

}
