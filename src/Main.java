import config.DatabaseConfig;
import constants.Colors;
import constants.MotorcycleManufacturers;
import constants.MotorcycleSuspensions;
import exception.InvalidEngineSpecsException;
import exception.InvalidSeatCountException;
import exception.InvalidYearException;
import model.*;
import constants.CarManufacturers;
import service.DatabaseService;
import service.VehicleService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Thread.sleep;
import static util.Adnotations.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, SQLException {

        Scanner in = new Scanner(System.in);
        boolean active = true;

        /*
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

        */
        System.out.println("Se incarca inregistrarile din baza de date... \n \n");
        DatabaseService.getInstance().loadDieselEngines();
        DatabaseService.getInstance().loadPetrolEngines();
        DatabaseService.getInstance().loadSedans();
        DatabaseService.getInstance().loadSportbikes();

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
                                        VehicleService.getInstance().addCar(sedan);
                                        DatabaseService.getInstance().addSedanToDb(sedan);
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
                                        VehicleService.getInstance().addCar(hatchback);
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
                                    VehicleService.getInstance().addCar(suv);
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
                                        VehicleService.getInstance().addMotorcycle(cruiser);
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
                                        VehicleService.getInstance().addMotorcycle(sportbike);
                                        DatabaseService.getInstance().addSportbikeToDb(sportbike);
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
                                        VehicleService.getInstance().addMotorcycle(dirtbike);
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
                                VehicleService.getInstance().addDieselEngine(eng);
                                DatabaseService.getInstance().addDieselEngineToDb(eng);
                            }
                            else{
                                PetrolEngine eng = new PetrolEngine(cap, cai, cil, cuplu);
                                VehicleService.getInstance().addPetrolEngine(eng);
                                DatabaseService.getInstance().addPetrolEngineToDb(eng);
                            }
                        }
                        catch (InputMismatchException exception){
                            in.nextLine();
                            showInvalidOption();
                        }
                        break;
                    case 4:
                        System.out.println("Motoare pe Diesel disponibile: \n");
                        if(VehicleService.getInstance().getDieselEngines() == 0)
                            System.out.println("Nu exista motoare Diesel disponibile!");
                        System.out.println("\nMotoare pe benzina disponibile: \n");
                        if(VehicleService.getInstance().getPetrolEngines() == 0)
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
                                VehicleService.getInstance().removeDieselEngine(eng);
                            }
                            else{
                                PetrolEngine eng = new PetrolEngine(cap, cai, cil, cuplu);
                                VehicleService.getInstance().removePetrolEngine(eng);
                            }
                        }
                        catch (InputMismatchException exception){
                            in.nextLine();
                            showInvalidOption();
                        }
                        break;
                    case 6:
                        VehicleService.getInstance().getCars();
                        break;
                    case 7:
                        VehicleService.getInstance().getMotorcycles();
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
                            VehicleService.getInstance().addEngineToCar(car, engine, diesel);
                            break;
                        }
                        catch (InputMismatchException exception){
                            in.nextLine();
                            showInvalidOption();
                        }
                        catch (Exception exception){
                            in.nextLine();
                            //System.out.println(exception.getMessage());
                            showBadIndex();
                        }
                        break;
                    case 9:
                        try{
                            showAddEngineOptions(1);
                            int car = in.nextInt();
                            showAddEngineOptions(2);
                            int engine = in.nextInt();
                            VehicleService.getInstance().addEngineToMotorcycle(car, engine);
                            break;
                        }
                        catch (InputMismatchException exception){
                            in.nextLine();
                            showInvalidOption();
                        }
                        catch (Exception exception){
                            in.nextLine();
                            System.out.println(exception.getMessage());
                            showBadIndex();
                        }
                        break;
                    case 10:
                        try{
                            System.out.println("Introduceti indexul masinii pe care doriti sa o stergeti: \n");
                            int car = in.nextInt();
                            VehicleService.getInstance().removeCar(car);
                            break;
                        }
                        catch (InputMismatchException exception){
                            in.nextLine();
                            showInvalidOption();
                        }
                        catch (Exception exception){
                            System.out.println(exception.getMessage());
                            in.nextLine();
                            showBadIndex();
                        }
                        break;
                    case 11:
                        try{
                            System.out.println("Introduceti indexul motocicletei pe care doriti sa o stergeti: \n");
                            int car = in.nextInt();
                            VehicleService.getInstance().removeMotorcycle(car);
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
        Connection connection = DatabaseConfig.getInstance().getDatabaseConnection();
        connection.close();
    }
}