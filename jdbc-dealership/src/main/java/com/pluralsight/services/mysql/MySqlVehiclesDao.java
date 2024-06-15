package com.pluralsight.services.mysql;

import com.pluralsight.models.Vehicle;
import com.pluralsight.services.VehiclesDao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlVehiclesDao implements VehiclesDao
{
    private DataSource dataSource;

    public MySqlVehiclesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Vehicle saveVehicle(Vehicle vehicle)
    {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO vehicle (make, model, price, year, color, odometer, type)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, vehicle.getMake());
            statement.setString(2, vehicle.getModel());
            statement.setDouble(3, vehicle.getPrice());
            statement.setInt(4, vehicle.getYear());
            statement.setString(5, vehicle.getColor());
            statement.setInt(6, vehicle.getOdometer());
            statement.setString(7, vehicle.getVehicleType());

            statement.executeUpdate();

            ResultSet key = statement.getGeneratedKeys();
            key.next();
            int id = key.getInt(1);

            return getVehicleByVin(id);
        } catch (Exception e) {
            System.out.println("error with add");
        }
        return null;
    }

    @Override
    public Vehicle getVehicleByVin(int vin)
    {
        try (Connection connection = dataSource.getConnection()) {
            String sql = """
                    SELECT vehicle_id
                        , make
                        , model
                        , price
                        , year
                        , color
                        , odometer
                        , type
                    FROM vehicle
                    WHERE vin = ?;
                    """;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, vin);

            ResultSet row = statement.executeQuery();

            if (row.next()) {
                String vehicleId = row.getString("vin");
                int year = row.getInt("year");
                String make = row.getString("make");
                String model = row.getString("model");
                String type = row.getString("type");
                String color = row.getString("color");
                int mileage = row.getInt("odometer");
                double price = row.getDouble("price");

                return new Vehicle(vehicleId, year, make, model, type, color, mileage, price);
            }
        } catch (SQLException e) {
            System.out.println("error with vehicle id ");
        }
        return null;
    }

    @Override
    public List<Vehicle> getAllVehicles()
    {
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = """
                    SELECT vin
                        , make
                        , model
                        , price
                        , year
                        , color
                        , odometer
                        , type
                    FROM vehicle;
                    """;

            Statement statement = connection.createStatement();
            ResultSet row = statement.executeQuery(sql);

            while (row.next()) {
                String vehicleId = row.getString("vin");
                int year = row.getInt("year");
                String make = row.getString("make");
                String model = row.getString("model");
                String type = row.getString("type");
                String color = row.getString("color");
                int mileage = row.getInt("odometer");
                double price = row.getDouble("price");


                vehicles.add(new Vehicle(vehicleId, year, make, model, type, color, mileage, price));
            }
        } catch (Exception e) {
            System.out.println("error with get all vehicles");
        }
        return vehicles;
    }

    @Override
    public void updateVehicle(Vehicle vehicle)
    {
        try (Connection connection = dataSource.getConnection()) {
            String sql = """
                UPDATE vehicle
                SET make = ?
                    , model = ?
                    , price = ?
                    , year = ?
                    , color = ?
                    , odometer = ?
                    , type = ?
                WHERE vin = ?;
                """;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, vehicle.getMake());
            statement.setString(2, vehicle.getModel());
            statement.setDouble(3, vehicle.getPrice());
            statement.setInt(4, vehicle.getYear());
            statement.setString(5, vehicle.getColor());
            statement.setInt(6, vehicle.getOdometer());
            statement.setString(7, vehicle.getVehicleType());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error with update");
        }

    }

    @Override
    public void deleteVehicle(int vin)
    {
        try (Connection connection = dataSource.getConnection()) {
            String sql = """
                   DELETE FROM vehicle
                   WHERE vin = ?;
                   """;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, vin);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error with delete");
        }

    }

    @Override
    public List<Vehicle> getVehiclesByPriceRange(double minPrice, double maxPrice)
    {
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = """
                    SELECT vin
                        , make
                        , model
                        , price
                        , year
                        , color
                        , odometer
                        , type
                    FROM vehicle
                    WHERE price BETWEEN ? AND ?;
                    """;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, minPrice);
            statement.setDouble(2, maxPrice);

            ResultSet row = statement.executeQuery();

            while (row.next()) {
                String vehicleId = row.getString("vin");
                int year = row.getInt("year");
                String make = row.getString("make");
                String model = row.getString("model");
                String type = row.getString("type");
                String color = row.getString("color");
                int mileage = row.getInt("odometer");
                double price = row.getDouble("price");

                vehicles.add(new Vehicle(vehicleId, year, make, model, type, color, mileage, price));
            }
        } catch (Exception e) {
            System.out.println("error with price range");
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> getVehiclesByMakeAndModel(String make, String model)
    {
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = """
                    SELECT vin
                        , make
                        , model
                        , price
                        , year
                        , color
                        , mileage_type
                        , type
                    FROM vehicle
                    WHERE make LIKE ? AND model LIKE ?;
                    """;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + make + "%");
            statement.setString(2, "%" + model + "%");

            ResultSet row = statement.executeQuery();

            while (row.next()) {
                String vehicleId = row.getString("vin");
                int year = row.getInt("year");
                String vehicleMake = row.getString("make");
                String vehicleModel = row.getString("model");
                String type = row.getString("type");
                String color = row.getString("color");
                int mileage = row.getInt("odometer");
                double price = row.getDouble("price");

                vehicles.add(new Vehicle(vehicleId, year, vehicleMake, vehicleModel, type, color, mileage, price));
            }
        } catch (Exception e) {
            System.out.println("error with make and model");
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> getVehiclesByYearRange(int minYear, int maxYear)
    {
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = """
                SELECT vin
                    , make
                    , model
                    , price
                    , year
                    , color
                    , odometer
                    , type
                FROM vehicle
                WHERE year BETWEEN ? AND ?;
                """;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, minYear);
            statement.setInt(2, maxYear);

            ResultSet row = statement.executeQuery();

            while (row.next()) {
                String vehicleId = row.getString("vin");
                int year = row.getInt("year");
                String vehicleMake = row.getString("make");
                String vehicleModel = row.getString("model");
                String type = row.getString("type");
                String color = row.getString("color");
                int mileage = row.getInt("odometer");
                double price = row.getDouble("price");

                vehicles.add(new Vehicle(vehicleId, year, vehicleMake, vehicleModel, type, color, mileage, price));
            }
        } catch (Exception e) {
            System.out.println("error with vehicle year range");
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> getVehiclesByColor(String color)
    {
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = """
                SELECT vin
                    , make
                    , model
                    , price
                    , year
                    , color
                    , odometer
                    , type
                FROM vehicle
                WHERE color LIKE ?;
                """;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + color + "%");

            ResultSet row = statement.executeQuery();

            while (row.next()) {
                String vehicleId = row.getString("vin");
                int year = row.getInt("year");
                String vehicleMake = row.getString("make");
                String vehicleModel = row.getString("model");
                String type = row.getString("type");
                String vehicleColor = row.getString("color");
                int mileage = row.getInt("odometer");
                double price = row.getDouble("price");

                vehicles.add(new Vehicle(vehicleId, year, vehicleMake, vehicleModel, type, vehicleColor, mileage, price));
            }
        } catch (Exception e) {
            System.out.println("error with color");
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> getVehiclesByMileageType(String mileageType)
    {
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = """
                SELECT vin
                    , make
                    , model
                    , price
                    , year
                    , color
                    , odometer
                    , type
                FROM vehicle
                WHERE mileage_type LIKE ?;
                """;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + mileageType + "%");

            ResultSet row = statement.executeQuery();

            while (row.next()) {
                String vehicleId = row.getString("vin");
                int year = row.getInt("year");
                String vehicleMake = row.getString("make");
                String vehicleModel = row.getString("model");
                String type = row.getString("type");
                String vehicleColor = row.getString("color");
                int mileage = row.getInt("odometer");
                double price = row.getDouble("price");

                vehicles.add(new Vehicle(vehicleId, year, vehicleMake, vehicleModel, type, vehicleColor, mileage, price));
            }
        } catch (Exception e) {
            System.out.println("error with odometer range");
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> getVehiclesByType(String type)
    {
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = """
                SELECT vin
                    , make
                    , model
                    , price
                    , year
                    , color
                    , odometer
                    , type
                FROM vehicle
                WHERE type LIKE ?;
                """;

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + type + "%");

            ResultSet row = statement.executeQuery();

            while (row.next()) {
                String vehicleId = row.getString("vin");
                int year = row.getInt("year");
                String vehicleMake = row.getString("make");
                String vehicleModel = row.getString("model");
                String vehicleType = row.getString("type");
                String vehicleColor = row.getString("color");
                int mileage = row.getInt("odometer");
                double price = row.getDouble("price");

                vehicles.add(new Vehicle(vehicleId, year, vehicleMake, vehicleModel, vehicleType, vehicleColor, mileage, price));
            }
        } catch (Exception e) {
        }
        return vehicles;
    }
}
