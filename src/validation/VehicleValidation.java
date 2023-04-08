package validation;

public class VehicleValidation {
    public static boolean validateYear(int year){
        return year >= 1970 && year <= 2023;
    }

    public static boolean validateCapacity(int seatCapacity){
        return seatCapacity != 3 && seatCapacity >= 2 && seatCapacity <= 5;
    }

    public static boolean validateMaxSpeed(int speed){
        return speed >= 100 && speed <= 350;
    }

    public static boolean validateWheelCount(int wheels){
        return wheels == 2 || wheels == 3;
    }

    public static boolean validateHorsePower(int hp){
        return hp >= 50 && hp <= 1000;
    }

    public static boolean validateEngineCapacity(double capacity){
        return capacity >= 0.8 && capacity <= 5.0;
    }

    public static boolean validateCylinders(int cylinders)
    {
        return cylinders >= 3 && cylinders <= 12;
    }

    public static boolean validateTorque(int torque)
    {
        return torque >= 100 && torque <= 2000;
    }
}
