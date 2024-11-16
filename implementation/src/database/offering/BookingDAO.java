package database.offering;

import database.DatabaseConnection;
import database.client.ClientDAO;
import organisation.offering.Booking;
import organisation.offering.OfferingItem;
import organisation.user.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingDAO {

    public static void insertBooking(Booking booking) {
        String sql = "INSERT INTO Booking (offering_Id, client_Id) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, booking.getOfferingItem().getId());
            pstmt.setInt(2, booking.getClient().getId());
            pstmt.executeUpdate();

            // get the sql generated key
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                booking.setId(id);
                System.out.println("Booking ID: " + id);
            }

            System.out.println("Booking inserted successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static ArrayList<Booking> getBookingsByClientId(int id) {
        ArrayList<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM Booking WHERE client_Id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int bookingId = rs.getInt("id");
                int offeringId = rs.getInt("offering_Id");
                int clientId = rs.getInt("client_Id");

                Client c = ClientDAO.getClientById(clientId);

                OfferingItem offeringItem = OfferingItemDAO.getOfferingItemById(offeringId);

                Booking booking = new Booking(bookingId, c, offeringItem);
                bookings.add(booking);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return bookings;
    }
}
