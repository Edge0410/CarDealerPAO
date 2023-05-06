import constants.Colors;
import constants.MotorcycleManufacturers;
import constants.MotorcycleSuspensions;
import exception.InvalidEngineSpecsException;
import exception.InvalidSeatCountException;
import exception.InvalidYearException;
import model.*;
import constants.CarManufacturers;
import service.VehicleService;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Thread.sleep;
import static util.Adnotations.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Scanner in = new Scanner(System.in);
        boolean active = true;

        VehicleService serviciuDefault = new VehicleService();

        try{
            serviciuDefault.loadSedansFromCSV("src/util/sedan.csv");
        }
        catch (Exception exception){
            System.out.println("Nu au putut fi citite sedanurile din fisier!");
        }

        try{
            serviciuDefault.loadSportbikesFromCSV("src/util/sportbike.csv");
        }
        catch (Exception exception){
            System.out.println("Nu au putut fi citite motociclete sport din fisier!");
        }

        try{
            serviciuDefault.loadPetrolEnginesFromCSV("src/util/petrolEngine.csv");
        }
        catch (Exception exception){
            System.out.println("Nu au putut fi citite motoarele pe benzina din fisier!");
        }

        try{
            serviciuDefault.loadDieselEnginesFromCSV("src/util/dieselEngine.csv");
        }
        catch (Exception exception){
            System.out.println("Nu au putut fi citite motoarele pe motorina din fisier!");
        }


        while(active){
            sleep(500);
            showOptions();
            try {
                int option = in.nextInt();
                switch(option){
                    case 1: // adauga masina
                        showCars();
                        try{
                            int tipMasina = in.nextInt();
                            switch(tipMasina){
                                case 1:
                                    try{
                                        showCarOptions(1);
                                        int marca = in.nextInt();
                                        in.nextLine();
                                        showCarOptions(2);
                                        String model = in.nextLine();
                                        showCarOptions(3);
                                        int an = in.nextInt();
                                        showCarOptions(4);
                                        int culoare = in.nextInt();
                                        Sedan sedan = new Sedan(CarManufacturers.values()[marca - 1], model, an, Colors.values()[culoare - 1]);
                                        serviciuDefault.addCar(sedan);
                                    }
                                    catch (InputMismatchException exception){
                                        in.nextLine();
                                        showInvalidOption();
                                    }
                                    break;
                                case 2:
                                    try{
                                        showCarOptions(1);
                                        int marca = in.nextInt();
                                        in.nextLine();
                                        showCarOptions(2);
                                        String model = in.nextLine();
                                        showCarOptions(3);
                                        int an = in.nextInt();
                                        showCarOptions(4);
                                        int culoare = in.nextInt();
                                        showCarOptions(5);
                                        int locuri = in.nextInt();
                                        Hatchback hatchback = new Hatchback(CarManufacturers.values()[marca - 1], model, an, Colors.values()[culoare - 1], locuri);
                                        serviciuDefault.addCar(hatchback);
                                    }
                                    catch (InputMismatchException exception){
                                        in.nextLine();
                                        showInvalidOption();
                                    }
                                    break;
                                case 3:
                                    showCarOptions(1);
                                    int marca = in.nextInt();
                                    in.nextLine();
                                    showCarOptions(2);
                                    String model = in.nextLine();
                                    showCarOptions(3);
                                    int an = in.nextInt();
                                    showCarOptions(4);
                                    int culoare = in.nextInt();
                                    in.nextLine();
                                    showCarOptions(6);
                                    boolean fourWD = in.nextBoolean();
                                    Suv suv = new Suv(CarManufacturers.values()[marca - 1], model, an, Colors.values()[culoare - 1], fourWD);
                                    serviciuDefault.addCar(suv);
                                    break;
                                default:
                                    throw new InputMismatchException("");
                            }
                        }
                        catch (InputMismatchException exception){
                            in.nextLine();
                            showInvalidOption();
                        }
                        catch (ArrayIndexOutOfBoundsException exception){
                            in.nextLine();
                            showInvalidManufacturer();
                        }
                        break;
                    case 2: // adauga motocicleta
                        showMotorcycles();
                        try{
                            int tipMotocicleta = in.nextInt();
                            switch(tipMotocicleta){
                                case 1: //cruiser
                                    try{
                                        showMotorcycleOptions(1);
                                        int marca = in.nextInt();
                                        in.nextLine();
                                        showMotorcycleOptions(2);
                                        String model = in.nextLine();
                                        showMotorcycleOptions(3);
                                        int an = in.nextInt();
                                        showMotorcycleOptions(4);
                                        int culoare = in.nextInt();
                                        showMotorcycleOptions(5);
                                        int wheels = in.nextInt();
                                        Cruiser cruiser = new Cruiser(MotorcycleManufacturers.values()[marca - 1], model, an, Colors.values()[culoare - 1], wheels);
                                        serviciuDefault.addMotorcycle(cruiser);
                                        serviciuDefault.getMotorcycles();
                                        break;
                                    }
                                    catch (InputMismatchException exception){
                                        in.nextLine();
                                        showInvalidOption();
                                    }
                                    break;
                                case 2: //sportbike
                                    try{
                                        showMotorcycleOptions(1);
                                        int marca = in.nextInt();
                                        in.nextLine();
                                        showMotorcycleOptions(2);
                                        String model = in.nextLine();
                                        showMotorcycleOptions(3);
                                        int an = in.nextInt();
                                        showMotorcycleOptions(4);
                                        int culoare = in.nextInt();
                                        showMotorcycleOptions(6);
                                        int viteza = in.nextInt();
                                        Sportbike sportbike = new Sportbike(MotorcycleManufacturers.values()[marca - 1], model, an, Colors.values()[culoare - 1], viteza);
                                        serviciuDefault.addMotorcycle(sportbike);
                                        serviciuDefault.getMotorcycles();
                                        break;
                                    }
                                    catch (InputMismatchException exception){
                                        in.nextLine();
                                        showInvalidOption();
                                    }
                                    break;
                                case 3:
                                    try{
                                        showMotorcycleOptions(1);
                                        int marca = in.nextInt();
                                        in.nextLine();
                                        showMotorcycleOptions(2);
                                        String model = in.nextLine();
                                        showMotorcycleOptions(3);
                                        int an = in.nextInt();
                                        showMotorcycleOptions(4);
                                        int culoare = in.nextInt();
                                        showMotorcycleOptions(7);
                                        int suspensii = in.nextInt();
                                        Dirtbike dirtbike = new Dirtbike(MotorcycleManufacturers.values()[marca - 1], model, an, Colors.values()[culoare - 1], MotorcycleSuspensions.values()[suspensii - 1]);
                                        serviciuDefault.addMotorcycle(dirtbike);
                                        serviciuDefault.getMotorcycles();
                                        break;
                                    }
                                    catch (InputMismatchException exception){
                                        in.nextLine();
                                        showInvalidOption();
                                    }
                                    break;
                                default:
                                    throw new InputMismatchException("");
                            }
                        }
                        catch (InputMismatchException exception){
                            in.nextLine();
                            showInvalidOption();
                        }
                        catch (ArrayIndexOutOfBoundsException exception){
                            in.nextLine();
                            showInvalidManufacturerOrSuspensionKit();
                        }
                        break;
                    case 3: // adauga motor
                        try{
                            showEngineOptions(1);
                            double cap = in.nextDouble();
                            showEngineOptions(2);
                            int cai = in.nextInt();
                            showEngineOptions(3);
                            int cil = in.nextInt();
                            showEngineOptions(4);
                            int cuplu = in.nextInt();
                            in.nextLine();
                            showEngineOptions(5);
                            boolean diesel = in.nextBoolean();
                            if(diesel){
                                DieselEngine eng = new DieselEngine(cap, cai, cil, cuplu);
                                serviciuDefault.addDieselEngine(eng);
                            }
                            else{
                                PetrolEngine eng = new PetrolEngine(cap, cai, cil, cuplu);
                                serviciuDefault.addPetrolEngine(eng);
                            }
                        }
                        catch (InputMismatchException exception){
                            in.nextLine();
                            showInvalidOption();
                        }
                        break;
                    case 4:
                        System.out.println("Motoare pe Diesel disponibile: \n");
                        if(serviciuDefault.getDieselEngines() == 0)
                            System.out.println("Nu exista motoare Diesel disponibile!");
                        System.out.println("\nMotoare pe benzina disponibile: \n");
                        if(serviciuDefault.getPetrolEngines() == 0)
                            System.out.println("Nu exista motoare pe Benzina disponibile! \n");
                        in.nextLine();
                        break;
                    case 5:
                        try{
                            showEngineOptions(1);
                            double cap = in.nextDouble();
                            showEngineOptions(2);
                            int cai = in.nextInt();
                            showEngineOptions(3);
                            int cil = in.nextInt();
                            showEngineOptions(4);
                            int cuplu = in.nextInt();
                            in.nextLine();
                            showEngineOptions(5);
                            boolean diesel = in.nextBoolean();
                            if(diesel){
                                DieselEngine eng = new DieselEngine(cap, cai, cil, cuplu);
                                serviciuDefault.removeDieselEngine(eng);
                            }
                            else{
                                PetrolEngine eng = new PetrolEngine(cap, cai, cil, cuplu);
                                serviciuDefault.removePetrolEngine(eng);
                            }
                        }
                        catch (InputMismatchException exception){
                            in.nextLine();
                            showInvalidOption();
                        }
                        break;
                    case 6:
                        serviciuDefault.getCars();
                        break;
                    case 7:
                        serviciuDefault.getMotorcycles();
                        break;
                    case 8:
                        try{
                            showAddEngineOptions(1);
                            int car = in.nextInt();
                            showAddEngineOptions(2);
                            int engine = in.nextInt();
                            in.nextLine();
                            showAddEngineOptions(3);
                            boolean diesel = in.nextBoolean();
                            serviciuDefault.addEngineToCar(car, engine, diesel);
                            break;
                        }
                        catch (InputMismatchException exception){
                            in.nextLine();
                            showInvalidOption();
                        }
                        catch (Exception exception){
                            in.nextLine();
                            showBadIndex();
                        }
                        break;
                    case 9:
                        try{
                            showAddEngineOptions(1);
                            int car = in.nextInt();
                            showAddEngineOptions(2);
                            int engine = in.nextInt();
                            serviciuDefault.addEngineToMotorcycle(car, engine);
                            break;
                        }
                        catch (InputMismatchException exception){
                            in.nextLine();
                            showInvalidOption();
                        }
                        catch (Exception exception){
                            in.nextLine();
                            showBadIndex();
                        }
                        break;
                    case 10:
                        try{
                            System.out.println("Introduceti indexul masinii pe care doriti sa o stergeti: \n");
                            int car = in.nextInt();
                            serviciuDefault.removeCar(car);
                            break;
                        }
                        catch (InputMismatchException exception){
                            in.nextLine();
                            showInvalidOption();
                        }
                        catch (Exception exception){
                            in.nextLine();
                            showBadIndex();
                        }
                        break;
                    case 11:
                        try{
                            System.out.println("Introduceti indexul motocicletei pe care doriti sa o stergeti: \n");
                            int car = in.nextInt();
                            serviciuDefault.removeMotorcycle(car);
                            break;
                        }
                        catch (InputMismatchException exception){
                            in.nextLine();
                            showInvalidOption();
                        }
                        catch (Exception exception){
                            in.nextLine();
                            showBadIndex();
                        }
                        break;
                    case 0: // iesire
                        active = false;
                        break;
                    default: // invalid input
                        throw new InputMismatchException("");
                }
            }
            catch (InputMismatchException exception)
            {
                in.nextLine();
                showInvalidOption();
            }
            catch (InvalidYearException | InvalidEngineSpecsException | InvalidSeatCountException exception)
            {
                in.nextLine();
                System.out.println(exception.getMessage());
            }

        }
    }
}