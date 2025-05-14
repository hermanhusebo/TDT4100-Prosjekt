package artproject;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArtCollectorTest {
    
    private ArtCollector A;

    @BeforeEach
    public void run(){
        A = new ArtCollector();
        Painting B = new Painting("Morgensol", 2004, "Herman Kul", "Norway", 150);
        Painting C = new Painting("Morgensky", 2005, "Herman Ukul", "Norway", 200);
        Painting D = new Painting("Morgennatt", 2006, "Herman Kul", "Norway", 250);

        A.addPainting(B);
        A.addPainting(C);
        A.addPainting(D);
    }

    @Test
    public void testGetPaintingsFromPainter(){
        assertEquals(2, A.getPaintingsFromPainter("Herman Kul").size());
        }

    @Test
    public void testMostCommonValue(){
        assertEquals("Herman Kul", A.mostCommonValue("painter"));
    }

    @Test
    public void testRemovePaintingByIndex(){
        A.removePaintingByIndex(0);
        assertEquals(2, A.getPaintings().size());
    }

}
