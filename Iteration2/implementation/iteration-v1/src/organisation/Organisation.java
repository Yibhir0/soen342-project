package organisation;

import java.util.ArrayList;
import java.util.List;

public class Organisation {
    private String name;
    private ArrayList<Location> locations;

    public Organisation(String name) {
        this.name = name;
        this.locations=new ArrayList<Location>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }
    public void addLocation(Location location) {
       locations.add(location);
    }


    public String toString() {
        String result = "Name: " + name + "\nLocation: "  + "\nSpaces:\n";

        return result;
    }
}
