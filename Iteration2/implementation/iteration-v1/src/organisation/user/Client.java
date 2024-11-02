package organisation.user;

import organisation.offering.Booking;
import organisation.offering.OfferingItem;

import java.awt.print.Book;
import java.util.ArrayList;

public class Client implements User{
    private final String username;
    private final String password ;
    private ArrayList<Booking> bookings = new ArrayList<Booking>();

    public Client (String username,String password){
        this.username=username;
        this.password=password;
    }

    public int login(String name,String password) {
        if (this.username.equals(name) && this.password.equals(password)) {
            return 1;
        } else {
            return 0;
        }
    }
    public void bookOffering(Booking booking) {
        bookings.add(booking);
    }




    public ArrayList<Booking> getBookings(){
        return bookings;
    }
    public boolean removeBooking(Booking booking) {

        return bookings.remove(booking);
    }
    public void printBookedOfferings(){

        System.out.println("Client: "+username);
        for (Booking booking: bookings){
        System.out.println(booking);
    }


    }
    public String getUsername(){
        return username;
    }


}
