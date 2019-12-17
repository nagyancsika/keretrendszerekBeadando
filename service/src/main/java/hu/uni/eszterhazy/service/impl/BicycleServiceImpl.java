package hu.uni.eszterhazy.service.impl;

import hu.uni.eszterhazy.dao.BicycleDAO;
import hu.uni.eszterhazy.model.Bicycle;
import hu.uni.eszterhazy.service.BicycleService;
import hu.uni.eszterhazy.service.exceptions.InvalidRange;
import hu.uni.eszterhazy.service.exceptions.BicycleAlreadyExists;
import hu.uni.eszterhazy.service.exceptions.BicycleNotFound;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class BicycleServiceImpl implements BicycleService {
    BicycleDAO dao;

    public BicycleServiceImpl(BicycleDAO dao) {
        this.dao = dao;
    }

    public Collection<Bicycle> listAllBicycles() throws IOException {
        return dao.readBicycles();
    }

    public Bicycle getBicycleByID(String ID) throws BicycleNotFound, IOException {
        return dao.readBicyclesById(ID);
    }

    public void addBicycle(Bicycle bicycle) throws BicycleAlreadyExists, IOException {
        dao.createBicycle(bicycle);

    }

    public void updateBicycle(Bicycle bicycle) throws BicycleNotFound, IOException {
        dao.updateBicycle(bicycle);

    }

    public void deleteBicycle(Bicycle bicycle) throws BicycleNotFound, IOException {
        dao.removeBicycle(bicycle);

    }

    public Collection<Bicycle> getBicyclesBetweenYears(int fromYear, int toYear) throws IOException, InvalidRange {
        if(toYear<fromYear){
            throw new InvalidRange(fromYear+"-"+toYear);
        }
        Collection<Bicycle> bicycles = listAllBicycles();
        Collection<Bicycle> result = new ArrayList<Bicycle>();
        for (Bicycle b : bicycles) {
            if(b.getYear_of_maufacturing()>=fromYear
                    && b.getYear_of_maufacturing()<=toYear){
                result.add(b);
            }

        }
        return result;

    }

    public Collection<Bicycle> getBicyclesByColor(String color) throws IOException {
        Collection<Bicycle> bicycles = listAllBicycles();
        Collection<Bicycle> result = new ArrayList<Bicycle>();
        for (Bicycle b : bicycles) {
            String bColor = b.getColor().toString();
            if(bColor.equals(color)) {
                result.add(b);
            }
        }
        return result;

    }
}
