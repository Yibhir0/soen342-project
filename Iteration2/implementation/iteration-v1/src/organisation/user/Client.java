package organisation.user;

import organisation.offering.OfferingItem;

import java.util.ArrayList;

public class Client implements User{
    private final String username;
    private final String password ;
    private ArrayList<OfferingItem> bookings = new ArrayList<OfferingItem>();

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
    public void bookOffering(OfferingItem offeringItem) {
        bookings.add(offeringItem);
    }
    public ArrayList<OfferingItem> getBookings(){
        return bookings;
    }
    public boolean removeBooking(OfferingItem offeringItem) {

        return bookings.remove(offeringItem);
    }
    public void printBookedOfferings(){
    for (var booking: bookings){
        System.out.println(booking);
    }
    }
    public String getUsername(){
        return username;
    }


}
