package service;

import config.DatabaseConfig;
import exception.*;
import model.*;
import service.generics.CSVReaderService;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import static validation.VehicleValidation.*;

public class VehicleService implements IVehicleService{
    private static VehicleService instance;
    private static List<Car> cars = new ArrayList<>();
    private static List<Motorcycle> motorcycles = new ArrayList<>();
    private static Map<Double, List<DieselEngine>> dieselEngines = new TreeMap<>();
    private static Map<Double, List<PetrolEngine>> petrolEngines = new TreeMap<>();

    private VehicleService(){

    }

    public static VehicleService getInstance(){
        if (instance == null)
            instance = new VehicleService();

        return instance;
    }

    public void loadSedansFromCSV(String filePath) throws IOException {
        List<Sedan> sedans = CSVReaderService.getInstance().readSedansFromCSV(filePath);
        cars.addAll(sedans);
    }

    public void loadSportbikesFromCSV(String filePath) throws IOException{
        List<Sportbike> sportbikes = CSVReaderService.getInstance().readSportbikesFromCSV(filePath);
        motorcycles.addAll(sportbikes);
    }

    public void loadPetrolEnginesFromCSV(String filePath) throws IOException {
        List<PetrolEngine> petrolEngines = CSVReaderService.getInstance().readPetrolEnginesFromCSV(filePath);
        for (PetrolEngine engine : petrolEngines) {
            if (this.petrolEngines.containsKey(engine.getCapacity())) {
                List<PetrolEngine> list = this.petrolEngines.get(engine.getCapacity());
                list.add(engine);
                this.petrolEngines.put(engine.getCapacity(), list);
            } else {
                List<PetrolEngine> list = new ArrayList<>();
                list.add(engine);
                this.petrolEngines.put(engine.getCapacity(), list);
            }
        }
    }

    public void loadDieselEnginesFromCSV(String filePath) throws IOException {
        List<DieselEngine> dieselEngines = CSVReaderService.getInstance().readDieselEnginesFromCSV(filePath);
        for (DieselEngine engine : dieselEngines) {
            if (this.dieselEngines.containsKey(engine.getCapacity())) {
                List<DieselEngine> list = this.dieselEngines.get(engine.getCapacity());
                list.add(engine);
                this.dieselEngines.put(engine.getCapacity(), list);
            } else {
                List<DieselEngine> list = new ArrayList<>();
                list.add(engine);
                this.dieselEngines.put(engine.getCapacity(), list);
            }
        }
    }

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

    public void removeDieselEngine(DieselEngine eng) throws SQLException {
        List<DieselEngine> engines = dieselEngines.get(eng.getCapacity());
        if (engines != null) {
            for (Iterator<DieselEngine> it = engines.iterator(); it.hasNext();) {
                DieselEngine engine = it.next();
                if (engine.equals(eng)) {
                    AuditService.getInstance().write("Motorul " + eng.getCapacity() + " " + eng.getHorsePower() + " a fost sters");
                    it.remove();
                    DatabaseService.getInstance().deleteDieselEngineFromDb(eng);
                    System.out.println("Motorul a fost eliminat cu succes!\n");
                    return;
                }
            }
        }
        System.out.println("Motorul nu a fost gasit!\n");
    }

    public void removePetrolEngine(PetrolEngine eng) throws SQLException {
        List<PetrolEngine> engines = petrolEngines.get(eng.getCapacity());
        if (engines != null) {
            for (Iterator<PetrolEngine> it = engines.iterator(); it.hasNext();) {
                PetrolEngine engine = it.next();
                if (engine.equals(eng)) {
                    AuditService.getInstance().write("Motorul " + eng.getCapacity() + " " + eng.getHorsePower() + " a fost sters");
                    it.remove();
                    DatabaseService.getInstance().deletePetrolEngineFromDb(eng);
                    System.out.println("Motorul a fost eliminat cu succes!\n");
                    return;
                }
            }
        }
        System.out.println("Motorul nu a fost gasit!\n");
    }

