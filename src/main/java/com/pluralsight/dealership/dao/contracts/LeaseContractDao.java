package com.pluralsight.dealership.dao.contracts;

import com.pluralsight.dealership.model.contract.LeaseContract;

import java.util.List;

public interface LeaseContractDao {
    void addLeaseContract(LeaseContract contract);
    List<LeaseContract> findAllLeaseContracts();
}
