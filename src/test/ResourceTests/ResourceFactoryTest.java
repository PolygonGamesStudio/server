package ResourceTests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resource.GameSettings;
import resource.ResourceFactory;

public class ResourceFactoryTest {

    private ResourceFactory resourceFactory;

    @BeforeMethod
    public void setUp() throws Exception {
        resourceFactory = ResourceFactory.instance();
    }

    @Test
    public void testInstance() throws Exception {
        ResourceFactory tempResourceFactory = null;

        tempResourceFactory = ResourceFactory.instance();
        Assert.assertEquals(resourceFactory, tempResourceFactory);
    }

    @Test
    public void testGetResource() throws Exception {
        Assert.assertNotNull(resourceFactory.getResource("settings/gameSettings.xml"));
    }
}
