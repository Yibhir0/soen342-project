import dataGenerator.organisation.OrganisationData;
import database.DatabaseSetup;
import organisation.Locations.City;
import organisation.offering.Booking;
import organisation.offering.Offering;


import organisation.Locations.Space;
import organisation.offering.OfferingItem;
import organisation.Locations.Location;
import organisation.Organisation;
import organisation.schedule.DayOfWeek;
import organisation.schedule.Schedule;
import organisation.user.Administrator;
import organisation.user.Client;
import organisation.user.Instructor;
import organisation.user.User;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;



public class Main {

    static Organisation org = OrganisationData.generateOrganizationData();

    public static void main(String[] args) {

        DatabaseSetup.createTables();

        mainMenu();
    }

public static void createOffering(Organisation organisation){
    //choose location
    Location location = chooseLocation(organisation.getLocations());
    if(location==null){
        return;
    }

    //choose space from chosen location

    Space space = chooseSpace(location);
    if(space==null){
        return;
    }

    //create organisation.schedule
    Schedule schedule = createSchedule();

    //enter lesson type
    String lessonType = createLessonType();
    //create Offering(String lessonType,Space space, Schedule organisation.schedule)
    Offering offering = new Offering(lessonType,space,schedule);

    //createOfferingItems(Offering organisation.offering)

    createOfferingItems(offering);

    org.addOffering(offering);




}

