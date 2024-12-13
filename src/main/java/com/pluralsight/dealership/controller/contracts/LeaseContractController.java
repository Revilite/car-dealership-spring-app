package com.pluralsight.dealership.controller.contracts;


import com.pluralsight.dealership.dao.contracts.LeaseContractDao;
import com.pluralsight.dealership.model.contract.LeaseContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LeaseContractController {
    private LeaseContractDao leaseDB;

    @Autowired
    public LeaseContractController(LeaseContractDao leaseDB) {
        this.leaseDB = leaseDB;
    }

    @GetMapping("/lease/{id}")
    public LeaseContract getSalesById(@PathVariable("id") int id) {
        return leaseDB.findLeaseById(id);
    }

    @PostMapping("/lease")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addLease(@RequestBody LeaseContract contract) {
        leaseDB.addLeaseContract(contract);

    }

    @GetMapping("/allLeases")
    public List<LeaseContract> getAllLeases() {
        return leaseDB.findAllLeaseContracts();
    }
}
