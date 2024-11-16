package organisation;

import organisation.Locations.City;
import organisation.offering.Offering;
import organisation.Locations.Location;
import organisation.offering.OfferingItem;
import organisation.user.Client;
import organisation.user.Instructor;

import java.util.ArrayList;

public class Organisation {
    private String name;
    private ArrayList<Location> locations;

    private ArrayList<Instructor> instructors;
    private ArrayList<Offering> offerings;
    private ArrayList<Client> clients;

    public Organisation(String name) {
        this.name = name;
        this.locations=new ArrayList<Location>();
    }
    public String getName() {
        return name;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }
    public void addLocation(Location location) {
       locations.add(location);
    }
    //temporary mean to get locations without database
    public void setLocations(ArrayList<Location> loc){
        this.locations=loc;
    }
    public void setOfferings(ArrayList<Offering> offer){
        this.offerings=offer;
    }
    public void addOffering(Offering offer){
        this.offerings.add(offer);
    }
    public ArrayList<Offering> getOfferings() {
        return offerings;
    }

    public void setInstructors(ArrayList<Instructor> instructors) {
        this.instructors = instructors;
    }

    public ArrayList<Instructor> getInstructors() {
        return this.instructors;
    }
    public void removeInstructor(Instructor instructor){
        instructors.remove(instructor);
    }
    public void removeClient(Client client){
        clients.remove(client);
    }
    public ArrayList<Client> getClients() {
        return clients;
    }
    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    /**
     * view all  available offerings that are taken by an Instructor (public can't see offerings not taken)
     */
    public  void viewOfferingsForPublic() {
        for (var offering :offerings) {
            offering.printOfferingsForPublic();
        }
    }

    /**
     * view all offerings
     */
    public  void viewAllOfferingsForAdmin() {

        int i=0;
        for (var offering :offerings) {
            System.out.println(i++ +": ");
            offering.printOfferingsForAdmin();
        }
    }

    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
    }


    /**
     * Display available offerings to take and offerings already taken by the instructor
     * @param instructor
     */
   public void viewAvailableOfferingsForInstructors(Instructor instructor) {

        ArrayList<OfferingItem> availableInstructorItems = getAvailableOfferingsForInstructors(instructor);
        if(availableInstructorItems.isEmpty()){
            System.out.println("No available Offerings.");
            return;
        }
        int i=0;
        for (var offeringItem : availableInstructorItems) {
            System.out.println(i++ +": "+ offeringItem.toStringForInstructors());
        }

    }


    /**
     *
     * @param instructor
     * @return
     */
    public ArrayList<OfferingItem> getAvailableOfferingsForInstructors(Instructor instructor) {
        ArrayList<OfferingItem> availableOfferings = new ArrayList<>();

        for (var offering : getOfferings()) {
            City city= offering.getSpace().getLocation().getCity();
            if (offering.getLessonType().equalsIgnoreCase(instructor.getSpeciality()) && instructor.geAvailableCities().contains(city)) {
                for(var item:offering.getOfferingItemList()){
                    if(!item.hasInstructor()){
                        availableOfferings.add(item);
                    }

                }

            }
        }
        return availableOfferings;
    }

    //Gets all available offerings that have an instructor
    public ArrayList<OfferingItem> getAvailableOfferingsForClient() {
        ArrayList<OfferingItem> availableOfferings = new ArrayList<>();
        for (var offering : getOfferings()) {
                for(var item:offering.getOfferingItemList()){
                    if(item.hasInstructor()&&item.isAvailable()){
                        availableOfferings.add(item);
                    }
                }
        }
        return availableOfferings;
    }

    /**
     * add a client to the organisation
     * @param client
     */
    public void addClient(Client client) {
        clients.add(client);
    }


    /**
     * remove an offering from the organisation
     * @param offering
     */
    public void removeOffering(Offering offering) {
        offerings.remove(offering);
    }

    public void printClientsForAdmin(){
        int i=0;
        for (var client: clients){
            System.out.println(i++ +": " + client.getUsername());
            client.printBookedOfferings();
        }
    }


    public ArrayList<City> getAvailableCities() {
        ArrayList<City> cities =new ArrayList<>();
        for(var l : locations){
            City city=l.getCity();
            if(!cities.contains(city)){
                cities.add(city);
            }
        }
        return cities;
    }
}
