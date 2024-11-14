package organisation.Locations;

public class City {
    private String name;
    private String state;
    private String country;

    public City(String name, String state, String country) {
        this.name = name;
        this.state = state;
        this.country = country;
    }

    public String getName() {
        return name;
    }


    public String toString() {
        return "Name: " + name + " State: " + state + " Country: " + country;
    }
}
