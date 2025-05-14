package artproject.storageFiles;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import artproject.ArtCollector;
import artproject.Painting;


public class CSVStorageTest {
    
    @Test
    public void testSaveAndLoad(){
        ArtCollector A = new ArtCollector();
        Painting B = new Painting("Morgensol", 2004, "Herman Kul", "Norway", 150);

        A.addPainting(B);
        
        ArtCollectorStorage storage = new CSVStorage(Path.of("test.csv"));
        try {
            storage.saveArtCollection(A.getPaintings());
            
            Painting painting = storage.loadArtCollection().get(0);
            
            assertEquals(painting.getCountry(), B.getCountry());
            assertEquals(painting.getName(), B.getName());
            assertEquals(painting.getPainter(), B.getPainter());
            assertEquals(painting.getPrice(), B.getPrice());
            assertEquals(painting.getYear(), B.getYear());
            

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail();
        } 
    }
}
