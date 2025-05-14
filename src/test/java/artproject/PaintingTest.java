package artproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;



public class PaintingTest {

    @Test
    public void testConstructor(){
        new Painting("Skrik", 2000, "Munch", "Norway", 100);
    }

    @Test
    public void testPrice(){
        assertThrows(IllegalArgumentException.class , () -> new Painting("Skrik", 2000, "Munch", "Norway", -100));
    }

    @Test
    public void testGetname(){
        Painting A = new Painting("Skrik", 2000, "Munch", "Norway", 100);
        assertEquals("Skrik", A.getName());
    }

}
