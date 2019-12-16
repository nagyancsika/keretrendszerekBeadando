package hu.uni.eszterhazy.service.exceptions;

public class BicycleNotFound extends Exception {
    public BicycleNotFound(String id) {
        super(id);
    }
}
