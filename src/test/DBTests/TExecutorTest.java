package DBTests;

import dbService.TExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class TExecutorTest {

    private Connection connection;

    @BeforeMethod
    public void setUp() throws Exception {
        try{

            Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
            DriverManager.registerDriver(driver);
        }
        catch(Exception e){
            System.err.println("\nError");
            System.err.println("DVServiceImpl, run1");
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        String url="jdbc:mysql://localhost:3306/checkers?user=checkers&password=QSQ9D9BUBW93DK8A7H9FPXOB5OLOP84BA4CJRWK96VN0GPVC6P";
        try{
            connection = DriverManager.getConnection(url);
        }
        catch(Exception e){
            System.err.println("\nError");
            System.err.println("DVServiceImpl, run2");
            System.err.println(e.getMessage());
            System.exit(-1);
        }
        // TODO: дописать удаление пользователя
        TExecutor.addUser(connection, "Polly", "Nirvana");
    }

    @Test
    public void findPosition(){
        int user_id = TExecutor.findUser(connection, "Polly");
        Assert.assertNotEquals(user_id, 0);
    }

    @Test
    public void findUserFirstNull(){
        int user_id =TExecutor.findUser(connection, "ASKFHALsdfxO!1234ssvvasde");
        Assert.assertEquals(user_id, 0);

    }
}
