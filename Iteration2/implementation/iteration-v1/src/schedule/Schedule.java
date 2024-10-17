package schedule;

import java.time.LocalDate;
import java.time.LocalTime;
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
        return "Start Date: " + startDate + "\nEnd Date: " + endDate + "\nDay of Week: " + dayOfWeekList + "\nStart Time: " + startTime + "\nEnd Time: " + endTime;
    }
}
