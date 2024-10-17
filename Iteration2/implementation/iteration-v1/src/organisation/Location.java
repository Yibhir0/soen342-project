package organisation;

public class Location {
    private String name;
    private String address;
    private City city;

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

    public String toString() {
        return "Name: " + name + "\nAddress: " + address + "\nCity: " + city.getName();
    }
}
