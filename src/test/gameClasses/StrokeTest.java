package gameClasses;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StrokeTest {
    Stroke stroke;
    @BeforeMethod
    public void setUp() throws Exception {

    }

    @Test
    public void testGetInverseTrue() throws Exception {
        stroke = new Stroke(1, 1, 1, 1, "st", "b");
        Assert.assertEquals(stroke.getInverse().getColor(), "w");

    }

    @Test
    public void testGetInverseFalse() throws Exception {
        stroke = new Stroke(1, 1, 1, 1, "st", "w");
        stroke.getInverse();
        Assert.assertEquals(stroke.getInverse().getColor(), "b");
    }

    @Test
    public void testIseEmptyTrue() throws Exception{
        stroke = new Stroke(-1, -1, -1, -1, "st", "w");
        Assert.assertTrue(stroke.isEmpty());
    }

    @Test
    public void testIseEmptyFalse() throws Exception{
        stroke = new Stroke(1, 1, 1, 1, "st", "b");
        Assert.assertFalse(stroke.isEmpty());
    }

    @Test
    public void testIseEmptyFalseFalse() throws Exception{
        stroke = new Stroke(-1, 1, 1, 1, "st", "b");
        Assert.assertFalse(stroke.isEmpty());
    }

}
