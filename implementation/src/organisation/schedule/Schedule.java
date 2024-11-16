package organisation.schedule;

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
        String daysOfWeek= daysOfTheWeekToString();
        //Sundays from 12PM to 3PM from 1.Sep to 30
        return  daysOfWeek
                + " from " + startTime.format(timeFormatter)
                + " to " + endTime.format(timeFormatter) +
                ", from " + startDate.format(dateFormatter)
                + " to " + endDate.format(dateFormatter)
                ;
    }
    public String daysToString() {
        DateTimeFormatter dateFormatter=DateTimeFormatter.ofPattern("d MMMM yyyy");
        String daysOfWeek= daysOfTheWeekToString();
        //Sunday from 1 September 2024 to 30 September 2024
        return  daysOfWeek
                + " from " + startDate.format(dateFormatter)
                + " to " + endDate.format(dateFormatter)
                ;
    }
    public String daysOfTheWeekToString(){
        String daysOfWeek="";
        int i=0;
        for(var day: dayOfWeekList){
            i++;
            daysOfWeek+=day.toString().charAt(0)+day.toString().substring(1).toLowerCase();
            if(i>1 &&i<dayOfWeekList.size()-1){
                daysOfWeek+=", ";
            } else if (i==dayOfWeekList.size()-1) {
                daysOfWeek+=" and ";
            }
        }
        return daysOfWeek;
    }

    public String getDaysOfWeek() {
        String daysOfWeek="";

        for (var day : dayOfWeekList) {
            daysOfWeek += day.toString() + ", ";
        }
        return daysOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public List<DayOfWeek> getDayOfWeek() {
        return dayOfWeekList;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
}
