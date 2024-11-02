package dataGenerator.organisation;

import dataGenerator.schedule.ScheduleData;
import dataGenerator.user.ClientData;
import dataGenerator.user.InstructorData;
import organisation.offering.Booking;
import organisation.offering.Offering;
import organisation.offering.OfferingItem;
import organisation.Locations.City;
import organisation.Locations.Location;
import organisation.Organisation;
import organisation.Locations.Space;
import organisation.schedule.Schedule;
import organisation.user.Client;
import organisation.user.Instructor;

import java.awt.print.Book;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrganisationData {



    public static Organisation generateOrganizationData(){

        Organisation organisation = new Organisation("");

        ArrayList<Instructor> instructors = InstructorData.generateInstructors();
        organisation.setInstructors(instructors);


        ArrayList<Client> clients = ClientData.generateClients();
        organisation.setClients(clients);

        //generating location and space data (temporary until database is implemented)
        organisation.setLocations(generateLocations());
        ArrayList<Location> locations = organisation.getLocations();
        organisation.setOfferings(generateOfferings(instructors));


        // Assign offering item to client

        OfferingItem oItem = organisation.getOfferings().get(0).getOfferingItemList().get(0);

        boolean isBooked = oItem.book();

        if(isBooked){
            Booking booking = new Booking(oItem);

            clients.get(0).bookOffering(booking);
        }
        else{
            System.out.println("Offering is not available");
        }

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
        //no instructor
        OfferingItem item3 = new OfferingItem( true, LocalTime.of(11,0), LocalTime.of(11,30));
        offeringItems.add(item3);

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

        offerings.get(1).addOfferingItem(offeringItems.get(2));
        offeringItems.get(2).setOffering(offerings.get(1));

        return offerings;
    }

//    public Offering(String lessonType,
//                    Space space, Schedule organisation.schedule) {
//        this.lessonType = lessonType;
//        this.offeringItemList = new ArrayList<OfferingItem>();
//        this.space = space;
//        this.organisation.schedule = organisation.schedule;
//    }






}