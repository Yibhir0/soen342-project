package organisation.user;

import organisation.offering.Booking;

import java.util.ArrayList;

public class Client implements User{

    private int id;
    private final String username;
    private final String password ;
    private ArrayList<Booking> bookings = new ArrayList<Booking>();
    private ArrayList<UnderageClient> children =new ArrayList<UnderageClient>();

    public Client (String username,String password){
        this.username=username;
        this.password=password;
    }

    public Client(int id, String username, String password){
        this.username=username;
        this.password=password;
        this.id=id;

    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

    public int login(String name,String password) {
        if (this.username.equals(name) && this.password.equals(password)) {
            return 1;
        } else {
            return 0;
        }
    }

    public boolean bookOffering(Booking newBooking) {
        for(Booking b: bookings){
            if(b.overlaps(newBooking)){
                return false;
            }
        }
       return bookings.add(newBooking);
    }

    public String getPassword(){
        return password;
    }

    public ArrayList<Booking> getBookings(){
        return bookings;
    }
    public boolean removeBooking(Booking booking) {
        return bookings.remove(booking);
    }
    public void printBookedOfferings(){

        System.out.println("Client: "+username);
        if(bookings.isEmpty()){
            System.out.println("No bookings");
        }
        int i=0;
        for (Booking booking: bookings){
            System.out.println(i++ + ":" + booking);
        }


    }
    public ArrayList<Booking> getBooking(){
        return bookings;
    }
    public String getUsername(){
        return username;
    }

    public void addChild( UnderageClient child){
            children.add(child);
    }
    public ArrayList<UnderageClient> getChildren(){
        return children;
    }

    public void setChildren(ArrayList<UnderageClient> children){
        this.children=children;
    }


    public void setBookings(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }
}
