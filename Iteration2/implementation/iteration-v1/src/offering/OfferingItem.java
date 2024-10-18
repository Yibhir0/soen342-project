package offering;

import organisation.Space;
import user.Instructor;

import java.time.LocalTime;

public class OfferingItem {

    private boolean isPrivate=false ;
    private LocalTime startTime;

    private boolean isAvailableToPublic = false;

    private LocalTime endTime;
    private Instructor instructor;
    private Offering offering;

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
    public void setAvailableToPublic() {
        isAvailableToPublic = !this.isAvailableToPublic ;
    }

    public String toString(){
        String type= isPrivate?"Private":"Group";
        String instructorName=instructor!=null?instructor.getName():"N/A";
        return startTime + " - " + endTime +". " + type + ". Instructor: " + instructorName ;
    }
    public String toStringForInstructors(){
        String type= isPrivate?"Private":"Group";
        return offering.getSpace() + " - " + endTime +". " + type;
    }

}
