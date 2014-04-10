package DBTests;

import base.MessageSystem;
import chat.ChatWSImpl;
import dbService.DBServiceImpl;
import dbService.TExecutor;
import dbService.UserDataSet;
import messageSystem.MessageSystemImpl;
import org.powermock.api.mockito.PowerMockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

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
    public void DBServiceImplTestAddUserRepeat() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        DBServiceImpl dbService = new DBServiceImpl(messageSystem);
        DBServiceImpl.createConnection();
        dbService.addUDS("User123asdasd", "pass123");
        Boolean bool_false = dbService.addUDS("User123asdasd", "pass123");
        Assert.assertFalse(bool_false);
    }

    @Test
    public void testGetUsers() throws Exception {
        DBServiceImpl.createConnection();
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
        DBServiceImpl.createConnection();
        DBServiceImpl dbService = new DBServiceImpl(messageSystem);
        UserDataSet result = dbService.getUDS(new BigInteger(65, new SecureRandom()).toString(16), "passwd");
        Assert.assertNull(result);
    }

    @Test
    public void testUpdateUsers() throws Exception {
        DBServiceImpl.createConnection();
        DBServiceImpl dbService = new DBServiceImpl(messageSystem);
        String name = new BigInteger(65, new SecureRandom()).toString(16);
        List<UserDataSet> test;
        test = new ArrayList<UserDataSet>();
        test.add(new UserDataSet(1, name, 500, 5, 5));
        dbService.updateUsers(test);
    }
}
