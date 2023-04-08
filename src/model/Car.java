package model;

import constants.CarManufacturers;
import constants.Colors;
import exception.NoEngineException;

public abstract class Car implements Vehicle {
    private final CarManufacturers manufacturer;
    private final String model;
    private final int year;
    private Colors color;
    private final int seatCapacity;
    private Engine engine;

    public Car(CarManufacturers manufacturer, String model, int year, Colors color, int seatCapacity) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.color = color;
        this.seatCapacity = seatCapacity;
    }

    public Car(CarManufacturers manufacturer, String model, int year, Colors color, int seatCapacity, Engine engine) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.color = color;
        this.seatCapacity = seatCapacity;
        this.engine = engine;
    }
    // from vehicle
    public abstract void generateNewVIN();
    public abstract String getVIN();

    public CarManufacturers getManufacturer() {
        return manufacturer;
    }

    public Colors getColor() {
        return color;
    }

    public int getYear() {
        return year;
    }

    public String getModel() {
        return model;
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String smallDetails(){
        if(this.getEngine() != null)
            return this.getManufacturer().name() + " " +  this.getYear() + " " + this.getColor() + " Motor: " + this.getEngine().smallDetails();
        else
            return this.getManufacturer().name() + " " +  this.getYear() + " " + this.getColor() + " Motor: FARA - doar caroserie";
    }
}
