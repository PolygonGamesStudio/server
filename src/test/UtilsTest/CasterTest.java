package UtilsTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Caster;

import java.util.LinkedHashMap;
import java.util.Map;

public class CasterTest {

    @Test
    public void casterTest() throws Exception {
        Map<String, Integer> nameAge = new LinkedHashMap<String, Integer>();

        nameAge.put("Alexei", 22);
        nameAge.put("Sawa", 21);
        nameAge.put("Max", 19);

        String[] names;

        names = Caster.castKeysToStrings(nameAge);

        Assert.assertEquals(names.length, 3);
        Assert.assertNotNull(names[0]);
        Assert.assertEquals(names[0], "Alexei");
        Assert.assertNotNull(names[1]);
        Assert.assertEquals(names[1], "Sawa");
        Assert.assertNotNull(names[2]);
        Assert.assertEquals(names[2], "Max");
    }

    @Test
    public void nullCaster() throws Exception {
        String[] names;
        names = Caster.castKeysToStrings(null);
        Assert.assertNull(names);
    }
}
