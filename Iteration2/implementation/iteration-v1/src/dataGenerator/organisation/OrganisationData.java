package dataGenerator.organisation;

import dataGenerator.schedule.ScheduleData;
import dataGenerator.user.InstructorData;
import offering.Offering;
import offering.OfferingItem;
import organisation.City;
import organisation.Location;
import organisation.Organisation;
import organisation.Space;
import schedule.Schedule;
import user.Instructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class OrganisationData {



    public static Organisation generateOrganizationData(){

        Organisation organisation = new Organisation("");

        ArrayList<Instructor> instructors = InstructorData.generateInstructors();
        organisation.setInstructors(instructors);

        //generating location and space data (temporary until database is implemented)
        organisation.setLocations(generateLocations());
        ArrayList<Location> locations = organisation.getLocations();
        organisation.setOfferings(generateOfferings(instructors));

        for(var loc:locations){
            ArrayList<Space> generatedSpaces= generateSpaces();
            loc.setSpaces(generatedSpaces);
        }

        return organisation;

    }


    public static ArrayList<Space> generateSpaces() {


        ArrayList<Location> locations = generateLocations();
        ArrayList<Space> spaces = new ArrayList<>();
        spaces.add(new Space("Gym",locations.get(0)));
        spaces.add(new Space("Pool",locations.get(0)));

        return spaces;

    }



    public static ArrayList<Location> generateLocations() {
        // to do
        ArrayList<Location> locations = new ArrayList<>();
        City mtl = new City("Montreal","Quebec","Canada");
        locations.add(new Location("EV. Building", "123 Mackay St",mtl));
        locations.add(new Location("Faubourg", "123 St-Catherine",mtl));
        return locations;

    }

    public static ArrayList<City> generateCities(){
        City mtl = new City("Montreal","Quebec","Canada");
        City ottawa = new City("Ottawa","Ontario","Canada");
        City toronto = new City("Toronto","Ontario","Canada");

        return new ArrayList<City>(Arrays.asList
                (mtl,
                ottawa,
                toronto));

    }
    public static ArrayList<OfferingItem> generateOfferingItems(ArrayList<Instructor> instructors){

        ArrayList<OfferingItem> offeringItems = new ArrayList<>();

        // swim
        OfferingItem item1 = new OfferingItem( false, LocalTime.of(9,0), LocalTime.of(10,0));
        item1.addInstructor(instructors.get(0));
        instructors.get(0).addOffering(item1);
        offeringItems.add(item1);

        //yoga
        OfferingItem item2 = new OfferingItem( true, LocalTime.of(10,0), LocalTime.of(10,30));
        item2.addInstructor(instructors.get(1));
        instructors.get(1).addOffering(item2);
        offeringItems.add(item2);

        return offeringItems;
    }

    public static ArrayList<Offering> generateOfferings(ArrayList<Instructor> instructors) {

        ArrayList<Offering> offerings = new ArrayList<>();

        // offeringItems (swimming and yoga)
        ArrayList<OfferingItem> offeringItems = generateOfferingItems(instructors);

        ArrayList<Schedule> schedules = ScheduleData.generateSchedules();
        ArrayList<Space> spaces = OrganisationData.generateSpaces();


        offerings.add(new Offering("Yoga",spaces.get(0),schedules.get(0)));

        offerings.get(0).addOfferingItem(offeringItems.get(0));
        offeringItems.get(0).setOffering(offerings.get(0));

        offerings.add(new Offering("Swim",spaces.get(1),schedules.get(1)));

        offerings.get(1).addOfferingItem(offeringItems.get(1));
        offeringItems.get(1).setOffering(offerings.get(1));
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
