package service;

import exception.*;
import model.*;

import java.util.*;

import static validation.VehicleValidation.*;

public class VehicleService implements IVehicleService{
    private List<Car> cars = new ArrayList<>();
    private List<Motorcycle> motorcycles = new ArrayList<>();
    private Map<Double, List<DieselEngine>> dieselEngines = new TreeMap<>();
    private Map<Double, List<PetrolEngine>> petrolEngines = new TreeMap<>();

    public void addCar(Car car){
        if(!validateYear(car.getYear()))
            throw new InvalidYearException("Anul masinii este invalid!");
        if(!validateCapacity(car.getSeatCapacity()))
            throw new InvalidSeatCountException("Numarul de locuri este invalid!");
        cars.add(car);
        AuditService.getInstance().write("Masina " + car.getManufacturer() + " " + car.getModel() + " a fost adaugata");
        System.out.println("Masina a fost adaugata cu succes!");
    }

    public void addMotorcycle(Motorcycle mtr){
        if(!validateYear(mtr.getYear()))
            throw new InvalidYearException("Anul motocicletei este invalid!");
        if(!validateMaxSpeed(mtr.getMaxSpeed()))
            throw new InvalidMaxSpeedException("Viteza maxima a motocicletei este invalida!");
        if(!validateWheelCount(mtr.getWheelCount()))
            throw new InvalidWheelCountException("Numarul de pneuri ale motocicletei este invalid!");
        motorcycles.add(mtr);
        AuditService.getInstance().write("Motocicleta " + mtr.getManufacturer() + " " + mtr.getModel() + " a fost adaugata");
        System.out.println("Motocicleta a fost adaugata cu succes!\n");
    }

    public void addPetrolEngine(PetrolEngine eng){ // false is petrol, true is diesel engine
        if(!validateCylinders(eng.getCylinderNumber()))
            throw new InvalidEngineSpecsException("Numarul de cilindrii este invalid!");
        if(!validateHorsePower(eng.getHorsePower()))
            throw new InvalidEngineSpecsException("Numarul de cai putere este invalid!");
        if(!validateEngineCapacity(eng.getCapacity()))
            throw new InvalidEngineSpecsException("Volumul motorului este invalid!");
        if(!validateTorque(eng.getTorque()))
            throw new InvalidEngineSpecsException("Cuplul motorului este invalid!");
        petrolEngines.putIfAbsent(eng.getCapacity(), new ArrayList<PetrolEngine>());
        petrolEngines.get(eng.getCapacity()).add(eng);
        AuditService.getInstance().write("Motorul " + eng.getCapacity() + " " + eng.getHorsePower() + " a fost adaugat");
        System.out.println("Motorul a fost adaugat cu succes!\n");
    }

    public void addDieselEngine(DieselEngine eng){ // false is petrol, true is diesel engine
        if(!validateCylinders(eng.getCylinderNumber()))
            throw new InvalidEngineSpecsException("Numarul de cilindrii este invalid!");
        if(!validateHorsePower(eng.getHorsePower()))
            throw new InvalidEngineSpecsException("Numarul de cai putere este invalid!");
        if(!validateEngineCapacity(eng.getCapacity()))
            throw new InvalidEngineSpecsException("Volumul motorului este invalid!");
        if(!validateTorque(eng.getTorque()))
            throw new InvalidEngineSpecsException("Cuplul motorului este invalid!");
        dieselEngines.putIfAbsent(eng.getCapacity(), new ArrayList<DieselEngine>());
        dieselEngines.get(eng.getCapacity()).add(eng);
        AuditService.getInstance().write("Motorul " + eng.getCapacity() + " " + eng.getHorsePower() + " a fost adaugat");
        System.out.println("Motorul a fost adaugat cu succes!\n");
    }

    public void getCars() {
        int count = 1;
        for(Car car : cars)
        {
            System.out.println(count + ". " + car.smallDetails());
            count++;
        }
        if(cars.isEmpty())
            System.out.println("Nu au fost adaugate masini!\n");
    }

    public void getMotorcycles(){
        int count = 1;
        for(Motorcycle car : motorcycles)
        {
            System.out.println(count + ". " + car.smallDetails());
            count++;
        }
        if(motorcycles.isEmpty())
            System.out.println("Nu au fost adaugate motociclete!\n");
    }

