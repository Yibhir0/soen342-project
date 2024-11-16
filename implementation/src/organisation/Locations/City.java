package organisation.Locations;

public class City {

    private int id;
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

    public int getId() {
        return id;
    }
    public String toString() {
        return "Name: " + name + " State: " + state + " Country: " + country;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }
}
