package model;

public abstract class Engine {
    private final double capacity;
    private final int horsePower;
    private final int cylinderNumber;
    private final int torque; //in Newton * meters

    public Engine(double capacity, int horsePower, int cylinderNumber, int torque) {
        this.horsePower = horsePower;
        this.cylinderNumber = cylinderNumber;
        this.torque = torque;
        this.capacity = capacity;
    }

    public int getCylinderNumber() {
        return cylinderNumber;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public int getTorque() {
        return torque;
    }

    public double getCapacity() {
        return capacity;
    }

    public abstract String fabricationDetails();

    public abstract String smallDetails();
}
