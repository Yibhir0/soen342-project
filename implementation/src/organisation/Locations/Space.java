package organisation.Locations;

import organisation.Locations.Location;

public class Space {

    private int id;
    private Location location;
    private String name;

    public Space(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return   location.getName()+", "+name;
    }

    public Location getLocation() {
        return location;
    }
}
