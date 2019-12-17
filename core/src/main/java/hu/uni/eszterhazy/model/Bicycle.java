package hu.uni.eszterhazy.model;

import hu.uni.eszterhazy.exceptions.DateNotAcceptable;
import hu.uni.eszterhazy.exceptions.IDNotMatching;
import hu.uni.eszterhazy.exceptions.YearOfManufacturingNotSet;

public class Bicycle {
    private String ID;
    private Brake brake;
    private String brand;
    private String frame;
    private Pedal pedals;
    private String color;
    private boolean broken;
    private int year_of_maufacturing =-1;

    public Bicycle() {
    }

    public Bicycle(String ID, Brake brake, String brand,
                   String frame, Pedal pedals, String color,
                   boolean broken, int year_of_maufacturing)
            throws IDNotMatching, YearOfManufacturingNotSet, DateNotAcceptable {
        setID(ID);
        this.brake = brake;
        this.brand = brand;
        this.frame = frame;
        this.pedals = pedals;
        this.color = color;
        this.broken = broken;
        this.year_of_maufacturing = year_of_maufacturing;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) throws IDNotMatching {
        if(!ID.matches("([A-Z]|[a-z]|\\d){3}")){
            throw new IDNotMatching(ID);
        }
        this.ID = ID;
    }

    public Brake getBrake() {
        return brake;
    }

    public void setBrake(Brake brake) {
        this.brake = brake;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public Pedal getPedals() {
        return pedals;
    }


    public void setPedals(Pedal pedals) {
        this.pedals = pedals;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isBroken() {
        return broken;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    public int getYear_of_maufacturing() {
        return year_of_maufacturing;
    }

    public void setYear_of_maufacturing(int year_of_maufacturing) {
        this.year_of_maufacturing = year_of_maufacturing;
    }

}
