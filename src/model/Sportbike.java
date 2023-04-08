package model;

import constants.Colors;
import constants.MotorcycleManufacturers;

public class Sportbike extends Motorcycle{
    private String VIN;
    private int maxSpeed;
    public Sportbike(MotorcycleManufacturers manufacturer, String model, int year, Colors color, int maxSpeed) {
        super(manufacturer, model, year, color, 2, maxSpeed);
        this.maxSpeed = maxSpeed;
        generateNewVIN();
    }

    public Sportbike(MotorcycleManufacturers manufacturer, String model, int year, Colors color, PetrolEngine engine, int maxSpeed) {
        super(manufacturer, model, year, color, 2, maxSpeed, engine);
        this.maxSpeed = maxSpeed;
        generateNewVIN();
    }

    public void generateNewVIN(){
        this.VIN = "sasiu_sportbike_1234";
    }

    public String getVIN() {
        return VIN;
    }



}
