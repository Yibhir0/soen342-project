package organisation.offering;

import organisation.user.Client;
import organisation.user.Instructor;

import java.time.LocalTime;

public class OfferingItem {

    private boolean isPrivate=false ;
    private LocalTime startTime;

    private boolean isAvailable = true;

    private LocalTime endTime;
    private Instructor instructor;
    private Offering offering;
    private Client client;

    public OfferingItem(boolean isPrivate, LocalTime startTime, LocalTime endTime) {
        this.isPrivate = isPrivate;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public OfferingItem(boolean isPrivate, LocalTime startTime, LocalTime endTime,Offering offering1) {
        this.isPrivate = isPrivate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.offering=offering1;
    }
    public void addInstructor(Instructor inst){
        instructor=inst;
    }

    public boolean hasInstructor() {
        return instructor!=null;
    }
    public void setOffering(Offering offer){
        this.offering=offer;
    }
public boolean book(Client client){
        if(!isAvailable){
            return false;
        }
        else{
            this.client=client;
            isAvailable=false;
            return true;
        }
}
public void removeBooking(){
        this.client=null;
        this.isAvailable=true;
}
public boolean isAvailable(){
        return isAvailable;
}

    public String toString(){
        String type= isPrivate?"Private":"Group";
        String instructorName=instructor!=null?instructor.getName():"N/A";
        return startTime + " - " + endTime +". " + type + ". Instructor: " + instructorName ;
    }

    public String toStringForPublic(){
        if(isAvailable){
        return this.toString();
        }
        else{
            return startTime + " - " + endTime +". Not available.";
        }
    }

    //doesn't display instructor name
    public String toStringForInstructors(){
        String type= isPrivate?"Private":"Group";
        return offering.getSpace()+". " + startTime + " - " + endTime +". " + type+".";
    }
    //shows client name
    public String toStringForAdmin(){
        String booking="";
        if(client!=null){
            booking=". booked by:"+client.getUsername();
        }
        return this.toString()+booking;
    }


}
