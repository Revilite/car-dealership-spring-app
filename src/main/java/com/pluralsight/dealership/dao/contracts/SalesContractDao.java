package com.pluralsight.dealership.dao.contracts;

import com.pluralsight.dealership.model.contract.SalesContract;

import java.util.List;

public interface SalesContractDao {
    void saveSalesContract(SalesContract salesContract);

    List<SalesContract> findAllSalesContracts();
}
