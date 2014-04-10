package MessageSystemTests;

import base.Abonent;
import base.Address;
import base.AddressService;
import messageSystem.AddressServiceImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddressServiceImplTest {

    private class MyService implements Abonent {

        @Override
        public Address getAddress() {
            return new Address();
        }
    }

    private AddressService addressService;
    private String serviceName;

    @BeforeMethod
    public void setUp() throws Exception {
        addressService = new AddressServiceImpl();
        serviceName = "myService";
    }

    @Test
    public void addSameServices() throws Exception {
        Assert.assertNull(addressService.getAddressByName(serviceName));
        Assert.assertTrue(addressService.addService(new MyService(), serviceName));
        Assert.assertFalse(addressService.addService(new MyService(), serviceName));
    }

    @Test
    public void testGetAddressByName() throws Exception {
        Assert.assertNull(addressService.getAddressByName(serviceName));
        addressService.addService(new MyService(), serviceName);
        Assert.assertNotNull(addressService.getAddressByName(serviceName));
    }
}
