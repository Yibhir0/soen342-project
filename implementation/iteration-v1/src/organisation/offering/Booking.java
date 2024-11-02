package organisation.offering;


public class Booking {

    private OfferingItem offeringItem;
    public Booking (OfferingItem oi){
        this.offeringItem = oi;
    }

    public String toString(){

        return offeringItem.getSpace().toString()+". "+offeringItem.toString();

    }
    public void removeOfferingItem(){
         offeringItem.removeBooking();
    }





}
