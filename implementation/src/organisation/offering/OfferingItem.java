package organisation.offering;

import organisation.Locations.Space;
import organisation.schedule.Schedule;
import organisation.user.Instructor;

import java.time.LocalDate;
import java.time.LocalTime;

public class OfferingItem {

    private int id;
    private boolean isPrivate = false ;
    private LocalTime startTime;
    private boolean isAvailable = true;
    private LocalTime endTime;
    private Instructor instructor;
    private Offering offering;

    public OfferingItem(boolean isPrivate, LocalTime startTime, LocalTime endTime) {
        this.isPrivate = isPrivate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public OfferingItem(boolean isPrivate, LocalTime startTime, LocalTime endTime,boolean isAvailable) {
        this.isPrivate = isPrivate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isAvailable=isAvailable;
    }
    public OfferingItem(boolean isPrivate, LocalTime startTime, LocalTime endTime,Offering offering1) {
        this.isPrivate = isPrivate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.offering=offering1;
    }

    public int getId() {
        return id;
    }

    public OfferingItem(int id, boolean isPrivate, LocalTime startTime, LocalTime endTime,boolean isAvailable) {
        this.id=id;
        this.isPrivate = isPrivate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isAvailable=isAvailable;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
    public Offering getOffering() {
        return offering;
    }




    public Instructor getInstructor() {
        return instructor;
    }
    public void addInstructor(Instructor inst){
        instructor=inst;
    }

    public boolean hasInstructor() {
        return instructor!=null;
    }
    public void removeInstructor(){
        this.instructor=null;
    }
    public void setOffering(Offering offer){
        this.offering=offer;
    }
public boolean book(){
        if(!isAvailable){
            return false;
        }
        else{
            isAvailable=false;
            return true;
        }
}
public Space getSpace(){
        return offering.getSpace();
}
public void removeBooking(){
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

    public boolean isPrivate() {
        return isPrivate;
    }

    //doesn't display instructor name
    public String toStringForInstructors(){
        String type= isPrivate?"Private":"Group";
        return offering.getSpace()+". " + startTime + " - " + endTime +". " + type+".";
    }
    public String detailedFormatting(){
        String type= isPrivate?"Private":"Group";
        return getSpace().toString()+". "+ startTime + " - " + endTime +". "
                +offering.getSchedule().daysToString()+". "+type+". Instructor: " + instructor.getName() ;
    }

public boolean overlaps(OfferingItem o){
        boolean identicalDayOfWeek=false;
        boolean identicalTimeSlot=false;
        boolean periodOverlap=false;

        //overlaps in a day of the week
        Schedule s1 = this.offering.getSchedule();
       Schedule s2 =o.offering.getSchedule();
        for(var d: s1.getDayOfWeek()){
            if(s2.getDayOfWeek().contains(d)){
                identicalDayOfWeek=true;
                break;
            }
        }

    //same time slot
        if(this.startTime.equals(o.startTime)||this.endTime.equals(o.endTime)){
            identicalTimeSlot=true;
        }

    //period overlap
    LocalDate start1=s1.getStartDate();
    LocalDate start2=s2.getStartDate();
    LocalDate end1=s2.getEndDate();
    LocalDate end2=s2.getEndDate();
        if(!(end1.isBefore(start2)||start1.isAfter(end2))){
            periodOverlap=true;
        }

        return identicalDayOfWeek && identicalTimeSlot && periodOverlap;
    }

    public void setId(int id) {
        this.id = id;
    }
}
