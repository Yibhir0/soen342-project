package offering;

import organisation.Space;
import schedule.Schedule;

import java.time.Duration;
import java.util.List;

public class Offering {
    private String lessonType;

    private Duration duration;

    private List<OfferingItem> offeringItemList;

    private Space space;

    private Schedule  schedule;

    public Offering(String lessonType, Duration duration,
                    List<OfferingItem> offeringItemList,
                    Space space, Schedule schedule) {
        this.lessonType = lessonType;
        this.duration = duration;
        this.offeringItemList = offeringItemList;
        this.space = space;
        this.schedule = schedule;
    }

    public void addOfferingItem(OfferingItem offeringItem) {
        offeringItemList.add(offeringItem);
    }
    public String toString() {
        return "Lesson Type: " + lessonType + "\nDuration: " + duration + "\nSchedule: "+ schedule + "\nOffering Items: " + offeringItemList;
    }
}
