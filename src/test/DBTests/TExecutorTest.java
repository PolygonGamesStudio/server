package DBTests;

import dbService.TExecutor;
import dbService.TResultHandler;
import dbService.UserDataSet;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
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
        String login = new BigInteger(65, new SecureRandom()).toString(16);
        TExecutor.addUser(connection, login, new BigInteger(65, new SecureRandom()).toString(16));
        TExecutor.delUser(connection, login);
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
        TExecutor.findUser(connection, "\" limit 0,0;-- ");

    }

    @Test
    public void testUpdateUser() throws Exception {
        name = new BigInteger(65, new SecureRandom()).toString(16);
        TExecutor.addUser(connection, name, passwd);
        TExecutor.updateUser(connection, name, 600, 1, 2);
        TExecutor.delUser(connection, name);
    }

    @Test
    public void testUpdateUserNull() throws Exception {
        TExecutor.updateUser(null, null, 2, 1, 2);
    }


    @Test
    public void testGetUSD() throws Exception {
        TExecutor.getUDS(connection, name, passwd, new TResultHandler<UserDataSet>(){
            @Override
            public UserDataSet handle(ResultSet result){
                try {
                    if(result.first()){
                        int id = result.getInt("id");
                        int rating = result.getInt("rating");
                        int winQuantity = result.getInt("win_quantity");
                        int loseQuantity = result.getInt("lose_quantity");
                        return new UserDataSet(id,name, rating, winQuantity, loseQuantity);
                    }
                }
                catch (SQLException e) {
                    System.err.println("\nError");
                    System.err.println("DBServiceImpl, addUDS");
                    System.err.println(e.getMessage());
                }
                return null;
            }
        });

    }

    @Test
    public void testGetUSDNull() throws Exception {
        TExecutor.getUDS(null, null, passwd, new TResultHandler<UserDataSet>(){
            @Override
            public UserDataSet handle(ResultSet result){
                try {
                    if(result.first()){
                        int id = result.getInt("id");
                        int rating = result.getInt("rating");
                        int winQuantity = result.getInt("win_quantity");
                        int loseQuantity = result.getInt("lose_quantity");
                        return new UserDataSet(id,null, rating, winQuantity, loseQuantity);
                    }
                }
                catch (SQLException e) {
                    System.err.println("\nError");
                    System.err.println("DBServiceImpl, addUDS");
                    System.err.println(e.getMessage());
                }
                return null;
            }
        });

    }

    @Test
    public void testFindPositionOne() throws Exception {
        TExecutor.findPosition(connection, name, new int[] {1,2,3}, 1, 1);

    }

    @Test(expectedExceptions = ArrayIndexOutOfBoundsException.class)
    public void testFindPositionThree() throws Exception {
        TExecutor.findPosition(connection, name, new int[] {1,2,3}, 3, 3);

    }

    @AfterMethod
    public void teardown() throws Exception {
        connection.close();
    }
}
