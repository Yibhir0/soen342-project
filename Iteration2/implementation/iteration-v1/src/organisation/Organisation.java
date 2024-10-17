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
    //temporary mean to get locations without database
    public void storeLocations(ArrayList<Location> loc){
        this.locations=loc;
    }



}
