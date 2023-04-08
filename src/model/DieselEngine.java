package model;
import constants.EngineDetails;

public class DieselEngine extends Engine{
    public DieselEngine(double capacity, int horsePower, int cylinderNumber, int torque) {
        super(capacity, horsePower, cylinderNumber, torque);
    }

    @Override
    public String fabricationDetails() {
        return "Caracteristicile acestui motor sunt urmatoarele: \n"
                + "Capacitate motor (litri):" + this.getCapacity() + "\n"
                + "Cai putere: " + this.getHorsePower() + "\n"
                + "Cuplu (Newton * metru): " + this.getTorque() + "\n"
                + "Numar cilindri: " + this.getCylinderNumber() + "\n\n"
                + EngineDetails.DIESEL_ENGINE_ADNOTATION + "\n";
    }

    @Override
    public String smallDetails(){
        return this.getCapacity() + " TDI/TSI " + this.getHorsePower() + " CP " + this.getTorque() + " Nm " + this.getCylinderNumber() + " Cilindri";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof DieselEngine)) {
            return false;
        }

        DieselEngine engine = (DieselEngine) obj;
        return Double.compare(engine.getCapacity(), this.getCapacity()) == 0
                && engine.getHorsePower() == this.getHorsePower()
                && engine.getCylinderNumber() == this.getCylinderNumber()
                && engine.getTorque() == this.getTorque();
    }
}
