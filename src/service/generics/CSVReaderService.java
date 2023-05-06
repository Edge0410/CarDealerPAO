package service.generics;

import constants.CarManufacturers;
import constants.Colors;
import constants.MotorcycleManufacturers;
import model.DieselEngine;
import model.PetrolEngine;
import model.Sedan;
import model.Sportbike;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderService implements ICSVReaderService {
    private static final CSVReaderService INSTANCE = new CSVReaderService();
    private CSVReaderService() {}
    public static CSVReaderService getInstance() {
        return INSTANCE;
    }

    public List<Sedan> readSedansFromCSV(String filePath) throws IOException {
        List<Sedan> sedans = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 4) {
                CarManufacturers manufacturer = CarManufacturers.valueOf(parts[0]);
                String model = parts[1];
                int year = Integer.parseInt(parts[2]);
                Colors color = Colors.valueOf(parts[3]);
                sedans.add(new Sedan(manufacturer, model, year, color));
            }
        }
        reader.close();
        return sedans;
    }

    public List<Sportbike> readSportbikesFromCSV(String filePath) throws IOException {
        List<Sportbike> sportbikes = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 5) {
                MotorcycleManufacturers manufacturer = MotorcycleManufacturers.valueOf(parts[0]);
                String model = parts[1];
                int year = Integer.parseInt(parts[2]);
                Colors color = Colors.valueOf(parts[3]);
                int maxSpeed = Integer.parseInt(parts[4]);
                sportbikes.add(new Sportbike(manufacturer, model, year, color, maxSpeed));
            }
        }
        reader.close();
        return sportbikes;
    }

    public List<DieselEngine> readDieselEnginesFromCSV(String filePath) throws IOException {
        List<DieselEngine> engines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 4) {
                double capacity = Double.parseDouble(parts[0]);
                int horsePower = Integer.parseInt(parts[1]);
                int cylinderNumber = Integer.parseInt(parts[2]);
                int torque = Integer.parseInt(parts[3]);
                engines.add(new DieselEngine(capacity, horsePower, cylinderNumber, torque));
            }
        }
        reader.close();
        return engines;
    }

    public List<PetrolEngine> readPetrolEnginesFromCSV(String filePath) throws IOException {
        List<PetrolEngine> petrolEngines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 4) {
                double capacity = Double.parseDouble(parts[0]);
                int horsePower = Integer.parseInt(parts[1]);
                int cylinderNumber = Integer.parseInt(parts[2]);
                int torque = Integer.parseInt(parts[3]);
                petrolEngines.add(new PetrolEngine(capacity, horsePower, cylinderNumber, torque));
            }
        }
        reader.close();
        return petrolEngines;
    }


}
