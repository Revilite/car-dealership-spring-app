package com.pluralsight.dealership.controller.contracts;

import com.pluralsight.dealership.dao.contracts.SalesContractDao;
import com.pluralsight.dealership.model.contract.SalesContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SalesContractController {

    private SalesContractDao salesDB;

    @Autowired
    public SalesContractController(SalesContractDao salesDB) {
        this.salesDB = salesDB;
    }

    @GetMapping
    public SalesContract getSalesById(@RequestParam("id") int id){

        return salesDB.findSalesContractById(id);
    }
}