package hu.uni.eszterhazy.exceptions;

public class IDNotMatching extends Throwable {
    public IDNotMatching(String vin) {
        super("Not maching VIN with the standard: "+vin);
    }
}
