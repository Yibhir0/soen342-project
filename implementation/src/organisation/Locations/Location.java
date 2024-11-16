package organisation.Locations;

import java.util.ArrayList;

public class Location {

    private int id;
    private String name;
    private String address;
    private City city;
    private ArrayList<Space> spaces;

    public Location(String name, String address, City city) {
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public City getCity() {
        return city;
    }
    public ArrayList<Space> getSpaces() {
        return spaces;
    }
    public void addSpace(Space space){
        spaces.add(space);
    }
    //temporary mean to get locations without database
    public void setSpaces(ArrayList<Space> s){
        this.spaces=s;
    }


    public String toString() {
        return "Id:"+ id+ " Name: " + name + "\nAddress: " + address + "\nCity: " + city.getName();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
