package hu.uni.eszterhazy.dao;

import hu.uni.eszterhazy.model.Bicycle;
import hu.uni.eszterhazy.service.exceptions.BicycleAlreadyExists;
import hu.uni.eszterhazy.service.exceptions.BicycleNotFound;

import java.io.IOException;
import java.util.Collection;

public interface BicycleDAO {

    Collection<Bicycle> readBicycles() throws IOException;

    Bicycle readBicyclesById(String id) throws IOException, BicycleNotFound;

    void createBicycle(Bicycle bicycle) throws IOException, BicycleAlreadyExists;

    void updateBicycle(Bicycle bicycle) throws IOException, BicycleNotFound;

    void removeBicycle(Bicycle bicycle) throws IOException, BicycleNotFound;
}
