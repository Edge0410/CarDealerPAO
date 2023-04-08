package model;

import constants.CarManufacturers;
import constants.Colors;

public class Suv extends Car{
    private final boolean fourWheelDrive;
    private String VIN;
    public Suv(CarManufacturers manufacturer, String model, int year, Colors color, boolean fourWheelDrive) {
        super(manufacturer, model, year, color, 5);
        this.fourWheelDrive = fourWheelDrive;
        generateNewVIN();
    }

    public Suv(CarManufacturers manufacturer, String model, int year, Colors color, int seatCapacity, boolean fourWheelDrive, Engine engine) {
        super(manufacturer, model, year, color, 5, engine);
        this.fourWheelDrive = fourWheelDrive;
        generateNewVIN();
    }

    public void generateNewVIN(){
        this.VIN = "sasiu_suv_1234";
    }

    public String getVIN() {
        return VIN;
    }






}
