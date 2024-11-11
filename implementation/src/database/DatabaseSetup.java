package database;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseSetup {
    public static void createTables() {
        String userTable = "CREATE TABLE IF NOT EXISTS users ("
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " name TEXT NOT NULL,"
                + " password TEXT NOT NULL"
                + ");";

        try (Connection conn = DatabaseConnection .connect();
             Statement stmt = conn.createStatement()) {
             stmt.execute(userTable);
             System.out.println("Tables created successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

