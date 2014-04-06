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
    private String name = "Polly";
    private String passwd = "Nirvana";

    @BeforeMethod
    public void setUp() throws Exception {

        Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
        DriverManager.registerDriver(driver);
        String url="jdbc:mysql://localhost:3306/checkers?user=checkers&password=QSQ9D9BUBW93DK8A7H9FPXOB5OLOP84BA4CJRWK96VN0GPVC6P";
        connection = DriverManager.getConnection(url);
        // TODO: дописать удаление пользователя

    }

    @Test
    public void findUserFirstNull(){
        int user_id =TExecutor.findUser(connection, new BigInteger(65, new SecureRandom()).toString(16));
        Assert.assertEquals(user_id, 0);

    }

    @Test
    public void findUserFirstCorrect(){
        int user_id =TExecutor.findUser(connection, name);
        Assert.assertEquals(user_id, 0);

    }

}
