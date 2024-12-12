package com.pluralsight.dealership.controller.contracts;

import com.pluralsight.dealership.dao.contracts.SalesContractDao;
import com.pluralsight.dealership.model.contract.SalesContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SalesContractController {

    private SalesContractDao salesDB;

    @Autowired
    public SalesContractController(SalesContractDao salesDB) {
        this.salesDB = salesDB;
    }

    @GetMapping("/sale")
    public SalesContract getSalesById(@RequestParam("id") int id) {
        return salesDB.findSalesContractById(id);
    }

    @PostMapping("/sale")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addSalesContract(@RequestBody SalesContract contract){
        salesDB.saveSalesContract(contract);
    }
}
