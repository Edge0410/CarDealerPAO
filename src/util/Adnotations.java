package util;

import java.util.InputMismatchException;

public class Adnotations {
    public static void showOptions(){
        System.out.println("Alegeti operatia dorita:\n\n 1. Adaugati o masina\n 2. Adauga o motocicleta \n 3. Adaugati un motor \n 4. Vizualizati motoarele disponibile \n 5. Eliminati un motor \n 6. Vizualizati masinile \n 7. Vizualizati motocicletele \n 8. Adaugati un motor la o masina \n 9. Adaugati un motor la o motocicleta (Doar pe benzina!) \n 10. Eliminati o masina \n 11. Eliminati o motocicleta \n 0. Iesire\n");
    }

    public static void showInvalidOption(){
        System.out.println("Operatie invalida! Incercati din nou.\n\n");
    }

    public static void showInvalidManufacturer(){
        System.out.println("Marca masinii este invalida. Incercati din nou.\n\n");
    }

    public static void showInvalidManufacturerOrSuspensionKit(){
        System.out.println("Marca motocicletei sau kitul de suspensii ales sunt invalide. Incercati din nou.\n\n");
    }

    public static void showCars(){
        System.out.println("Selectati tipul de masina dorit:\n 1. Sedan\n 2. Hatchback\n 3. SUV\n\n");
    }

    public static void showMotorcycles(){
        System.out.println("Selectati tipul de motocicleta dorit:\n 1. Cruiser \n 2. SportBike \n 3. Motorbike\n\n");
    }

    public static void showCarOptions(int option){
        switch(option){
            case 1:
                System.out.println("Selectati producatorul masinii: \n 1. Audi \n 2. BMW \n 3. Opel \n 4. Mercedes \n");
                break;
            case 2:
                System.out.println("Introduceti modelul masinii: \n");
                break;
            case 3:
                System.out.println("Introduceti anul masinii: \n");
                break;
            case 4:
                System.out.println("Selectati culoarea masinii: \n 1. Rosu \n 2. Verde \n 3. Gray \n 4. Negru Carbonizat \n 5. Bleu \n 6. Alb \n");
                break;
            case 5:
                System.out.println("Introduceti numarul de locuri (2, 4, 5): \n");
                break;
            case 6:
                System.out.println("Masina este 4x4? (True/False): \n");
                break;
            default:
                throw new InputMismatchException("");
        }
    }

    public static void showMotorcycleOptions(int option){
        switch(option){
            case 1:
                System.out.println("Selectati producatorul motocicletei: \n 1. Yamaha \n 2. Honda\n 3. Kawasaki \n 4. Harley \n");
                break;
            case 2:
                System.out.println("Introduceti modelul motocicletei: \n");
                break;
            case 3:
                System.out.println("Introduceti anul motocicletei: \n");
                break;
            case 4:
                System.out.println("Selectati culoarea motocicletei: \n 1. Rosu \n 2. Verde \n 3. Gray \n 4. Negru Carbonizat \n 5. Bleu \n 6. Alb \n");
                break;
            case 5:
                System.out.println("Introduceti numarul de pneuri ale motocicletei (2/3): \n");
                break;
            case 6:
                System.out.println("Introduceti limita maxima de viteza a motocicletei (Km/h): \n");
                break;
            case 7:
                System.out.println("Selectati kitul de suspensii dorit pe motocicleta: \n 1. Fara kit \n 2. Spring \n 3. Revalve \n 4. Competition \n");
                break;
            default:
                throw new InputMismatchException("");
        }
    }

    public static void showEngineOptions(int option){
        switch(option){
            case 1:
                System.out.println("Introduceti capacitatea motorului: \n");
                break;
            case 2:
                System.out.println("Introduceti numarul de cai putere ai motorului: \n");
                break;
            case 3:
                System.out.println("Introduceti numarul de cilindrii ai motorului: \n");
                break;
            case 4:
                System.out.println("Introduceti cuplul motorului: \n");
                break;
            case 5:
                System.out.println("Motorul functioneaza pe benzina? (true = Diesel, false = Benzina): \n");
                break;
            default:
                throw new InputMismatchException("");
        }
    }

    public static void showAddEngineOptions(int option){
        switch(option){
            case 1:
                System.out.println("Introduceti indexul masinii: \n");
                break;
            case 2:
                System.out.println("Introduceti indexul motorului: \n");
                break;
            case 3:
                System.out.println("Introduceti motorizarea (true - diesel / false - benzina):\n");
                break;
            default:
                throw new InputMismatchException("");
        }
    }

    public static void showBadIndex(){
        System.out.println("Indici invalizi!");
    }
}
