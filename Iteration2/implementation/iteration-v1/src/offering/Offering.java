package offering;

import organisation.Space;
import schedule.Schedule;

import java.time.Duration;
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
        return "We offer " + lessonType +" classes in " +space+ " on "+ schedule + "as follows: \n" + offeringItems;
    }

    public Collection<? extends OfferingItem> getOfferingItemList() {
        return offeringItemList;
    }
}
