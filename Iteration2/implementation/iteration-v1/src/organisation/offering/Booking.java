package organisation.offering;


public class Booking {

    private OfferingItem offeringItem;
    public Booking (OfferingItem oi){
        this.offeringItem = oi;
    }

    public String toString(){

        return offeringItem.toString();

    }





}
