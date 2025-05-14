package artproject.storageFiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;

import artproject.ArtCollector;
import artproject.Painting;

public class CSVStorage implements ArtCollectorStorage {

    private final Path path;

    public CSVStorage(Path path) {
        this.path = Objects.requireNonNull(path);
    }

    @Override
    public List<Painting> loadArtCollection() throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(this.path)) {
            return reader.lines().map(line -> {
                String [] parts = line.split(",");
                String name = parts[0];
                int year = Integer.parseInt(parts[1]);
                String painter = parts[2];
                String country = parts[3];
                double price = Double.parseDouble(parts[4]);
                return new Painting(name, year, painter, country, price);
            }).toList();
        }
    }

    @Override
    public void saveArtCollection(List<Painting> artCollection) throws IOException {        //StandarOenOption CREATE and TRUNCATE_EXISTING ensures "fresh start"
        try (BufferedWriter writer = Files.newBufferedWriter(this.path, StandardOpenOption.CREATE/*_NEW*/, StandardOpenOption.TRUNCATE_EXISTING)){
            for (Painting painting : artCollection){
                String line = painting.getName() + "," + painting.getYear() 
                + "," + painting.getPainter() + "," + painting.getCountry()
                + "," + painting.getPrice() + "\n";
                writer.write(line);
            }
        }
    }


    public static void main(String[] args) {
        ArtCollector A = new ArtCollector();
        Painting B = new Painting("Morgensol", 2004, "Herman Kul", "Norway", 150);
        Painting C = new Painting("Morgensky", 2005, "Herman Ukul", "Norway", 200);
        Painting D = new Painting("Morgennatt", 2006, "Herman Kul", "Norway", 250);

        A.addPainting(B);
        A.addPainting(C);
        A.addPainting(D);
        
        ArtCollectorStorage storage = new CSVStorage(Path.of("test.csv"));
        try {
            storage.saveArtCollection(A.getPaintings());
            System.out.println(storage.loadArtCollection());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }
}
