package model;

import constants.Colors;
import constants.MotorcycleManufacturers;
import constants.MotorcycleSuspensions;

public class Dirtbike extends Motorcycle{
    private String VIN;
    private MotorcycleSuspensions suspensionKit;
    public Dirtbike(MotorcycleManufacturers manufacturer, String model, int year, Colors color) {
        super(manufacturer, model, year, color, 2, 200);
        this.suspensionKit = MotorcycleSuspensions.NONE;
        generateNewVIN();
    }

    public Dirtbike(MotorcycleManufacturers manufacturer, String model, int year, Colors color, MotorcycleSuspensions suspensionKit) {
        super(manufacturer, model, year, color, 2, 200);
        this.suspensionKit = suspensionKit;
        generateNewVIN();
    }

    public Dirtbike(MotorcycleManufacturers manufacturer, String model, int year, Colors color, PetrolEngine engine) {
        super(manufacturer, model, year, color, 2, 200, engine);
        this.suspensionKit = MotorcycleSuspensions.NONE;
        generateNewVIN();
    }

    public Dirtbike(MotorcycleManufacturers manufacturer, String model, int year, Colors color, PetrolEngine engine, MotorcycleSuspensions suspensionKit) {
        super(manufacturer, model, year, color, 2, 200, engine);
        this.suspensionKit = suspensionKit;
        generateNewVIN();
    }

    public void generateNewVIN(){
        this.VIN = "sasiu_dirtbike_1234";
    }

    public String getVIN() {
        return VIN;
    }

    public MotorcycleSuspensions getSuspensionKit() {
        return suspensionKit;
    }

    public void setSuspensionKit(MotorcycleSuspensions suspensionKit) {
        this.suspensionKit = suspensionKit;
    }
}
