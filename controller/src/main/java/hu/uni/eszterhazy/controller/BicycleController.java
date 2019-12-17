package hu.uni.eszterhazy.controller;

import hu.uni.eszterhazy.model.Bicycle;
import hu.uni.eszterhazy.service.BicycleService;
import hu.uni.eszterhazy.service.exceptions.BicycleAlreadyExists;
import hu.uni.eszterhazy.service.exceptions.BicycleNotFound;
import hu.uni.eszterhazy.service.exceptions.InvalidRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;

@Controller
public class BicycleController {
    BicycleService service;

    public BicycleController(@Autowired BicycleService service) {
        this.service = service;
    }

    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hello(){
        return "Hello";
    }

    @RequestMapping(value = "/count")
    @ResponseBody
    public int getBicycleNumber() throws IOException {
        return service.listAllBicycles().size();
    }

    @RequestMapping(value = "/listBicycles")
    @ResponseBody
    public Collection<Bicycle> listBicycles() throws IOException {
        return service.listAllBicycles();
    }

    @RequestMapping(value = "/addBicycle",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addBicycle(@RequestBody Bicycle bicycle) throws IOException, BicycleAlreadyExists {
        service.addBicycle(bicycle);
        return "New Bicycle added: "+bicycle.getID();
    }

    @RequestMapping(value = "/bicycle/{ID:[A-Za-z0-9]{3}}")
    @ResponseBody
    public Bicycle getBicycleByID(@PathVariable String ID) throws IOException, BicycleNotFound {
        
        return service.getBicycleByID(ID);
    }

    @RequestMapping(value = "/listBicycles/years")
    @ResponseBody
    public Collection<Bicycle> getBicyclesBetweenYears(
            @RequestParam(required = true) int fromYear,
            @RequestParam(required = true) int toYear) throws IOException, InvalidRange {
        return service.getBicyclesBetweenYears(fromYear,toYear);
    }

    @RequestMapping(value = "/listBicycles/{fromYear}-{toYear}")
    @ResponseBody
    public Collection<Bicycle> getBicyclesBetweenYearsPath(
            @PathVariable int fromYear, @PathVariable int toYear
    ) throws IOException, InvalidRange {
        return service.getBicyclesBetweenYears(fromYear,toYear);
    }

    @RequestMapping(value = "/listBicyclesByColor/{color}")
    @ResponseBody
    public Collection<Bicycle> getBicyclesByColorPath(
            @PathVariable String color
    ) throws IOException {
        return service.getBicyclesByColor(color);
    }

}
