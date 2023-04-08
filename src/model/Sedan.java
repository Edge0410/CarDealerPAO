package model;

import constants.CarManufacturers;
import constants.Colors;

public class Sedan extends Car {
    private String VIN;
    public Sedan(CarManufacturers manufacturer, String model, int year, Colors color) {
        super(manufacturer, model, year, color, 5);
        generateNewVIN();
    }

    public Sedan(CarManufacturers manufacturer, String model, int year, Colors color, Engine engine) {
        super(manufacturer, model, year, color, 5, engine);
        generateNewVIN();
    }

    public void generateNewVIN(){
        this.VIN = "sasiu_sedan_1234";
    }

    public String getVIN() {
        return VIN;
    }
}