    public int getDieselEngines() {
        int count = 0;
        for (List<DieselEngine> engines : dieselEngines.values()) {
            for(DieselEngine eng : engines){
                count++;
                System.out.println(count + ". " + eng.smallDetails());
            }
        }
        return count;
    }

    public int getPetrolEngines() {
        int count = 0;
        for (List<PetrolEngine> engines : petrolEngines.values()) {
            for(PetrolEngine eng : engines){
                count++;
                System.out.println(count + ". " + eng.smallDetails());
            }
        }

        return count;
    }

    public void removeDieselEngine(DieselEngine eng) {
        List<DieselEngine> engines = dieselEngines.get(eng.getCapacity());
        if (engines != null) {
            for (Iterator<DieselEngine> it = engines.iterator(); it.hasNext();) {
                DieselEngine engine = it.next();
                if (engine.equals(eng)) {
                    AuditService.getInstance().write("Motorul " + eng.getCapacity() + " " + eng.getHorsePower() + " a fost sters");
                    it.remove();
                    System.out.println("Motorul a fost eliminat cu succes!\n");
                    return;
                }
            }
        }
        System.out.println("Motorul nu a fost gasit!\n");
    }

    public void removePetrolEngine(PetrolEngine eng) {
        List<PetrolEngine> engines = petrolEngines.get(eng.getCapacity());
        if (engines != null) {
            for (Iterator<PetrolEngine> it = engines.iterator(); it.hasNext();) {
                PetrolEngine engine = it.next();
                if (engine.equals(eng)) {
                    AuditService.getInstance().write("Motorul " + eng.getCapacity() + " " + eng.getHorsePower() + " a fost sters");
                    it.remove();
                    System.out.println("Motorul a fost eliminat cu succes!\n");
                    return;
                }
            }
        }
        System.out.println("Motorul nu a fost gasit!\n");
    }

    public void addEngineToCar(int carIndex, int engineIndex, boolean diesel) {
        Car car = cars.get(carIndex - 1);

        if(!diesel) {
            int count = 0;
            PetrolEngine engine = null;
            for (List<PetrolEngine> engines : petrolEngines.values()) {
                for(PetrolEngine eng : engines){
                    count++;
                    if(count == engineIndex) {
                        engine = eng;
                        break;
                    }
                }
            }
            car.setEngine(engine);
            AuditService.getInstance().write("Motorul " + engine.getCapacity() +" a fost adaugat la masina " + car.getModel());
            System.out.println("Motorul a fost adaugat pe masina!");
        }
        else
        {
            int count = 0;
            DieselEngine engine = null;
            for (List<DieselEngine> engines : dieselEngines.values()) {
                for(DieselEngine eng : engines){
                    count++;
                    if(count == engineIndex){
                        engine = eng;
                        break;
                    }
                }
            }
            car.setEngine(engine);
            AuditService.getInstance().write("Motorul " + engine.getCapacity() +" a fost adaugat la masina " + car.getModel());
            System.out.println("Motorul a fost adaugat pe masina!");
        }
    }

    public void addEngineToMotorcycle(int carIndex, int engineIndex) {
        Motorcycle car = motorcycles.get(carIndex - 1);

        int count = 0;
        PetrolEngine engine = null;
        for (List<PetrolEngine> engines : petrolEngines.values()) {
            for(PetrolEngine eng : engines){
                count++;
                if(count == engineIndex) {
                    engine = eng;
                    break;
                }
            }
        }
        car.setEngine(engine);
        AuditService.getInstance().write("Motorul " + engine.getCapacity() +" a fost adaugat la motocicleta " + car.getModel());
        System.out.println("Motorul a fost adaugat pe motocicleta!");
    }

    public void removeCar(int carIndex){
        int count = 0;
        for(Car car : cars){
            count++;
            if(count == carIndex) {
                AuditService.getInstance().write("Masina " + car.getModel() + " a fost stearsa");
                cars.remove(car);
                System.out.println("Masina a fost eliminata cu succes!");
                return ;
            }
        }
        throw new ArrayIndexOutOfBoundsException("");
    }

    public void removeMotorcycle(int carIndex){
        int count = 0;
        for(Motorcycle car : motorcycles){
            count++;
            if(count == carIndex) {
                AuditService.getInstance().write("Motocicleta " + car.getModel() + " a fost stearsa");
                motorcycles.remove(car);
                System.out.println("Motocicleta a fost eliminata cu succes!");
                return ;
            }
        }
        throw new ArrayIndexOutOfBoundsException("");
    }
}
