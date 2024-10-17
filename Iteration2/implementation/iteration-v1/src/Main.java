import dataGenerator.space.SpaceData;
import offering.OfferingItem;
import organisation.Location;
import organisation.Organisation;
import organisation.Space;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        Organisation organisation=new Organisation("");
        //generating location and space data (temporary until database is implemented)
        organisation.storeLocations(SpaceData.generateLocations());
        ArrayList<Location> locations=organisation.getLocations();
        for(var loc:locations){
            ArrayList<Space> generatedSpaces=SpaceData.generateSpaces(loc);
            loc.storeSpaces(generatedSpaces);
        }


    }






}