package dbService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TExecutor {

	public static void addUser(Connection connection,String login, String password){
		PreparedStatement stmt=null;
		String query="INSERT INTO Users(nickname,password,registration_date) VALUES(?,?,CURRENT_TIMESTAMP)";
		try{
			stmt = connection.prepareStatement(query);
			stmt.setString(1, login);
			stmt.setString(2, password);
			stmt.executeUpdate();
			stmt.close();
		}
		catch(Exception e){
			System.err.println("\nError");
			System.err.println("TExecutor, addUser");
			System.err.println(e.getMessage());
		}

        try{
            stmt.close();
        } catch (Exception ignored) {
        }

	}


   public static void delUser(Connection connection, String nickname) {
       PreparedStatement stmt=null;
       String query="Delete from Users Where nickname=?";
       try{
           stmt = connection.prepareStatement(query);
           stmt.setString(1, nickname);
           stmt.executeUpdate();
           stmt.close();
       }
       catch(Exception e){
           System.err.println("\nError");
           System.err.println("TExecutor, delUser");
           System.err.println(e.getMessage());
       }

       try{
           stmt.close();
       }
       catch (Exception ignored) {
       }
   }

	public static int findUser(Connection connection, String login){
		int rows=0;
		PreparedStatement stmt=null;
		String query="SELECT COUNT(*) as C FROM Users WHERE nickname=?";
		try{
			stmt = connection.prepareStatement(query);
			stmt.setString(1, login);
			stmt.execute();
			ResultSet resultSet = stmt.getResultSet();
			if(resultSet.first())
				rows=resultSet.getInt("C");
			stmt.close();
		}
		catch(Exception e){
			System.err.println("\nError");
			System.err.println("TExecutor, addUser");
			System.err.println(e.getMessage());
		}

        try{
            stmt.close();
        } catch (Exception ignored) {
        }

		return rows;
	}

	public static <T> T getUDS(Connection connection, String login, String password,
			TResultHandler<T> handler){
		PreparedStatement stmt=null;
		T user=null;
		String query="SELECT id,rating,win_quantity,lose_quantity FROM Users WHERE nickname=? AND password=?";
		try{
			stmt = connection.prepareStatement(query);
			stmt.setString(1, login);
			stmt.setString(2, password);
			stmt.execute();
			ResultSet resultSet = stmt.getResultSet();
			user=handler.handle(resultSet);
			stmt.close();
		}
		catch(Exception e){
			System.err.println("\nError");
			System.err.println("TExecutor, addUser");
			System.err.println(e.getMessage());
		}

        try{
            stmt.close();
        }
        catch(Exception ignor){
        }

		return user;
	}

	public static void updateUser(Connection connection, String login, 
			int rating, int winQuantity, int loseQuantity ){
		PreparedStatement stmt=null;
		String query="UPDATE Users " +
				"SET rating = ?, win_quantity = ?, lose_quantity = ? " +
				"WHERE nickname = ?";
		try{
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, rating);
			stmt.setInt(2, winQuantity);
			stmt.setInt(3, loseQuantity);
			stmt.setString(4, login);
			stmt.executeUpdate();
			stmt.close();
		}
		catch(Exception e){
			System.err.println("\nError");
			System.err.println("TExecutor, updateUser");
			System.err.println(e.getMessage());
		}

        try{
            stmt.close();
        }
        catch(Exception ignor){
        }

	}

}