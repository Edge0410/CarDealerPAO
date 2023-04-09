package service;

import model.*;

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
}
