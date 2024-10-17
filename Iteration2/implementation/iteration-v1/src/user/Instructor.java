package user;


import java.util.ArrayList;
import java.util.List;

import offering.OfferingItem;
import organisation.City;

public class Instructor implements User {
    private final String name;
    private String phone;
    private String speciality;
    private String password;

    private List<OfferingItem> offeringItems = new ArrayList<OfferingItem>();

    private List<City> availableCities = new ArrayList<City>();

    public Instructor(String name, String password, String phone, String speciality, List<City> availableCities) {
        this.name = name;
        this.phone = phone;
        this.speciality = speciality;
        this.password = password;
        this.availableCities = availableCities;
    }

    public void addOffering(OfferingItem offeringItem) {

        offeringItems.add(offeringItem);
    }

    public int login(String name,String password) {
        if (this.name.equals(name) && this.password.equals(password)) {
            return 1;
        } else {
            return 0;
        }
    }

    public String toString() {
        return "Name: " + name + "\nPhone: " + phone + "\nSpeciality: " + speciality;
    }

}
