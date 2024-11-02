package dataGenerator.schedule;

import organisation.schedule.DayOfWeek;
import organisation.schedule.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class ScheduleData {


    public static ArrayList<Schedule> generateSchedules() {

        ArrayList<Schedule> schedules = new ArrayList<>();

        schedules.add(new Schedule(LocalDate.of(2024, 10, 17), LocalDate.of(2024, 10, 29), Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY), LocalTime.of(9, 0), LocalTime.of(10, 0)));
        schedules.add(new Schedule(LocalDate.of(2024, 12, 1), LocalDate.of(2024, 12, 31), Arrays.asList(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY), LocalTime.of(10, 0), LocalTime.of(11, 0)));

        return schedules;

    }
}
