package database.location;

import database.DatabaseConnection;
import organisation.Locations.City;
import organisation.Locations.Location;
import organisation.Locations.Space;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LocationDAO {

    public static void insertLocation(Location location) {

        String query = "INSERT INTO Location (city_Id, name, address) VALUES ("
                + location.getCity().getId() + ", '"
                + location.getName() + "', '"
                + location.getAddress() + "');";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
             stmt.execute(query);
             // Get the id of the inserted location
                ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()");
                int id = rs.getInt(1);
                location.setId(id);
                System.out.println("location inserted id : "+ id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertSpace(Space space) {

        String query = "INSERT INTO Space (location_Id, name) VALUES ("
                + space.getLocation().getId() + ", '"
                + space.getName() + "');";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(query);
            // Get the id of the inserted space
            ResultSet rs = stmt.executeQuery("SELECT last_insert_rowid()");
            int id = rs.getInt(1);
            space.setId(id);

            System.out.println("space inserted id : "+ id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Space getSpaceByById(int space_id){


        Space space = null;
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM Space WHERE id = " + space_id;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int locationId = rs.getInt("location_Id");
                String name = rs.getString("name");


                Location location = getLocationById(locationId);


                space = new Space(name, location);
                space.setId(id);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return space;
    }

    public static Location getLocationById(int locationId) {

        Location location = null;

        System.out.println("locationId : "+ locationId);
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM Location WHERE id = " + locationId;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("here i am");
                int id = rs.getInt("id");
                int cityId = rs.getInt("city_Id");
                City city = getCityById(cityId);
                String name = rs.getString("name");
                String address = rs.getString("address");
                location = new Location( name, address,city);
                location.setId(id);


            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return location;
    }

    public static City getCityById(int cityId) {
        City city = null;
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM City WHERE id = " + cityId;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String state = rs.getString("state");
                String country = rs.getString("country");
                city = new City(name, state, country);
                city.setId(id);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return city;
    }

    public static ArrayList<Location> getAllLocations() {
        ArrayList<Location> locations = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM Location";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int cityId = rs.getInt("city_Id");
                City city = getCityById(cityId);
                String name = rs.getString("name");
                String address = rs.getString("address");
                Location location = new Location(name, address, city);
                location.setId(id);
                locations.add(location);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return locations;
    }

    public static ArrayList<Space> getSpacesByLocationId(int id) {
        ArrayList<Space> spaces = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {
            String sql = "SELECT * FROM Space WHERE location_Id = " + id;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int spaceId = rs.getInt("id");
                String name = rs.getString("name");
                Space space = new Space(name, getLocationById(id));
                space.setId(spaceId);
                spaces.add(space);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return spaces;
    }
}