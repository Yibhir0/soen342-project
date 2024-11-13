package organisation.user;


import java.util.ArrayList;
import java.util.List;

import organisation.offering.OfferingItem;
import organisation.Locations.City;

public class Instructor implements User {
    private final String name;
    private String phone;
    private String speciality;
    private String password;

    private ArrayList<OfferingItem> offeringItems = new ArrayList<OfferingItem>();

    private ArrayList<City> availableCities= new ArrayList<City>();;

    public Instructor(String name, String password, String phone, String speciality, ArrayList<City> availableCities) {
        this.name = name;
        this.phone = phone;
        this.speciality = speciality;
        this.password = password;
        this.availableCities = availableCities;
    }
    public String getName() {
        return name;
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
    public void displayOfferingItems(){
        for(var o:offeringItems){
            System.out.println(o.toStringForInstructors());
        }
    }

    public String toString() {
        String availability="";
        int i=0;
        for(City city: availableCities){
            i++;
            availability+=city.getName();
            if(i>1 &&i<availableCities.size()-1){
                availability+=", ";
            } else if (i==availableCities.size()-1) {
                availability+=" and ";
            }
        }
        return  name + " (" + phone+ ") "+ "is a " + speciality+ " instructor, available to work in "+availability;
    }


    public String getSpeciality() {
        return speciality;
    }

    public ArrayList<OfferingItem> getOfferingItems() {
        return offeringItems;
    }
    public void removeOfferingItem(OfferingItem o){
        offeringItems.remove(o);
    }

    public List<City> geAvailableCities() {
        return availableCities;
    }

    public void addCity(City city) {
        availableCities.add(city);
    }
}
