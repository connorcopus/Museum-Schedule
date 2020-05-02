package sample;

import java.util.Objects;

public class Shift {
    int shiftID;
    String startTime;
    String endTime;
    public Shift(int shiftID, String startTime, String endTime){
        this.shiftID = shiftID;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getShiftID() {
        return shiftID;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String calculateDuration(){
        String[] s = startTime.split(":");
        int startHours = Integer.parseInt(s[0]);
        int startMinutes = Integer.parseInt(s[1]);
        s = endTime.split(":");
        int endHours = Integer.parseInt(s[0]);
        int endMinutes = Integer.parseInt(s[1]);
        int hours;
        int minutes;
        if(startHours < endHours || (startHours == endHours && startMinutes <= endMinutes)){
            hours = endHours - startHours;
        }else{
            hours = (24 - startHours) + endHours;
        }
        minutes = endMinutes - startMinutes;
        hours += minutes/60;
        minutes = minutes%60;
        return new String("" + hours + " hour(s) and " + minutes + " minute(s)");
    }

}
