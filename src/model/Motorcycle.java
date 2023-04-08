package model;

import constants.CarManufacturers;
import constants.Colors;
import constants.MotorcycleManufacturers;
import constants.MotorcycleSuspensions;
import exception.NoEngineException;

public abstract class Motorcycle implements Vehicle{
    private final MotorcycleManufacturers manufacturer;
    private final String model;
    private final int year;
    private Colors color;
    private final int wheelCount;
    private int maxSpeed;
    private PetrolEngine engine;

    public Motorcycle(MotorcycleManufacturers manufacturer, String model, int year, Colors color, int wheelCount, int maxSpeed) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.color = color;
        this.wheelCount = wheelCount;
        this.maxSpeed = maxSpeed;
    }

    public Motorcycle(MotorcycleManufacturers manufacturer, String model, int year, Colors color, int wheelCount, int maxSpeed, PetrolEngine engine) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.color = color;
        this.wheelCount = wheelCount;
        this.engine = engine;
        this.maxSpeed = maxSpeed;
    }

    public abstract void generateNewVIN();
    public abstract String getVIN();

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public MotorcycleManufacturers getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public Colors getColor() {
        return color;
    }

    public int getYear() {
        return year;
    }

    public int getWheelCount() {
        return wheelCount;
    }

    public PetrolEngine getEngine() {
        return engine;
    }

    public void setEngine(PetrolEngine engine) {
        this.engine = engine;
    }

    public String smallDetails(){
        if(this.getEngine() != null)
            return this.getManufacturer().name() + " " +  this.getYear() + " " + this.getColor() + " Motor: " + this.getEngine().smallDetails();
        else
            return this.getManufacturer().name() + " " +  this.getYear() + " " + this.getColor() + " Motor: FARA - doar caroserie";
    }
}
