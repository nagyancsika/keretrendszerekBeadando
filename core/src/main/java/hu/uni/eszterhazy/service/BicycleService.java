package hu.uni.eszterhazy.service;

import hu.uni.eszterhazy.service.exceptions.BicycleAlreadyExists;

import hu.uni.eszterhazy.model.Bicycle;
import hu.uni.eszterhazy.service.exceptions.BicycleNotFound;
import hu.uni.eszterhazy.service.exceptions.InvalidRange;

import java.io.IOException;
import java.util.Collection;

public interface BicycleService {
    Collection<Bicycle> listAllBicycles() throws IOException;

    Bicycle getBicycleByID(String ID) throws BicycleNotFound, IOException;

    void addBicycle(Bicycle bicycle) throws BicycleAlreadyExists, IOException;

    void updateBicycle(Bicycle bicycle) throws BicycleNotFound, IOException;

    void deleteBicycle(Bicycle bicycle) throws BicycleNotFound, IOException;

    Collection<Bicycle> getBicyclesBetweenYears(int fromYear, int toYear) throws IOException, InvalidRange;

    Collection<Bicycle> getBicyclesByColor(String color) throws IOException;
}
