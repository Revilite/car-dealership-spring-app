package com.pluralsight.dealership.dao.vehicles;

import com.pluralsight.dealership.model.vehicle.*;
import java.util.List;

public interface DealershipDAO {
    Dealership findDealershipById(int id);
    List<Dealership> findAllDealerships();


}
