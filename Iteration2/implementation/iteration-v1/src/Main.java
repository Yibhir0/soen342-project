import dataGenerator.offering.OfferingData;
import dataGenerator.organisation.OrganisationData;
import offering.Offering;
import organisation.Location;
import organisation.Organisation;
import organisation.Space;

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








}