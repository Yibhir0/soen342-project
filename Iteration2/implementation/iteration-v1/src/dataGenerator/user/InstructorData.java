package dataGenerator.user;

import dataGenerator.organisation.OrganisationData;
import organisation.City;
import user.Instructor;

import java.util.ArrayList;
import java.util.List;

public class InstructorData {


    public static ArrayList<Instructor> generateInstructors() {

        ArrayList<Instructor> instructors = new ArrayList<>();

        List<City> cities = OrganisationData.generateCities();

        instructors.add(new Instructor("John","Doe","514-123-4567","Yoga",cities ));
        instructors.add(new Instructor("Jane","Doe","514-123-4567","Swim",cities ));
        return instructors;

    }
}
