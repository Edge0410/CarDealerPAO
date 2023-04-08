package model;

import constants.CarManufacturers;
import constants.Colors;

public class Hatchback extends Car{
    private String VIN;
    public Hatchback(CarManufacturers manufacturer, String model, int year, Colors color, int seatCapacity) {
        super(manufacturer, model, year, color, seatCapacity);
        generateNewVIN();
    }

    public Hatchback(CarManufacturers manufacturer, String model, int year, Colors color, int seatCapacity, Engine engine) {
        super(manufacturer, model, year, color, seatCapacity, engine);
        generateNewVIN();
    }

    public void generateNewVIN(){
        this.VIN = "sasiu_hatchback_1234";
    }

    public String getVIN() {
        return VIN;
    }
}
