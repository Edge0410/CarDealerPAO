package model;

import constants.Colors;
import constants.MotorcycleManufacturers;

public class Cruiser extends Motorcycle{
    private String VIN;
    public Cruiser(MotorcycleManufacturers manufacturer, String model, int year, Colors color, int wheelCount) {
        super(manufacturer, model, year, color, wheelCount, 200);
        generateNewVIN();
    }

    public Cruiser(MotorcycleManufacturers manufacturer, String model, int year, Colors color, int wheelCount, PetrolEngine engine) {
        super(manufacturer, model, year, color, wheelCount, 200, engine);
        generateNewVIN();
    }

    public void generateNewVIN(){
        this.VIN = "sasiu_cruiser_1234";
    }

    public String getVIN() {
        return VIN;
    }


}
