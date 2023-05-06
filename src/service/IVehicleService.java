package service;

import model.*;

import java.io.IOException;
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
    void removeDieselEngine(DieselEngine eng);
    void removePetrolEngine(PetrolEngine eng);
    void addEngineToCar(int carIndex, int engineIndex, boolean diesel);
    void addEngineToMotorcycle(int carIndex, int engineIndex);
    void removeCar(int carIndex);
    void removeMotorcycle(int carIndex);
    void loadSedansFromCSV(String filePath) throws IOException;
    void loadSportbikesFromCSV(String filePath) throws IOException;
    void loadPetrolEnginesFromCSV(String filePath) throws IOException;
    void loadDieselEnginesFromCSV(String filePath) throws IOException;
}
