package database.client;

import database.DatabaseConnection;

import organisation.user.Client;
import organisation.user.UnderageClient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientDAO {

    public static void insertClient(Client client) {
        String sql = "INSERT INTO Client(username, password) VALUES(?,?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, client.getUsername());
            pstmt.setString(2, client.getPassword());
            pstmt.executeUpdate();

            // get the sql generated key
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                client.setId(id);
                System.out.println("Client ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertUnderageClient(UnderageClient client, Client parent) {
        String sql = "INSERT INTO UnderageClient(username, password, parent_id) VALUES(?,?,?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, client.getUsername());
            pstmt.setString(2, client.getPassword());
            pstmt.setInt(3, parent.getId());
            pstmt.executeUpdate();

            // get the sql generated key
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                client.setId(id);
                System.out.println("Underage Client ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Client getClientById(int id) {
        String sql = "SELECT id, username, password FROM Client WHERE id = ?";
        Client client = null;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                client = new Client(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return client;
    }

    public static ArrayList<Client> getAllClients() {

        String sql = "SELECT id, username, password FROM Client";
        ArrayList<Client> clients = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Client client = new Client(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"));


                clients.add(client);
                ArrayList<UnderageClient> children = getAllUnderageClients(client);
                client.setChildren(children);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return clients;
    }

    private static ArrayList<UnderageClient> getAllUnderageClients(Client c) {

        String sql = "SELECT id, username, password FROM UnderageClient WHERE parent_id = " + c.getId();
        ArrayList<UnderageClient> clients = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                UnderageClient client = new UnderageClient(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),c);

                clients.add(client);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return clients;
    }


}