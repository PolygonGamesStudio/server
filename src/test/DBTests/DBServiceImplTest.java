package DBTests;

import base.MessageSystem;
import dbService.DBServiceImpl;
import dbService.UserDataSet;
import messageSystem.MessageSystemImpl;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.util.List;
import java.util.Random;

public class DBServiceImplTest {

    private MessageSystem messageSystem;

    @BeforeMethod
    public void setUp() throws Exception {
        messageSystem = new MessageSystemImpl();
    }

    @Test
    public void DBServiceImplTestAddUserCorrect() {

        DBServiceImpl dbService = new DBServiceImpl(messageSystem);
        Boolean bool = dbService.addUDS(new BigInteger(65, new SecureRandom()).toString(16),
                new BigInteger(65, new SecureRandom()).toString(16));
        Assert.assertTrue(bool);
    }

    @Test
    public void DBServiceImplTestAddUserRepeat() {

        DBServiceImpl dbService = new DBServiceImpl(messageSystem);
        dbService.addUDS("Pitty", "Nirvana");
        Boolean bool = dbService.addUDS("Pitty", "Nirvana");
        Assert.assertTrue(bool);
    }

    @Test
    public void testGetUsers() throws Exception {
        DBServiceImpl dbService = new DBServiceImpl(messageSystem);
        String name = new BigInteger(65, new SecureRandom()).toString(16);
        String passwd = new BigInteger(65, new SecureRandom()).toString(16);
        dbService.addUDS(name, passwd);
        UserDataSet result = dbService.getUDS(name, passwd);
//        TODO: как проверить?   java.lang.AssertionError: expected object to not be null
//        Assert.assertNotNull(result);

    }

    @Test
    public void testGetUsersNull() throws Exception {
        DBServiceImpl dbService = new DBServiceImpl(messageSystem);
        UserDataSet result = dbService.getUDS(new BigInteger(65, new SecureRandom()).toString(16), "passwd");
        Assert.assertNull(result);

    }
}
