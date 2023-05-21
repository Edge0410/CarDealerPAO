package service;

import config.DatabaseConfig;
import constants.CarManufacturers;
import constants.Colors;
import constants.MotorcycleManufacturers;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {
    private static DatabaseService instance;

    private DatabaseService(){

    }

    public static DatabaseService getInstance(){
        if (instance == null)
            instance = new DatabaseService();

        return instance;
    }



    public void loadDieselEngines() throws SQLException {
        Connection connection = DatabaseConfig.getInstance().getDatabaseConnection();
        Statement statement = connection.createStatement();

        String sql = "SELECT * FROM bdpao.dieselengines";
        ResultSet resultSet = statement.executeQuery(sql);

        List<DieselEngine> dieselEngines = new ArrayList<>();

        while (resultSet.next()) {
            double capacity = resultSet.getDouble("capacity");
            int horsePower = resultSet.getInt("horsepower");
            int cylinderNumber = resultSet.getInt("cylindernumber");
            int torque = resultSet.getInt("torque");

            DieselEngine dieselEngine = new DieselEngine(capacity, horsePower, cylinderNumber, torque);
            VehicleService.getInstance().addDieselEngine(dieselEngine);
        }

        resultSet.close();
        statement.close();
    }

    public void loadPetrolEngines() throws SQLException {
        Connection connection = DatabaseConfig.getInstance().getDatabaseConnection();
        Statement statement = connection.createStatement();

        String sql = "SELECT * FROM bdpao.petrolengines";
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            double capacity = resultSet.getDouble("capacity");
            int horsePower = resultSet.getInt("horsepower");
            int cylinderNumber = resultSet.getInt("cylindernumber");
            int torque = resultSet.getInt("torque");

            PetrolEngine petrolEngine = new PetrolEngine(capacity, horsePower, cylinderNumber, torque);
            VehicleService.getInstance().addPetrolEngine(petrolEngine);
        }

        resultSet.close();
        statement.close();
    }

    public void loadSedans() throws SQLException {
        Connection connection = DatabaseConfig.getInstance().getDatabaseConnection();
        Statement statement = connection.createStatement();

        String sql = "SELECT * FROM bdpao.sedans";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String manufacturerString = resultSet.getString("manufacturer");
            String model = resultSet.getString("model");
            int year = resultSet.getInt("year");
            String colorString = resultSet.getString("color");
            int petrolEngineId = resultSet.getInt("petrolengine_id");
            int dieselEngineId = resultSet.getInt("dieselengine_id");

            CarManufacturers manufacturer = CarManufacturers.valueOf(manufacturerString);
            Colors color = Colors.valueOf(colorString);

            Sedan sedan = new Sedan(manufacturer, model, year, color);
            VehicleService.getInstance().addCar(sedan);
            //cautam indexul motorului care este pus pe masina

            int engineIndex = -1;

            if(dieselEngineId != 0){
                engineIndex = VehicleService.getInstance().findDieselEngineById(dieselEngineId);
                VehicleService.getInstance().addEngineToCar(-1,engineIndex,true);
            }
            if(petrolEngineId != 0){
                engineIndex = VehicleService.getInstance().findPetrolEngineById(petrolEngineId);
                VehicleService.getInstance().addEngineToCar(-1,engineIndex,false);
            }
            // adaugam si motorul daca este necesar
        }
    }

    public void loadSportbikes() throws SQLException {
        Connection connection = DatabaseConfig.getInstance().getDatabaseConnection();
        Statement statement = connection.createStatement();

        String sql = "SELECT * FROM bdpao.sportbikes";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String manufacturerString = resultSet.getString("manufacturer");
            String model = resultSet.getString("model");
            int year = resultSet.getInt("year");
            String colorString = resultSet.getString("color");
            int maxSpeed = resultSet.getInt("maxSpeed");
            int petrolEngineId = resultSet.getInt("petrolengine_id");

            MotorcycleManufacturers manufacturer = MotorcycleManufacturers.valueOf(manufacturerString);
            Colors color = Colors.valueOf(colorString);

            Sportbike sedan = new Sportbike(manufacturer, model, year, color, maxSpeed);
            VehicleService.getInstance().addMotorcycle(sedan);
            //cautam indexul motorului care este pus pe masina

            int engineIndex = -1;

            if(petrolEngineId != 0){
                engineIndex = VehicleService.getInstance().findPetrolEngineById(petrolEngineId);
                VehicleService.getInstance().addEngineToMotorcycle(-1,engineIndex);
            }
            // adaugam si motorul daca este necesar
        }
    }

    public void addSedanToDb(Sedan sedan){
        try {
            Connection connection = DatabaseConfig.getInstance().getDatabaseConnection();

            // Prepare the SQL statement with parameters
            String sql = "INSERT INTO sedans (manufacturer, model, year, color) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, sedan.getManufacturer().toString());
            statement.setString(2, sedan.getModel());
            statement.setInt(3, sedan.getYear());
            statement.setString(4, sedan.getColor().toString());

            // Execute the SQL statement
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Sedan adaugat in baza de date.");
            } else {
                System.out.println("Sedanul nu a fost adaugat in baza de date.");
            }

            // Close the statement and connection
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addSportbikeToDb(Sportbike sportbike) {
        try {
            Connection connection = DatabaseConfig.getInstance().getDatabaseConnection();

            // Prepare the SQL statement with parameters
            String sql = "INSERT INTO sportbikes (manufacturer, model, year, color, maxSpeed) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, sportbike.getManufacturer().toString());
            statement.setString(2, sportbike.getModel());
            statement.setInt(3, sportbike.getYear());
            statement.setString(4, sportbike.getColor().toString());
            statement.setInt(5, sportbike.getMaxSpeed());

            // Execute the SQL statement
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Sportbike adaugat in baza de date.");
            } else {
                System.out.println("Sportbike-ul nu a fost adaugat in baza de date.");
            }

            // Close the statement and connection
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addDieselEngineToDb(DieselEngine dieselEngine) {
        try {
            Connection connection = DatabaseConfig.getInstance().getDatabaseConnection();

            // Prepare the SQL statement with parameters
            String sql = "INSERT INTO dieselengines (capacity, horsepower, cylindernumber, torque) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, dieselEngine.getCapacity());
            statement.setInt(2, dieselEngine.getHorsePower());
            statement.setInt(3, dieselEngine.getCylinderNumber());
            statement.setInt(4, dieselEngine.getTorque());

            // Execute the SQL statement
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Motor pe diesel adaugat in baza de date.");
            } else {
                System.out.println("Motor pe diesel adaugat in baza de date.");
            }

            // Close the statement and connection
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPetrolEngineToDb(PetrolEngine petrolEngine) {
        try {
            Connection connection = DatabaseConfig.getInstance().getDatabaseConnection();

            // Prepare the SQL statement with parameters
            String sql = "INSERT INTO petrolengines (capacity, horsepower, cylindernumber, torque) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, petrolEngine.getCapacity());
            statement.setInt(2, petrolEngine.getHorsePower());
            statement.setInt(3, petrolEngine.getCylinderNumber());
            statement.setInt(4, petrolEngine.getTorque());

            // Execute the SQL statement
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Motor pe benzina adaugat in baza de date.");
            } else {
                System.out.println("Motor pe benzina adaugat in baza de date.");
            }

            // Close the statement and connection
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSedanFromDb(Sedan sedan) throws SQLException {
        Connection connection = DatabaseConfig.getInstance().getDatabaseConnection();
        PreparedStatement selectStatement = connection.prepareStatement(
                "SELECT id FROM sedans WHERE manufacturer = ? AND model = ? AND year = ? AND color = ? LIMIT 1"
        );
        selectStatement.setString(1, sedan.getManufacturer().toString());
        selectStatement.setString(2, sedan.getModel());
        selectStatement.setInt(3, sedan.getYear());
        selectStatement.setString(4, sedan.getColor().toString());
        ResultSet resultSet = selectStatement.executeQuery();

        if (resultSet.next()) {
            int sedanId = resultSet.getInt("id");

            PreparedStatement deleteStatement = connection.prepareStatement(
                    "DELETE FROM sedans WHERE id = ?"
            );
            deleteStatement.setInt(1, sedanId);
            deleteStatement.executeUpdate();
            deleteStatement.close();

            System.out.println("Sedanul selectat a fost sters din baza de date.");
        } else {
            System.out.println("Nu au fost gasite astfel de sedanuri.");
        }

        resultSet.close();
        selectStatement.close();
    }

    public void deleteSportbikeFromDb(Sportbike sportbike) throws SQLException {
        Connection connection = DatabaseConfig.getInstance().getDatabaseConnection();
        PreparedStatement selectStatement = connection.prepareStatement(
                "SELECT id FROM sportbikes WHERE manufacturer = ? AND model = ? AND year = ? AND color = ? AND maxSpeed = ? LIMIT 1"
        );
        selectStatement.setString(1, sportbike.getManufacturer().toString());
        selectStatement.setString(2, sportbike.getModel());
        selectStatement.setInt(3, sportbike.getYear());
        selectStatement.setString(4, sportbike.getColor().toString());
        selectStatement.setInt(5, sportbike.getMaxSpeed());
        ResultSet resultSet = selectStatement.executeQuery();

        if (resultSet.next()) {
            int sportbikeId = resultSet.getInt("id");

            PreparedStatement deleteStatement = connection.prepareStatement(
                    "DELETE FROM sportbikes WHERE id = ?"
            );
            deleteStatement.setInt(1, sportbikeId);
            deleteStatement.executeUpdate();
            deleteStatement.close();

            System.out.println("Sportbike-ul selectat a fost sters din baza de date.");
        } else {
            System.out.println("Nu au fost gasite astfel de sportbike-uri.");
        }

        resultSet.close();
        selectStatement.close();
    }

    public void deleteDieselEngineFromDb(DieselEngine dieselEngine) throws SQLException {
        Connection connection = DatabaseConfig.getInstance().getDatabaseConnection();
        PreparedStatement selectStatement = connection.prepareStatement(
                "SELECT id FROM dieselengines WHERE capacity = ? AND horsePower = ? AND cylinderNumber = ? AND torque = ? LIMIT 1"
        );
        selectStatement.setDouble(1, dieselEngine.getCapacity());
        selectStatement.setInt(2, dieselEngine.getHorsePower());
        selectStatement.setInt(3, dieselEngine.getCylinderNumber());
        selectStatement.setInt(4, dieselEngine.getTorque());
        ResultSet resultSet = selectStatement.executeQuery();

        if (resultSet.next()) {
            int dieselEngineId = resultSet.getInt("id");

            PreparedStatement deleteStatement = connection.prepareStatement(
                    "DELETE FROM dieselengines WHERE id = ?"
            );
            deleteStatement.setInt(1, dieselEngineId);
            deleteStatement.executeUpdate();
            deleteStatement.close();

            System.out.println("Motorul pe diesel selectat a fost sters din baza de date.");
        } else {
            System.out.println("Nu au fost gasite astfel de motoare.");
        }

        resultSet.close();
        selectStatement.close();
    }

    public void deletePetrolEngineFromDb(PetrolEngine petrolEngine) throws SQLException {
        Connection connection = DatabaseConfig.getInstance().getDatabaseConnection();
        PreparedStatement selectStatement = connection.prepareStatement(
                "SELECT id FROM petrolengines WHERE capacity = ? AND horsePower = ? AND cylinderNumber = ? AND torque = ? LIMIT 1"
        );
        selectStatement.setDouble(1, petrolEngine.getCapacity());
        selectStatement.setInt(2, petrolEngine.getHorsePower());
        selectStatement.setInt(3, petrolEngine.getCylinderNumber());
        selectStatement.setInt(4, petrolEngine.getTorque());
        ResultSet resultSet = selectStatement.executeQuery();

        if (resultSet.next()) {
            int petrolEngineId = resultSet.getInt("id");

            PreparedStatement deleteStatement = connection.prepareStatement(
                    "DELETE FROM petrolengines WHERE id = ?"
            );
            deleteStatement.setInt(1, petrolEngineId);
            deleteStatement.executeUpdate();
            deleteStatement.close();

            System.out.println("Motorul pe benzina selectat a fost sters din baza de date.");
        } else {
            System.out.println("Nu au fost gasite astfel de motoare.");
        }
        resultSet.close();
        selectStatement.close();
    }

    public void addPetrolEngineToSedanDb(PetrolEngine petrolEngine, Sedan car) throws SQLException {
        Connection connection = DatabaseConfig.getInstance().getDatabaseConnection();

        PreparedStatement selectEngineStatement = connection.prepareStatement(
                "SELECT id FROM petrolengines WHERE capacity = ? AND horsePower = ? AND cylinderNumber = ? AND torque = ?"
        );
        selectEngineStatement.setDouble(1, petrolEngine.getCapacity());
        selectEngineStatement.setInt(2, petrolEngine.getHorsePower());
        selectEngineStatement.setInt(3, petrolEngine.getCylinderNumber());
        selectEngineStatement.setInt(4, petrolEngine.getTorque());
        ResultSet engineResultSet = selectEngineStatement.executeQuery();

        int petrolEngineId = -1;
        if (engineResultSet.next()) {
            petrolEngineId = engineResultSet.getInt("id");
        }

        engineResultSet.close();
        selectEngineStatement.close();

        PreparedStatement selectSedanStatement = connection.prepareStatement(
                "SELECT id FROM sedans WHERE manufacturer = ? AND model = ? AND year = ? AND color = ?"
        );
        selectSedanStatement.setString(1, car.getManufacturer().name());
        selectSedanStatement.setString(2, car.getModel());
        selectSedanStatement.setInt(3, car.getYear());
        selectSedanStatement.setString(4, car.getColor().name());
        ResultSet sedanResultSet = selectSedanStatement.executeQuery();

        while (sedanResultSet.next()) {
            int sedanId = sedanResultSet.getInt("id");
            PreparedStatement updateStatement = connection.prepareStatement(
                    "UPDATE sedans SET petrolengine_id = ?, dieselengine_id = null WHERE id = ?"
            );
            updateStatement.setInt(1, petrolEngineId);
            updateStatement.setInt(2, sedanId);
            updateStatement.executeUpdate();
            updateStatement.close();

            System.out.println("Baza de date a fost actualizata.");
        }

        sedanResultSet.close();
        selectSedanStatement.close();
    }

    public void addDieselEngineToSedanDb(DieselEngine dieselEngine, Sedan car) throws SQLException {
        Connection connection = DatabaseConfig.getInstance().getDatabaseConnection();

        PreparedStatement selectEngineStatement = connection.prepareStatement(
                "SELECT id FROM dieselengines WHERE capacity = ? AND horsePower = ? AND cylinderNumber = ? AND torque = ?"
        );
        selectEngineStatement.setDouble(1, dieselEngine.getCapacity());
        selectEngineStatement.setInt(2, dieselEngine.getHorsePower());
        selectEngineStatement.setInt(3, dieselEngine.getCylinderNumber());
        selectEngineStatement.setInt(4, dieselEngine.getTorque());
        ResultSet engineResultSet = selectEngineStatement.executeQuery();

        int dieselEngineId = -1;
        if (engineResultSet.next()) {
            dieselEngineId = engineResultSet.getInt("id");
        }

        engineResultSet.close();
        selectEngineStatement.close();

        PreparedStatement selectSedanStatement = connection.prepareStatement(
                "SELECT id FROM sedans WHERE manufacturer = ? AND model = ? AND year = ? AND color = ?"
        );
        selectSedanStatement.setString(1, car.getManufacturer().name());
        selectSedanStatement.setString(2, car.getModel());
        selectSedanStatement.setInt(3, car.getYear());
        selectSedanStatement.setString(4, car.getColor().name());
        ResultSet sedanResultSet = selectSedanStatement.executeQuery();

        while (sedanResultSet.next()) {
            int sedanId = sedanResultSet.getInt("id");
            PreparedStatement updateStatement = connection.prepareStatement(
                    "UPDATE sedans SET dieselengine_id = ?, petrolengine_id = null WHERE id = ?"
            );
            updateStatement.setInt(1, dieselEngineId);
            updateStatement.setInt(2, sedanId);
            updateStatement.executeUpdate();
            updateStatement.close();

            System.out.println("Baza de date a fost actualizata.");
        }

        sedanResultSet.close();
        selectSedanStatement.close();
    }

    public void addPetrolEngineToSportbikeDb(PetrolEngine petrolEngine, Sportbike sportbike) throws SQLException {
        Connection connection = DatabaseConfig.getInstance().getDatabaseConnection();

        PreparedStatement selectEngineStatement = connection.prepareStatement(
                "SELECT id FROM petrolengines WHERE capacity = ? AND horsePower = ? AND cylinderNumber = ? AND torque = ?"
        );
        selectEngineStatement.setDouble(1, petrolEngine.getCapacity());
        selectEngineStatement.setInt(2, petrolEngine.getHorsePower());
        selectEngineStatement.setInt(3, petrolEngine.getCylinderNumber());
        selectEngineStatement.setInt(4, petrolEngine.getTorque());
        ResultSet engineResultSet = selectEngineStatement.executeQuery();

        int petrolEngineId = -1;
        if (engineResultSet.next()) {
            petrolEngineId = engineResultSet.getInt("id");
        }

        engineResultSet.close();
        selectEngineStatement.close();

        PreparedStatement selectSportbikeStatement = connection.prepareStatement(
                "SELECT id FROM sportbikes WHERE manufacturer = ? AND model = ? AND year = ? AND color = ? AND maxSpeed = ?"
        );
        selectSportbikeStatement.setString(1, sportbike.getManufacturer().name());
        selectSportbikeStatement.setString(2, sportbike.getModel());
        selectSportbikeStatement.setInt(3, sportbike.getYear());
        selectSportbikeStatement.setString(4, sportbike.getColor().name());
        selectSportbikeStatement.setInt(5, sportbike.getMaxSpeed());
        ResultSet sportbikeResultSet = selectSportbikeStatement.executeQuery();

        while (sportbikeResultSet.next()) {
            int sportbikeId = sportbikeResultSet.getInt("id");
            PreparedStatement updateStatement = connection.prepareStatement(
                    "UPDATE sportbikes SET petrolengine_id = ? WHERE id = ?"
            );
            updateStatement.setInt(1, petrolEngineId);
            updateStatement.setInt(2, sportbikeId);
            updateStatement.executeUpdate();
            updateStatement.close();

            System.out.println("Baza de date a fost actualizata.");
        }

        sportbikeResultSet.close();
        selectSportbikeStatement.close();
    }









}