    public void addEngineToCar(int carIndex, int engineIndex, boolean diesel) {
        if(carIndex == -1)
        {
            carIndex = cars.size();
        }
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
        if(carIndex == -1)
        {
            carIndex = motorcycles.size();
        }
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

    public void removeCar(int carIndex) throws SQLException {
        int count = 0;
        for(Car car : cars){
            count++;
            if(count == carIndex) {
                AuditService.getInstance().write("Masina " + car.getModel() + " a fost stearsa");
                Car copycar = car;
                cars.remove(car);

                // in cazul in care este sedan - vreau sa sterg si din baza de date
                if(copycar instanceof Sedan)
                {
                    DatabaseService.getInstance().deleteSedanFromDb((Sedan) copycar);
                }

                System.out.println("Masina a fost eliminata cu succes!");
                return ;
            }
        }
        throw new ArrayIndexOutOfBoundsException("");
    }

    public void removeMotorcycle(int carIndex) throws SQLException {
        int count = 0;
        for(Motorcycle car : motorcycles){
            count++;
            if(count == carIndex) {
                AuditService.getInstance().write("Motocicleta " + car.getModel() + " a fost stearsa");
                Motorcycle copycar = car;
                motorcycles.remove(car);
                // in cazul in care este sedan - vreau sa sterg si din baza de date
                if(copycar instanceof Sportbike)
                {
                    DatabaseService.getInstance().deleteSportbikeFromDb((Sportbike)copycar);
                }
                System.out.println("Motocicleta a fost eliminata cu succes!");
                return ;
            }
        }
        throw new ArrayIndexOutOfBoundsException("");
    }

    public int findDieselEngineById(int engineId) throws SQLException {

        Connection connection = DatabaseConfig.getInstance().getDatabaseConnection();
        Statement statement = connection.createStatement();

        String sql = "SELECT * FROM bdpao.dieselengines where id = " + engineId;
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            double capacity = resultSet.getDouble("capacity");
            int horsePower = resultSet.getInt("horsepower");
            int cylinderNumber = resultSet.getInt("cylindernumber");
            int torque = resultSet.getInt("torque");

            int count = 0;
            for (List<DieselEngine> engines : dieselEngines.values()) {
                for(DieselEngine eng : engines){
                    count++;
                    if(capacity == eng.getCapacity() && horsePower == eng.getHorsePower() && cylinderNumber == eng.getCylinderNumber() && torque == eng.getTorque()){ // daca motorul coincide cu cel ales
                        resultSet.close();
                        statement.close();
                        return count;
                    }
                }
            }
        }
        resultSet.close();
        statement.close();
        return -1;
    }

    public int findPetrolEngineById(int engineId) throws SQLException {

        Connection connection = DatabaseConfig.getInstance().getDatabaseConnection();
        Statement statement = connection.createStatement();

        String sql = "SELECT * FROM bdpao.petrolengines where id = " + engineId;
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            double capacity = resultSet.getDouble("capacity");
            int horsePower = resultSet.getInt("horsepower");
            int cylinderNumber = resultSet.getInt("cylindernumber");
            int torque = resultSet.getInt("torque");

            int count = 0;
            for (List<PetrolEngine> engines : petrolEngines.values()) {
                for(PetrolEngine eng : engines){
                    count++;
                    if(capacity == eng.getCapacity() && horsePower == eng.getHorsePower() && cylinderNumber == eng.getCylinderNumber() && torque == eng.getTorque()){ // daca motorul coincide cu cel ales
                        resultSet.close();
                        statement.close();
                        return count;
                    }
                }
            }
        }
        resultSet.close();
        statement.close();
        return -1;
    }

    public int getEngineIdFromDatabase(Car sedan) throws SQLException {
        int engineId = -1; // Default value if engine not found
        Connection connection = DatabaseConfig.getInstance().getDatabaseConnection();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT id FROM engines WHERE capacity = ? AND horsePower = ? AND cylinderNumber = ? AND torque = ?"
        );
        statement.setDouble(1, sedan.getEngine().getCapacity());
        statement.setInt(2, sedan.getEngine().getHorsePower());
        statement.setInt(3, sedan.getEngine().getCylinderNumber());
        statement.setInt(4, sedan.getEngine().getTorque());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            engineId = resultSet.getInt("id");
        }

        statement.close();
        return engineId;
    }
}
