package DBTests;

import base.MessageSystem;
import dbService.DBServiceImpl;
import messageSystem.MessageSystemImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.util.Random;

public class DBServiceImplTest {

    private MessageSystem messageSystem;
    private Connection connection;

    @BeforeMethod
    public void setUp() throws Exception {
        messageSystem = new MessageSystemImpl();
        DBServiceImpl dbService = new DBServiceImpl(messageSystem);
        dbService.setConnection();
    }

    @Test
    public void DBServiceImplTestCorrect() {

        DBServiceImpl dbService = new DBServiceImpl(messageSystem);
        boolean bool = dbService.addUDS(new BigInteger(65, new SecureRandom()).toString(16), "Nirvana");
        Assert.assertNotEquals(bool, true);
    }

    @Test
    public void DBServiceImplTestRepeat() {

        DBServiceImpl dbService = new DBServiceImpl(messageSystem);
        dbService.addUDS("Pitty", "Nirvana");
        boolean bool = dbService.addUDS("Pitty", "Nirvana");
        Assert.assertEquals(bool, false);
    }

}
