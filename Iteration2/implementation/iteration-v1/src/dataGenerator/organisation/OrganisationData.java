package dataGenerator.organisation;

import dataGenerator.user.InstructorData;
import organisation.City;
import organisation.Location;
import organisation.Organisation;
import organisation.Space;
import user.Instructor;

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


        for(var loc:locations){
            ArrayList<Space> generatedSpaces= generateSpaces();
            loc.storeSpaces(generatedSpaces);
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





}