    private static String createLessonType() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter lesson type:");
        return scanner.nextLine();
    }


    /**
     * Create a organisation.schedule (we are assuming the organisation.user does not make mistakes)
     * @return Schedule
 */
    private static Schedule createSchedule() {

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter start date (yyyy-MM-dd): ");
            LocalDate startDate = LocalDate.parse(scanner.nextLine());

            System.out.print("Enter end date (yyyy-MM-dd): ");
            LocalDate endDate = LocalDate.parse(scanner.nextLine());

            System.out.print("Enter days of the week (comma separated, e.g., MONDAY,TUESDAY): ");
            List<DayOfWeek> dayOfWeekList = Arrays.stream(scanner.nextLine().split(","))
                    .map(String::trim)
                    .map(DayOfWeek::valueOf)
                    .collect(Collectors.toList());

            System.out.print("Enter start time (HH:mm): ");
            LocalTime startTime = LocalTime.parse(scanner.nextLine());

            System.out.print("Enter end time (HH:mm): ");
            LocalTime endTime = LocalTime.parse(scanner.nextLine());

            return new Schedule(startDate, endDate, dayOfWeekList, startTime, endTime);

    }

    private static Space chooseSpace(Location location) {
        ArrayList<Space> spaces = location.getSpaces();
        System.out.println("Choose a space:");
        int index = 0;
        for (Space space : spaces) {
            System.out.println((index++) + ": " + space);
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter space id:");
        int spaceId = scanner.nextInt();
        try{
            return spaces.get(spaceId);
        } catch (Exception e) {
            System.out.println("Invalid space id");
            return null;
        }
    }

    private static Location chooseLocation(ArrayList<Location> locations) {

    System.out.println("choose a location:");
    int index = 0;
    for (Location location : locations) {
        System.out.println((index++) + ": " + location);
    }
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter location id:");
    int locationId = scanner.nextInt();

    try {
        return locations.get(locationId);
    } catch (Exception e) {
        System.out.println("Invalid location id");
        return null;

    }

}
public static void createOfferingItems(Offering offering){
        Scanner scanner=new Scanner(System.in);
    String input="y";

    while(!input.equals("n")||!input.equals("N")||!input.equals("no")||!input.equals("NO")) {
        LocalTime start;
        LocalTime end;
        do {
            System.out.print("Enter start time (HH:mm):");//check if start time is between organisation.offering.Schedule.startTime and  organisation.offering.Schedule.endTime
            start = LocalTime.parse(scanner.next());
            if(offering.validateTime(start)){
                System.out.println("time needs to be between "+offering.getSchedule().getStartTime() + "and" + offering.getSchedule().getEndTime());
            }
        }while (offering.validateTime(start));
        do {
            System.out.print("Enter end time (HH:mm):");
            end = LocalTime.parse(scanner.next());
            if(offering.validateTime(end)){
                System.out.println("time needs to be between "+offering.getSchedule().getStartTime() + "and" + offering.getSchedule().getEndTime());
            }
        }while (offering.validateTime(end));

        System.out.print("Is this organisation.offering public? (y/n):");
        String in =scanner.next();
        boolean isPrivate=true;
        if(in.equals("y")){
            isPrivate=false;
        }


        offering.addOfferingItem(new OfferingItem(isPrivate, start, end,offering));

        System.out.print("continue? (y/n):");
        input=scanner.next();
    }

}


public static int logInAsAdmin(){
     User admin = new Administrator();

     System.out.println("Enter username:");
     Scanner scanner = new Scanner(System.in);
     String username = scanner.nextLine();
     System.out.println("Enter password:");
     String password = scanner.nextLine();
     return admin.login(username,password);
}

    public static void adminMenu(){

        Scanner scanner=new Scanner(System.in);

        while(true){
            System.out.println("1. Create Offering");
            System.out.println("2. View Offering");
            System.out.println("3. Cancel Offering");
            System.out.println("4. Cancel Booking");

            System.out.println("5. Logout");

            System.out.print("Enter choice:");
            int choice=scanner.nextInt();
            switch(choice){
                case 1:
                    createOffering(org);
                    break;
                case 2:
                    org.viewAllOfferingsForAdmin();
                    break;
                case 3:
                    adminCancel();
                    break;
                case 4:
                    cancelBookingByAdmin();
                    break;
                case 5:
                   return;
                default:
                    System.out.println("Invalid choice");
            }

        }
    }



    private static void mainMenu() {
    Scanner scanner = new Scanner(System.in);
    while (true) {

        System.out.println("1. Login as Admin");
        System.out.println("2. Login as Instructor");
        System.out.println("3. Login as Client");
        System.out.println("4. View Offerings");
        System.out.println("5. Register as Client");
        System.out.println("6. Register as Instructor");
        System.out.println("7. Exit");

        System.out.print("Enter choice:");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                if (logInAsAdmin() == 1) {
                    System.out.println("Successful login");
                    adminMenu();
                } else {
                    System.out.println("Invalid username or password ");
                }
                break;
            case 2:
                Instructor instructor = logInAsInstructor();
                if (instructor  != null) {
                    instructorMenu(instructor);
                } else{
                    System.out.println("Invalid username or password ");
                }
                break;
            case 3:
               Client client=logInAsClient();
                if (client  != null) {
                   clientMenu(client);
                } else{
                    System.out.println("Invalid username or password ");
                }
                break;
            case 4:
                org.viewOfferingsForPublic();
                break;
            case 5:
                registerAsClient();
                break;
            case 6:
                registerAsInstructor();
                break;
            case 7:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");
        }

    }
}



    private static void clientMenu(Client client) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. View Available Offering");
            System.out.println("2. Book Offering");
            System.out.println("3. View booked Offerings");
            System.out.println("4. Cancel booked Offering");
            System.out.println("5. Logout");

            System.out.print("Enter choice:");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    org.viewOfferingsForPublic();
                    break;
                case 2:
                    bookOffering(client);
                    break;
                case 3:
                    client.printBookedOfferings();
                    break;
                case 4:
                    cancelBookingByClient(client);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }


