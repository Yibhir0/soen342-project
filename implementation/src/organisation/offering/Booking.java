package organisation.offering;


import organisation.user.Client;

public class Booking {

    private int id;
    private OfferingItem offeringItem;


    private Client client;

    public Booking (int id , Client client, OfferingItem oi){
        this.offeringItem = oi;
        this.client = client;
        this.id = id;
    }
    public Booking (OfferingItem oi){
        this.offeringItem = oi;
    }

    public String toString(){

        return offeringItem.detailedFormatting();

    }

    public OfferingItem getOfferingItem(){
        return offeringItem;
    }

    public void setClient(Client client){
        this.client = client;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public Client getClient(){
        return client;
    }
    public boolean overlaps(Booking b){
       return this.offeringItem.overlaps(b.offeringItem);
    }
    public void removeOfferingItem(){
         offeringItem.removeBooking();
    }





}
