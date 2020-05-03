package sample;

import java.util.ArrayList;
import java.util.Objects;

public class Volunteer {
    int id;
    String name;
    ArrayList<Shift> shifts = new ArrayList<Shift>();
    public Volunteer(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }
}