public static void bookOffering(Client client){
        ArrayList<OfferingItem> availableOfferingItems=org.getAvailableOfferingsForClient();

        org.viewOfferingsForPublic();//there is no index
         System.out.println("Enter index of Offering Item: ");
         Scanner s=new Scanner(System.in);
         int index= s.nextInt();
            try{
                OfferingItem bookedOffering=availableOfferingItems.get(index);
                Booking booking = new Booking(bookedOffering);
                client.bookOffering(booking);
               if(bookedOffering.book()) {
                System.out.println("Offering booked successfully");}
               else{
                   System.out.println("Offering already booked");
               }

            }
            catch(Exception e){
                System.out.println("Invalid Input");
            }

}
    /**
     * Instructor menu
     * @param instructor
     */
    private static void instructorMenu(Instructor instructor) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. View Available Offering");
            System.out.println("2. Select Offering");
            System.out.println("3. View your lessons");
            System.out.println("4. Logout");

            System.out.print("Enter choice:");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    org.viewAvailableOfferingsForInstructors(instructor);
                    break;
                case 2:
                    selectOffering(instructor);
                    break;
                case 3:
                    instructor.displayOfferingItems();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    /**
     * Select an organisation.offering
     * @param instructor
     */
    private static void selectOffering(Instructor instructor) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter organisation.offering id: [0,1,2...]");

        org.viewAvailableOfferingsForInstructors(instructor);

        int offeringId = scanner.nextInt();

        ArrayList<OfferingItem> offeringItems = org.getAvailableOfferingsForInstructors(instructor);

        try{

            OfferingItem offeringItem = offeringItems.get(offeringId);
            offeringItem.addInstructor(instructor);

            // we may add duplicates
            instructor.addOffering(offeringItem);
            System.out.println("Offering added successfully");

        }
        catch(Exception e){
            System.out.println("Invalid organisation.offering id");
        }

    }




    /**
     * Log in as an instructor
     * @return Instructor when successful, null otherwise
     */

    private static Instructor logInAsInstructor () {
        ArrayList<Instructor> instructors = org.getInstructors();

        System.out.println("Enter username:");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        for (var instructor : instructors) {
            if (instructor.login(username, password) == 1) {
                System.out.println("Login successful");
                return instructor;
            }
        }
        System.out.println("Account Not Found");
        return null;
    }

    private static Client logInAsClient () {

        ArrayList<Client> clients = org.getClients();

        System.out.println("Enter username:");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        for (Client client : clients) {
            if (client.login(username, password) == 1) {
                System.out.println("Login successful");
                return client;
            }
        }
        System.out.println("Account Not Found");
        return null;
    }


    // register for client and instructor
    public static void registerAsInstructor(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        System.out.println("Enter Phone Number:");
        String phone = scanner.nextLine();
        System.out.println("Enter Speciality:");
        String speciality = scanner.nextLine();
        List<City> cities = OrganisationData.generateCities();
        Instructor instructor = new Instructor(username, password,phone,speciality,cities);
        org.addInstructor(instructor);
        System.out.println("Login successful");
        instructorMenu(instructor);
    }

    public static void registerAsClient(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        Client client = new Client(username, password);
        org.addClient(client);
        System.out.println("Login successful");
        clientMenu(client);
    }


    private static void adminCancel(){
        System.out.println("1. Cancel Offering");
        System.out.println("2. Cancel Offering Item");
        System.out.print("Enter choice:");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                adminCancelOffering();
                break;
            case 2:
                adminCancelOfferingItem();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }


    // admin cancel offering
    private static void adminCancelOffering() {

        System.out.println("Choose offering you want to cancel");
        int offeringId = getIndexOfOffering();
        org.removeOffering(org.getOfferings().get(offeringId));
        System.out.println("Offering cancelled successfully");

    }

    private static int getIndexOfOffering(){
        org.viewAllOfferingsForAdmin();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter organisation.offering id: [0,1,2...]");
        int offeringId = scanner.nextInt();
        return offeringId;
    }

    // admin cancel offering item
    private static void adminCancelOfferingItem(){
        System.out.println("Choose offering item you want to cancel from");
        int offeringId = getIndexOfOffering();
        Offering offering = org.getOfferings().get(offeringId);
        offering.printOfferingItems();
        System.out.println("Enter organisation.offering item id: [0,1,2...]");
        Scanner scanner = new Scanner(System.in);
        int offeringItemId = scanner.nextInt();

        OfferingItem offeringItem = offering.getOfferingItemList().get(offeringItemId);

        offering.removeOfferingItem(offeringItem);

        System.out.println("Offering item cancelled successfully");
    }

    // cancel booking by client
    private static void cancelBookingByClient(Client client){
        client.printBookedOfferings();
        cancelBooking(client);
    }

    private static void cancelBooking(Client client){
        System.out.println("Enter index of Booking or -1 to return: [0,1,2...]" );
        Scanner scanner = new Scanner(System.in);
        int bookingId = scanner.nextInt();
        if(bookingId==-1){
            return;
        }
        Booking booking =client.getBookings().get(bookingId);
        booking.removeOfferingItem();
        client.removeBooking(booking);

        System.out.println("Booking cancelled successfully");
    }

    // cancel booking by admin
    public static void cancelBookingByAdmin(){
        System.out.println("Choose client you want to cancel booking for");
        int clientId = getIndexOfClient();
        Client client = org.getClients().get(clientId);

        client.printBookedOfferings();

        cancelBooking(client);

    }

    public static int getIndexOfClient(){
        org.printClientsForAdmin();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter client id: [0,1,2...]");
        int clientId = scanner.nextInt();
        return clientId;
    }











}

