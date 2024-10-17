package organisation;

import java.util.ArrayList;

public class Location {
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
    public void storeSpaces(ArrayList<Space> s){
        this.spaces=s;
    }


    public String toString() {
        return "Name: " + name + "\nAddress: " + address + "\nCity: " + city.getName();
    }
}
