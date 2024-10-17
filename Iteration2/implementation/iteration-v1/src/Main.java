
import dataGenerator.offering.OfferingData;

import offering.Offering;


import offering.Offering;
import offering.OfferingItem;
import organisation.Location;
import organisation.Organisation;


import java.util.ArrayList;

public class Main {

    ArrayList<Offering> offerings = OfferingData.generateOfferings();

    public static void main(String[] args) {

        for(var offering:OfferingData.generateOfferings()){
            System.out.println(offering);
        }
//        Organisation organisation=new Organisation("");
//        //generating location and space data (temporary until database is implemented)
//        organisation.setLocations(OrganisationData.generateLocations());
//        ArrayList<Location> locations=organisation.getLocations();
//        for(var loc:locations){
//            ArrayList<Space> generatedSpaces= OrganisationData.generateSpaces(loc);
//            loc.storeSpaces(generatedSpaces);
//        }


    }




public void createOffering(Organisation organisation){
    //choose location
    System.out.println("choose a location:");
    for (Location location : organisation.getLocations()) {
        System.out.println("1. "+location);
    }

    //choose space from chosen location
    //create schedule
    //enter lesson type
    //create Offering(String lessonType,Space space, Schedule schedule)
    //createOfferingItems(Offering offering)
}
public void createOfferingItems(Offering offering){
    String input="y";
    while(!input.equals("n")||!input.equals("N")||!input.equals("no")||!input.equals("NO")) {

        System.out.print("Add offering item? (y/n):");
        System.out.print("Enter start time:");//check if start time is between offering.Schedule.startTime and  offering.Schedule.endTime
        System.out.print("Enter end time:");//same for end time
        System.out.print("Is this offering public? (y/n):");
        // create OfferingItem(boolean isPrivate, LocalTime startTime, LocalTime endTime)
        //offering.add(offeringItem)

    }

}




}