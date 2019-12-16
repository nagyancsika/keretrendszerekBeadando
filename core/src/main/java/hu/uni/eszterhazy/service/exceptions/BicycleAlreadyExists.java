package hu.uni.eszterhazy.service.exceptions;

public class BicycleAlreadyExists extends Exception {
    public BicycleAlreadyExists(String ID) {
        super(ID);
    }
}
