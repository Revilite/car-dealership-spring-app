package com.pluralsight.dealership.dao.contracts;

import com.pluralsight.dealership.model.contract.SalesContract;
import com.pluralsight.dealership.model.vehicle.Vehicle;
import com.pluralsight.dealership.model.vehicle.VehicleforDummies;
import org.springframework.stereotype.Component;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SalesContractDAOMysqlImpl implements SalesContractDao {
    private final DataSource dataSource;

    public SalesContractDAOMysqlImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void saveSalesContract(SalesContract salesContract) {

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement saveContract = connection.prepareStatement("""
                    INSERT INTO sales_contracts (sales_tax_amount, recording_fee, processing_fee, total_price, date, customer_name, customer_email, vin, is_financing)
                    VALUES
                    (?, ?, ?, ?, ?, ?, ?, ?, ?);
                    """);
            saveContract.setDouble(1, salesContract.getSalesTaxAmount());
            saveContract.setDouble(2, salesContract.getRecordingFee());
            saveContract.setDouble(3, salesContract.getProcessingFee());
            saveContract.setDouble(4, salesContract.getTotalPrice());
            saveContract.setString(5, salesContract.getDate());
            saveContract.setString(6, salesContract.getCustomerName());
            saveContract.setString(7, salesContract.getCustomerEmail());
            saveContract.setInt(8, salesContract.getVehicleSold().getVin());
            saveContract.setBoolean(9, salesContract.isFinancing());

            PreparedStatement updateVehicle = connection.prepareStatement("""
                    UPDATE vehicles
                    set sold = 1
                    WHERE vin = ?;
                    """);

            updateVehicle.setInt(1, salesContract.getVehicleSold().getVin());

            saveContract.executeUpdate();
            updateVehicle.executeUpdate();

        } catch (SQLException e) {
            System.out.println("VIN does not exist");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SalesContract> findAllSalesContracts() {
        List<SalesContract> contracts = new ArrayList<>();

        String date, customerName, customerEmail, make, model, vehicleType, color;
        int vin, year, odometer;
        double price;
        boolean isFinancing;
        Vehicle vehicleSold;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement findAllSales = connection.prepareStatement("""
                    SELECT * FROM sales_contracts
                    JOIN vehicles ON vehicles.vin = sales_contracts.vin
                    ORDER BY date;
                    """);

            ResultSet resultSet = findAllSales.executeQuery();

            while (resultSet.next()) {


                date = resultSet.getString("date");
                customerName = resultSet.getString("customer_name");
                customerEmail = resultSet.getString("customer_email");
                make = resultSet.getString("make");
                model = resultSet.getString("model");
                vehicleType = resultSet.getString("vehicle_type");
                color = resultSet.getString("color");
                vin = resultSet.getInt("vin");
                year = resultSet.getInt("year");
                odometer = resultSet.getInt("odometer");
                price = resultSet.getDouble("price");
                isFinancing = resultSet.getBoolean("is_financing");

                vehicleSold = new VehicleforDummies(vin, year, make, model, vehicleType, color, odometer, price);
                contracts.add(new SalesContract(date, customerName, customerEmail, vehicleSold, isFinancing));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return contracts;
    }

    @Override
    public SalesContract findSalesContractById(int id) {
        String date, customerName, customerEmail, make, model, vehicleType, color;
        int vin, year, odometer;
        double price;
        boolean isFinancing;
        Vehicle vehicleSold;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("""
                    SELECT * FROM sales_contracts
                    JOIN vehicles ON vehicles.vin = sales_contracts.vin
                    WHERE sales_contract_id = ?;
                    """);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            rs.next();
            date = rs.getString("date");
            customerName = rs.getString("customer_name");
            customerEmail = rs.getString("customer_email");
            make = rs.getString("make");
            model = rs.getString("model");
            vehicleType = rs.getString("vehicle_type");
            color = rs.getString("color");
            vin = rs.getInt("vin");
            year = rs.getInt("year");
            odometer = rs.getInt("odometer");
            price = rs.getDouble("price");
            isFinancing = rs.getBoolean("is_financing");

            vehicleSold = new VehicleforDummies(vin, year, make, model, vehicleType, color, odometer, price);

            return new SalesContract(date, customerName, customerEmail, vehicleSold, isFinancing);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}