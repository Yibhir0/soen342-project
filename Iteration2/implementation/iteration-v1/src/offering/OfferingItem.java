package offering;

import user.Instructor;

import java.time.LocalTime;

public class OfferingItem {

    private boolean isPrivate=false ;
    private LocalTime startTime;

    private boolean isAvailableToPublic = false;

    private LocalTime endTime;
    private Instructor instructor;

    public OfferingItem(boolean isPrivate, LocalTime startTime, LocalTime endTime) {
        this.isPrivate = isPrivate;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public void addInstructor(Instructor inst){
        instructor=inst;
    }

    public void setAvailableToPublic() {
        isAvailableToPublic = !this.isAvailableToPublic ;
    }

    public String toString(){
        return "Private: " + isPrivate + "\nStart Time: " + startTime + "\nEnd Time: " + endTime;
    }
}
