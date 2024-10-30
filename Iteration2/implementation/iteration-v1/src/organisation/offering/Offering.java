package organisation.offering;

import organisation.Locations.Space;
import organisation.schedule.Schedule;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Offering {
    private String lessonType;

    private List<OfferingItem> offeringItemList;

    private Space space;

    private Schedule schedule;

    public Offering(String lessonType,
                    Space space, Schedule schedule) {
        this.lessonType = lessonType;
        this.offeringItemList = new ArrayList<OfferingItem>();
        this.space = space;
        this.schedule = schedule;
    }

    public void addOfferingItem(OfferingItem offeringItem) {
        offeringItemList.add(offeringItem);
    }

    public String getLessonType() {
        return lessonType;
    }


    public String toString() {
        String offeringItems="";
        for(var o:offeringItemList){
            offeringItems+="\t"+o.toString()+"\n";
        }
        return "We offer " + lessonType +" classes in " +space+ " on "+ schedule + " as follows: \n" + offeringItems;
    }

    public Collection<? extends OfferingItem> getOfferingItemList() {
        return offeringItemList;
    }

    public void printOfferingsForPublic() {
        System.out.println( "We offer " + lessonType +" classes in " +space+ " on "+ schedule + " as follows:");
        for(var o:offeringItemList){
            if (o.hasInstructor()) {
                System.out.println("\t" + o.toStringForPublic() );
            }
        }
    }
    public void printOfferingsForAdmin() {
        System.out.println( "We offer " + lessonType +" classes in " +space+ " on "+ schedule + " as follows:");
        for(var o:offeringItemList) {

            System.out.println("\t" + o.toStringForAdmin());
        }
    }


    public Space getSpace() {
        return space;
    }
    public boolean validateTime(LocalTime t){
        return t.isAfter(schedule.getStartTime())&&t.isBefore(schedule.getEndTime());
    }

    public Schedule getSchedule() {
        return schedule;
    }
}
