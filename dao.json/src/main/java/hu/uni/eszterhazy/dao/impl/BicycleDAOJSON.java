package hu.uni.eszterhazy.dao.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import hu.uni.eszterhazy.dao.BicycleDAO;
import hu.uni.eszterhazy.model.Bicycle;
import hu.uni.eszterhazy.service.exceptions.BicycleAlreadyExists;
import hu.uni.eszterhazy.service.exceptions.BicycleNotFound;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class BicycleDAOJSON implements BicycleDAO {
    File jsonfile;
    ObjectMapper mapper;

    public BicycleDAOJSON(String filepath) throws IOException {
        jsonfile = new File(filepath);
        if (!jsonfile.exists()) {
            jsonfile.createNewFile();
            FileWriter writer = new FileWriter(jsonfile);
            writer.write("[]");
            writer.close();
        }
        if (jsonfile.getTotalSpace() <= 0) {
            FileWriter writer = new FileWriter(jsonfile);
            writer.write("[]");
            writer.close();
        }
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }


    public Collection<Bicycle> readBicycles() throws IOException {
        Collection<Bicycle> result = new ArrayList<Bicycle>();
        TypeReference type = new TypeReference<ArrayList<Bicycle>>() {
        };
        result = mapper.readValue(jsonfile, type);
        return result;
    }

    public Bicycle readBicyclesById(String id) throws IOException, BicycleNotFound {
        Collection<Bicycle> bicycles = readBicycles();
        for (Bicycle b : bicycles) {
            if(b.getID().equalsIgnoreCase(id)){
                return b;
            }
        }
        throw new BicycleNotFound(id);
    }

    public void createBicycle(Bicycle bicycle) throws IOException, BicycleAlreadyExists {
        Collection<Bicycle> bicycles = readBicycles();
        try {
            readBicyclesById(bicycle.getID());
            throw new BicycleAlreadyExists(bicycle.getID());
        } catch (BicycleNotFound bicycleNotFound) {
            bicycles.add(bicycle);
            mapper.writeValue(jsonfile, bicycles);
        }

    }

    public void updateBicycle(Bicycle bicycle) throws IOException, BicycleNotFound {
        Collection<Bicycle> bicycles = readBicycles();
        Bicycle c = readBicyclesById(bicycle.getID());
        List<Bicycle> bicyclelist = new ArrayList<Bicycle>(bicycles);
        int index = bicyclelist.indexOf(c);
        bicyclelist.set(index, bicycle);
        mapper.writeValue(jsonfile,bicyclelist);

    }

    public void removeBicycle(Bicycle bicycle) throws IOException, BicycleNotFound {
        Collection<Bicycle> bicycles = readBicycles();
        Bicycle b = readBicyclesById(bicycle.getID());
        bicycles.remove(b);
        mapper.writeValue(jsonfile, bicycles);
    }
}
