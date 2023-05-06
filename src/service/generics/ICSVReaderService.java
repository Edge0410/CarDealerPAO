package service.generics;

import model.DieselEngine;
import model.PetrolEngine;
import model.Sedan;
import model.Sportbike;

import java.io.IOException;
import java.util.List;

public interface ICSVReaderService {
    List<Sedan> readSedansFromCSV(String filePath) throws IOException;
    List<Sportbike> readSportbikesFromCSV(String filePath) throws IOException;
    List<DieselEngine> readDieselEnginesFromCSV(String filePath) throws IOException;
    List<PetrolEngine> readPetrolEnginesFromCSV(String filePath) throws IOException;
}
