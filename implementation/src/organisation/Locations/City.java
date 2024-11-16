package organisation.Locations;

import java.util.Objects;

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

<<<<<<< HEAD
    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }
=======
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(name, city.name) && Objects.equals(state, city.state) && Objects.equals(country, city.country);
    }

>>>>>>> 88c77727e0dea31cab3025907f22d65526ea7632
}
