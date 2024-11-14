package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseSetup {
    public static void createTables() {

        String cityTable = CityTable();
        String locationTable = LocationTable();
        String spaceTable = SpaceTable();
        String offeringTable = OfferingTable();
        String instructorTable = InstructorTable();
        String instrAvailabilityTable = InstrAvailabilityTable();
        String offeringItemTable = OfferingItemTable();
        String clientTable = ClientTable();
        String bookingTable = BookingTable();

        try (Connection conn = DatabaseConnection .connect();
             Statement stmt = conn.createStatement()) {
             stmt.execute(cityTable);
             stmt.execute(locationTable);
             stmt.execute(spaceTable);
             stmt.execute(offeringTable);
             stmt.execute(instrAvailabilityTable);
             stmt.execute(instructorTable);
             stmt.execute(offeringItemTable);
             stmt.execute(clientTable);
             stmt.execute(bookingTable);
             System.out.println("Tables created successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String CityTable(){
            return "CREATE TABLE IF NOT EXISTS City ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name CHAR NOT NULL,"
                    + "address CHAR NOT NULL,"
                    + "country CHAR NOT NULL"
                    + ");";

    }

    private static String LocationTable(){
        return "CREATE TABLE IF NOT EXISTS Location ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "city_Id INTEGER,"
                + "name CHAR NOT NULL,"
                + "address CHAR NOT NULL,"
                + "FOREIGN KEY(city_Id) REFERENCES City(id)"
                + ");";

    }

    private static String SpaceTable(){
        return "CREATE TABLE IF NOT EXISTS Space ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "location_Id INTEGER,"
                + "name CHAR NOT NULL,"
                + "FOREIGN KEY(location_Id) REFERENCES Location(id)"
                + ");";

    }

    private static String OfferingTable(){
        return "CREATE TABLE IF NOT EXISTS Offering ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "space_Id INTEGER,"
                + "lessonType CHAR NOT NULL,"
                + "startDay DATE NOT NULL,"
                + "endDay DATE NOT NULL,"
                + "dayOfWeek CHAR NOT NULL,"
                + "startTime TIME NOT NULL,"
                + "endTime TIME NOT NULL,"
                + "FOREIGN KEY(space_Id) REFERENCES Space(id)"
                + ");";
    }

    private static String InstructorTable() {
        return "CREATE TABLE IF NOT EXISTS Instructor ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "phone CHAR NOT NULL,"
                + "name CHAR NOT NULL,"
                + "password CHAR NOT NULL,"
                + "specialization CHAR NOT NULL"
                + ");";
    }

    private static String InstrAvailabilityTable() {
        return "CREATE TABLE IF NOT EXISTS Instr_availability ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "city_Id INTEGER,"
                + "instructor_Id INTEGER,"
                + "FOREIGN KEY(city_Id) REFERENCES City(id),"
                + "FOREIGN KEY(instructor_Id) REFERENCES Instructor(id)"
                + ");";
    }

    private static String OfferingItemTable() {
        return "CREATE TABLE IF NOT EXISTS OfferingItem ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "offering_Id INTEGER,"
                + "instructor_Id INTEGER,"
                + "isPrivate BOOLEAN,"
                + "startTime TIME NOT NULL,"
                + "endTime TIME NOT NULL,"
                + "isAvailable BOOLEAN,"
                + "FOREIGN KEY(offering_Id) REFERENCES Offering(id),"
                + "FOREIGN KEY(instructor_Id) REFERENCES Instructor(id)"
                + ");";
    }

    private static String ClientTable() {
        return "CREATE TABLE IF NOT EXISTS Client ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "username CHAR NOT NULL,"
                + "password CHAR NOT NULL"
                + ");";
    }

    private static String BookingTable() {
        return "CREATE TABLE IF NOT EXISTS Booking ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "offering_Id INTEGER,"
                + "client_Id INTEGER,"
                + "FOREIGN KEY(offering_Id) REFERENCES Offering(id),"
                + "FOREIGN KEY(client_Id) REFERENCES Client(id)"
                + ");";
    }

    public static void listAllTablesAndAttributes(){
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT name FROM sqlite_master WHERE type='table'";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Tables in the database:");
            while (rs.next()) {
                String tableName = rs.getString("name");
                System.out.println(tableName);
                printTableAttributes(tableName);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printTableAttributes(String tableName){

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {

            String sql = "PRAGMA table_info(" + tableName + ")";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("Attributes in the table " + tableName + ":");
            while (rs.next()) {
                String columnName = rs.getString("name");
                System.out.println(columnName);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void dropAllTables(){
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement()) {

            String sql = "SELECT name FROM sqlite_master WHERE type='table'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String tableName = rs.getString("name");
                String dropTable = "DROP TABLE IF EXISTS " + tableName;
                if(!(tableName.equals("sqlite_sequence"))){
                    stmt.execute(dropTable);
                }

            }
            System.out.println("All tables dropped successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}

