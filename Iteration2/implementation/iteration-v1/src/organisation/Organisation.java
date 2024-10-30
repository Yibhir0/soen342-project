package organisation;

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

    public ArrayList<Client> getClients() {
        return clients;
    }
    public void setClients(ArrayList<Client> Clients) {
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
        for (var offering :offerings) {
            offering.printOfferingsForAdmin();
        }
    }


    /**
     * Display available offerings to take and offerings already taken by the instructor
     * @param instructor
     */
   public void viewAvailableOfferingsForInstructors(Instructor instructor) {

        ArrayList<OfferingItem> availableInstructorItems = getAvailableOfferings(instructor);
        if(availableInstructorItems.isEmpty()){
            System.out.println("No available Offerings.");
            return;
        }
        for (var offeringItem : availableInstructorItems) {
            System.out.println(offeringItem);
        }

    }
    /**
     *
     * @param instructor
     * @return
     */
    public ArrayList<OfferingItem> getAvailableOfferings(Instructor instructor) {
        ArrayList<OfferingItem> availableOfferings = new ArrayList<>();
        for (var offering : getOfferings()) {
            if (offering.getLessonType().equals(instructor.getSpeciality())) {
                for(var item:offering.getOfferingItemList()){
                    if(!item.hasInstructor()){
                        availableOfferings.add(item);
                    }

                }

            }
        }
        return availableOfferings;
    }

}
