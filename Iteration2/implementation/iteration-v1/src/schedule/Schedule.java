package schedule;

import organisation.City;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Schedule {
    private LocalDate startDate;
    private LocalDate endDate;
    private List<DayOfWeek> dayOfWeekList;

    private LocalTime startTime;
    private LocalTime endTime;

    public Schedule(LocalDate startDate, LocalDate endDate, List<DayOfWeek> dayOfWeekList, LocalTime startTime, LocalTime endTime) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.dayOfWeekList = dayOfWeekList;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String toString() {
        DateTimeFormatter timeFormatter=DateTimeFormatter.ofPattern("h:mm a");
        DateTimeFormatter dateFormatter=DateTimeFormatter.ofPattern("d MMMM yyyy");
        String dayOfWeek="";
        int i=0;
        for(var day: dayOfWeekList){
            i++;
            dayOfWeek+=day.toString().charAt(0)+day.toString().substring(1).toLowerCase();
            if(i>1 &&i<dayOfWeekList.size()-1){
                dayOfWeek+=", ";
            } else if (i==dayOfWeekList.size()-1) {
                dayOfWeek+=" and ";
            }
        }
        //Sundays from 12PM to 3PM from 1.Sep to 30
        return  dayOfWeek
                + " from " + startTime.format(timeFormatter)
                + " to " + endTime.format(timeFormatter) +
                ", from " + startDate.format(dateFormatter)
                + " to " + endDate.format(dateFormatter)
                ;
    }
}
