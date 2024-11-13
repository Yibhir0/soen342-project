package organisation.Locations;

import organisation.Locations.Location;

public class Space {

    private Location location;
    private String name;

    public Space(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public String toString() {
        return   location.getName()+", "+name;
    }

    public Location getLocation() {
        return location;
    }
}
