package sample;

public class Assignment {
    String name;
    String startTime;
    String endTime;

    public Assignment(String name, String startTime, String endTime){
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName(){
        return name;
    }

    public String getStartTime(){
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
