package organisation;

import java.util.ArrayList;

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
    public void setLocations(ArrayList<Location> loc){
        this.locations=loc;
    }

<<<<<<< HEAD
    public String toString() {
        String result = "Name: " + name + "\nLocation: "  + "\nSpaces:\n";
=======

>>>>>>> dcb1dcfbca5f7d2f6abc6412fecb75d19b519eb4

}
