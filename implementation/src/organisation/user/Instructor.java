package organisation.user;


import java.util.ArrayList;
import java.util.List;

import organisation.offering.OfferingItem;
import organisation.Locations.City;

public class Instructor implements User {

    private int id;
    private final String name;
    private String phone;
    private String speciality;
    private String password;

    private List<OfferingItem> offeringItems = new ArrayList<OfferingItem>();

    private List<City> availableCities= new ArrayList<City>();;



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

    public String getPhone() {
        return phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }


    public void setPhone(String phone) {
        this.phone = phone;
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

    public List<OfferingItem> getOfferingItems() {
        return offeringItems;
    }
    public void removeOfferingItem(OfferingItem o){
        offeringItems.remove(o);
    }

    public List<City> geAvailableCities() {
        return availableCities;
    }

    public void setAvailableCities(List<City> availableCities) {
        this.availableCities = availableCities;
    }

    public void addCity(City city) {
        availableCities.add(city);
    }

    public void setOfferingItems(ArrayList<OfferingItem> offeringItems) {
        this.offeringItems = offeringItems;
    }
}
