package dataGenerator.offering;

import dataGenerator.organisation.OrganisationData;
import dataGenerator.schedule.ScheduleData;
import dataGenerator.user.InstructorData;
import offering.Offering;
import offering.OfferingItem;
import organisation.Space;
import schedule.Schedule;
import user.Instructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class OfferingData {

    public static ArrayList<OfferingItem> generateOfferingItems(){

            ArrayList<OfferingItem> offeringItems = new ArrayList<>();

            ArrayList<Instructor> instructors = InstructorData.generateInstructors();

            // swim
            OfferingItem item1 = new OfferingItem( false, LocalTime.of(9,0), LocalTime.of(10,0));
            item1.addInstructor(instructors.get(0));
            offeringItems.add(item1);

            //yoga
            OfferingItem item2 = new OfferingItem( true, LocalTime.of(10,0), LocalTime.of(10,30));
            item1.addInstructor(instructors.get(1));
            offeringItems.add(item2);
            return offeringItems;
    }

    public static ArrayList<Offering> generateOfferings() {
        ArrayList<Offering> offerings = new ArrayList<>();
        ArrayList<OfferingItem> offeringItems = generateOfferingItems();

        ArrayList<Schedule> schedules = ScheduleData.generateSchedules();



        ArrayList<Space> spaces = OrganisationData.generateSpaces();
        offerings.add(new Offering("Yoga",spaces.get(0),schedules.get(0)));

        offerings.get(0).addOfferingItem(offeringItems.get(0));

        offerings.add(new Offering("Swim",spaces.get(1),schedules.get(1)));

        offerings.get(1).addOfferingItem(offeringItems.get(1));
        return offerings;
    }

//    public Offering(String lessonType,
//                    Space space, Schedule schedule) {
//        this.lessonType = lessonType;
//        this.offeringItemList = new ArrayList<OfferingItem>();
//        this.space = space;
//        this.schedule = schedule;
//    }


}
