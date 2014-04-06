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
        TExecutor.addUser(connection, name, passwd);
    }

    @Test
    public void findUserFirstNull(){
        int user_id =TExecutor.findUser(connection, new BigInteger(65, new SecureRandom()).toString(16));
        Assert.assertEquals(user_id, 0);

    }

    @Test
    public void updateUser() {
        TExecutor.updateUser(connection, name, 400, 1, 4);
    }

    @Test
    public void updateUserNull() {
        TExecutor.updateUser(connection, new BigInteger(65, new SecureRandom()).toString(16), 400, 1, 4);
    }

    @Test
    public void getUDS() {
        TExecutor.getUDS(connection, name, passwd, new TResultHandler<UserDataSet>() {
            @Override
            public UserDataSet handle(ResultSet result) {
                try {
                    int id = result.getInt("id");
                    int rating = result.getInt("rating");
                    int winQuantity = result.getInt("win_quantity");
                    int loseQuantity = result.getInt("lose_quantity");
                    return new UserDataSet(id, name, rating, winQuantity, loseQuantity);
                } catch (SQLException e) {
                    System.err.println("\nError");
                    System.err.println("DBServiceImpl, addUDS");
                    System.err.println(e.getMessage());
                }
                return null;
            }
        });
    }

    @Test
    public void getUDSNull() {
        TExecutor.getUDS(connection, new BigInteger(65, new SecureRandom()).toString(16), new BigInteger(65, new SecureRandom()).toString(16), new TResultHandler<UserDataSet>() {
            @Override
            public UserDataSet handle(ResultSet result) {
                try {
                    int id = result.getInt("id");
                    int rating = result.getInt("rating");
                    int winQuantity = result.getInt("win_quantity");
                    int loseQuantity = result.getInt("lose_quantity");
                    return new UserDataSet(id, new BigInteger(65, new SecureRandom()).toString(16), rating, winQuantity, loseQuantity);
                } catch (SQLException e) {
                    System.err.println("\nError");
                    System.err.println("DBServiceImpl, addUDS");
                    System.err.println(e.getMessage());
                }
                return null;
            }
        });
    }
}
