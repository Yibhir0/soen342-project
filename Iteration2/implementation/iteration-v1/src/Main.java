import dataGenerator.organisation.OrganisationData;
import offering.Offering;


import organisation.Space;
import offering.OfferingItem;
import organisation.Location;
import organisation.Organisation;
import schedule.DayOfWeek;
import schedule.Schedule;
import user.Administrator;
import user.Instructor;
import user.User;


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

    //create schedule
    Schedule schedule = createSchedule();



    //enter lesson type
    String lessonType = createLessonType();
    //create Offering(String lessonType,Space space, Schedule schedule)
    Offering offering = new Offering(lessonType,space,schedule);

    //createOfferingItems(Offering offering)

    createOfferingItems(offering);

    org.addOffering(offering);




}

    private static String createLessonType() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter lesson type:");
        return scanner.nextLine();
    }


    /**
     * Create a schedule (we are assuming the user does not make mistakes)
     * @return
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
            System.out.print("Enter start time (HH:mm):");//check if start time is between offering.Schedule.startTime and  offering.Schedule.endTime
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

        System.out.print("Is this offering public? (y/n):");
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
            System.out.println("3. Exit");

            System.out.print("Enter choice:");
            int choice=scanner.nextInt();
            switch(choice){
                case 1:
                    createOffering(org);
                    break;
                case 2:
                    viewOffering();
                    break;
                case 3:
                    System.exit(0);
                    break;
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
        System.out.println("3. View Offerings");
        System.out.println("4. Exit");

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
                viewOffering();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");
        }

    }
}


    /**
     * Display available offerings to take and offerings already taken by the instructor
     * @param instructor
     */
    private static void viewAvailableOfferingsForInstructors(Instructor instructor) {

        ArrayList<OfferingItem> availableInstructorItems = getAvailableOfferings(instructor);
        for (var offeringItem : availableInstructorItems) {
            System.out.println(offeringItem);
        }

    }


    /**
     *
     * @param instructor
     * @return
     */
    private static ArrayList<OfferingItem> getAvailableOfferings(Instructor instructor) {
        ArrayList<OfferingItem> availableOfferings = new ArrayList<>();
        for (var offering : org.getOfferings()) {
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

    /**
     * Instructor menu
     * @param instructor
     */
    private static void instructorMenu(Instructor instructor) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. View Available Offering");
            System.out.println("2. Select Offering");
            System.out.println("3. View your lessons:");
            System.out.println("4. Exit");

            System.out.print("Enter choice:");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    viewAvailableOfferingsForInstructors(instructor);
                    break;
                case 2:
                    selectOffering(instructor);
                    break;
                case 3:
                    instructor.displayOfferingItems();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    /**
     * Select an offering
     * @param instructor
     */
    private static void selectOffering(Instructor instructor) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter offering id: [0,1,2...]");

        viewAvailableOfferingsForInstructors(instructor);

        int offeringId = scanner.nextInt();

        ArrayList<OfferingItem> offeringItems = getAvailableOfferings(instructor);

        try{

            OfferingItem offeringItem = offeringItems.get(offeringId);
            offeringItem.addInstructor(instructor);

            // we may add duplicates
            instructor.addOffering(offeringItem);
            System.out.println("Offering added successfully");

        }
        catch(Exception e){
            System.out.println("Invalid offering id");
        }

    }


    /**
     * view all offerings that are taken by an Instructor (public can't see offering not taken)
     */
    private static void viewOffering() {
        for (var offering : org.getOfferings()) {
            offering.viewOfferingsWithInstructor();
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


}

