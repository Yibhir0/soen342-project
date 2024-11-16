package database.organisation;

import database.Instructor.InstructorDAO;
import database.client.ClientDAO;
import database.location.LocationDAO;
import database.offering.BookingDAO;
import database.offering.OfferingDAO;
import database.offering.OfferingItemDAO;
import organisation.Locations.Location;
import organisation.Locations.Space;
import organisation.Organisation;
import organisation.offering.Booking;
import organisation.offering.OfferingItem;
import organisation.user.Client;
import organisation.user.Instructor;


import java.util.ArrayList;

public class OrganisationDAO {

    public static Organisation getOrganisationFromDB(){

        Organisation organisation = new Organisation("Test Organisation");

        // Fetch instructors from the database
        ArrayList<Instructor> instructors = InstructorDAO.getInstructors();
        organisation.setInstructors(instructors);

//        // Fetch clients from the database
        ArrayList<Client> clients = ClientDAO.getAllClients();
        organisation.setClients(clients);
//
        for(var client:clients){
            if(client instanceof Client){
                ArrayList<Booking> bookings = BookingDAO.getBookingsByClientId(client.getId());
                client.setBookings(bookings);
            }
        }

//        // Fetch locations from the database
        ArrayList<Location> locations = LocationDAO.getAllLocations();


        organisation.setLocations(locations);

        // Fetch offerings from the database
        organisation.setOfferings(OfferingDAO.getAllOfferings());

        OfferingItem oItem = organisation.getOfferings().get(0).getOfferingItemList().get(0);
        boolean isBooked = oItem.book();

        if(isBooked){
            Booking booking = new Booking(oItem);
            booking.setClient(clients.get(0));
            clients.get(0).bookOffering(booking);
            // add the booking to the database
            BookingDAO.insertBooking(booking);
        }
        else{
            System.out.println("Offering is not available");
        }

        for(var loc:locations){

            ArrayList<Space> generatedSpaces= LocationDAO.getSpacesByLocationId(loc.getId());

            loc.setSpaces(generatedSpaces);
            // SQl
        }

        return organisation;
    }
}
