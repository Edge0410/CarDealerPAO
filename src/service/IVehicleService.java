package service;

import model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IVehicleService{
    void addCar(Car car);
    void addMotorcycle(Motorcycle mtr);
    void addPetrolEngine(PetrolEngine eng);
    void addDieselEngine(DieselEngine eng);
    void getCars();
    void getMotorcycles();
    int getDieselEngines();
    int getPetrolEngines();
    void removeDieselEngine(DieselEngine eng) throws SQLException;
    void removePetrolEngine(PetrolEngine eng) throws SQLException;
    void addEngineToCar(int carIndex, int engineIndex, boolean diesel) throws SQLException;
    void addEngineToMotorcycle(int carIndex, int engineIndex) throws SQLException;
    void removeCar(int carIndex) throws SQLException;
    void removeMotorcycle(int carIndex) throws SQLException;
    void loadSedansFromCSV(String filePath) throws IOException;
    void loadSportbikesFromCSV(String filePath) throws IOException;
    void loadPetrolEnginesFromCSV(String filePath) throws IOException;
    void loadDieselEnginesFromCSV(String filePath) throws IOException;
}
