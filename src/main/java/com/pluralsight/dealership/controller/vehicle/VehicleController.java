package com.pluralsight.dealership.controller.vehicle;


import com.pluralsight.dealership.dao.vehicles.VehicleDao;
import com.pluralsight.dealership.model.vehicle.VehicleforDummies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VehicleController {
    private VehicleDao vehicleDB;


    //Injects in runtime
    @Autowired
    public VehicleController(VehicleDao vehicleDB) {
        this.vehicleDB = vehicleDB;
    }


    @GetMapping("/allCars")
    public List<VehicleforDummies> findAllVehicles() {
        return vehicleDB.findAllVehicles();
    }

    @GetMapping("/vehiclePrice")
    public List<VehicleforDummies> findVehiclesByPrice(@RequestParam("minPrice") double minPrince, @RequestParam("maxPrice") double maxPrice) {
        return vehicleDB.findVehiclesByPriceRange(minPrince, maxPrice);
    }

    @GetMapping("/vehicleMakeModel")
    public List<VehicleforDummies> findVehiclebyMakeModel(@RequestParam("make") String make, @RequestParam("model") String model) {
        return vehicleDB.findVehiclesByMakeModel(make, model);
    }

    @GetMapping("/vehicleYear")
    public List<VehicleforDummies> findVehicleByYear(@RequestParam("minYear") int minYear, @RequestParam("maxYear") int maxYear) {
        return vehicleDB.findVehiclesByYear(minYear, maxYear);
    }

    @GetMapping("/vehicleColor")
    public List<VehicleforDummies> findVehiclesByColor(@RequestParam("color") String color) {
        return vehicleDB.findVehicleByColor(color);
    }

    @GetMapping("/vehicleMiles")
    public List<VehicleforDummies> findVehiclesByMiles(@RequestParam("minMiles") int minMiles, @RequestParam("maxMiles") int maxMiles) {
        return vehicleDB.findVehicleByMileRange(minMiles, maxMiles);
    }

    @GetMapping("/vehicleType")
    public List<VehicleforDummies> findVehiclesByType(@RequestParam("type") String type) {
        return vehicleDB.findVehicleByVehicleType(type);
    }

    @GetMapping("/vehicle/{vin}")
    public VehicleforDummies findVehicleById(@PathVariable("vin") int vin){
        return vehicleDB.findVehicleByVIN(vin);
    }

    @PutMapping("/changeVehicle/{vin}")
    public void changeVehicle(@RequestBody VehicleforDummies vehicle, @PathVariable("vin") int vin){
        vehicleDB.changeVehicle(vehicle, vin);
    }

    @PostMapping("/addVehicle")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addVehicles(@RequestBody VehicleforDummies vehicle){
        vehicleDB.addVehicle(vehicle);
    }

    @DeleteMapping("/removeVehicle/{vin}")
    @ResponseStatus(code  = HttpStatus.NO_CONTENT)
    public void deleteVehicleById(@PathVariable("vin") int vin){
        vehicleDB.removeVehicleByVIN(vin);
    }
}



