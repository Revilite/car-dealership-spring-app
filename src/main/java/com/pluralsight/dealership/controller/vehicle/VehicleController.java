package com.pluralsight.dealership.controller.vehicle;


import com.pluralsight.dealership.dao.vehicles.VehicleDao;
import com.pluralsight.dealership.model.vehicle.VehicleforDummies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
