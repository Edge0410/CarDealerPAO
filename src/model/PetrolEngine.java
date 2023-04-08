package model;
import constants.EngineDetails;

public class PetrolEngine extends Engine{
    public PetrolEngine(double capacity, int horsePower, int cylinderNumber, int torque) {
        super(capacity, horsePower, cylinderNumber, torque);
    }

    @Override
    public String fabricationDetails() {
        return "Caracteristicile acestui motor sunt urmatoarele: \n"
                + "Capacitate motor (litri):" + this.getCapacity() + "\n"
                + "Cai putere: " + this.getHorsePower() + "\n"
                + "Cuplu (Newton * metru): " + this.getTorque() + "\n"
                + "Numar cilindri: " + this.getCylinderNumber() + "\n\n"
                + EngineDetails.PETROL_ENGINE_ADNOTATION + "\n";
    }

    @Override
    public String smallDetails(){
        return this.getCapacity() + " MPI " + this.getHorsePower() + " CP " + this.getTorque() + " Nm " + this.getCylinderNumber() + " Cilindri";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof PetrolEngine)) {
            return false;
        }

        PetrolEngine engine = (PetrolEngine) obj;
        return Double.compare(engine.getCapacity(), this.getCapacity()) == 0
                && engine.getHorsePower() == this.getHorsePower()
                && engine.getCylinderNumber() == this.getCylinderNumber()
                && engine.getTorque() == this.getTorque();
    }
}
