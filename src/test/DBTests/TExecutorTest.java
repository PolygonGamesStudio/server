package DBTests;

import dbService.TExecutor;
import dbService.TResultHandler;
import dbService.UserDataSet;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.*;

public class TExecutorTest {

    private Connection connection;
    private String name;
    private String passwd = new BigInteger(65, new SecureRandom()).toString(16);

    @BeforeMethod
    public void setUp() throws Exception {
        Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
        DriverManager.registerDriver(driver);
        String url="jdbc:mysql://localhost:3306/checkers?user=checkers&password=QSQ9D9BUBW93DK8A7H9FPXOB5OLOP84BA4CJRWK96VN0GPVC6P";
        connection = DriverManager.getConnection(url);

    }

    @Test
    public void testAddUser() throws Exception {
        TExecutor.addUser(connection, new BigInteger(65, new SecureRandom()).toString(16), new BigInteger(65, new SecureRandom()).toString(16));
    }

    @Test
    public void testAddNull() throws Exception {
        TExecutor.addUser(connection, null, passwd);
    }

    @Test
    public void testFindUser() throws Exception {
        name = new BigInteger(65, new SecureRandom()).toString(16);
        TExecutor.addUser(connection, name, passwd);
        TExecutor.findUser(connection, name);

    }

    @Test
    public void testFindUserNull() throws Exception {
        TExecutor.findUser(connection, new BigInteger(65, new SecureRandom()).toString(16));

    }

    @Test
    public void testUpdateUser() throws Exception {
        name = new BigInteger(65, new SecureRandom()).toString(16);
        TExecutor.addUser(connection, name, passwd);
        TExecutor.updateUser(connection, name, 600, 1, 2);
    }

    @Test
    public void testUpdateUserNull() throws Exception {
        TExecutor.updateUser(connection, null, 600, 1, 2);
    }
}
