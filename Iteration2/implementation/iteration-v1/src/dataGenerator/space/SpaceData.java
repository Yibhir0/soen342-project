package dataGenerator.space;

import organisation.City;
import organisation.Location;
import organisation.Space;

import java.util.ArrayList;
import java.util.List;

public class SpaceData {



    public static ArrayList<Space> generateSpaces(Location location) {

        // to do
        ArrayList<Space> spaces = new ArrayList<>();
        spaces.add(new Space("Gym",location));
        spaces.add(new Space("Pool",location));

        return spaces;

    }

    public static ArrayList<Location> generateLocations() {

        // to do
        ArrayList<Location> locations = new ArrayList<>();
        City mtl=new City("Montreal","Quebec","Canada");
        locations.add(new Location("EV. Building", "123 Mackay St",mtl));
        locations.add(new Location("Faubourg", "123 St-Catherine",mtl));
        return locations;

    }
}
