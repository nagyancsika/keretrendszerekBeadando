package hu.uni.eszterhazy.exceptions;

public class IDNotMatching extends Throwable {
    public IDNotMatching(String id) {
        super("Not maching ID with the standard: "+id);
    }
}
