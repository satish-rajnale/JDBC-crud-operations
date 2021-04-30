package jspToSql;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jspToSql.Names;

//userservlet= fetcher
//Names= User
//UserList= resultList
//userDao= UserData

public class UserData {
	private static final String INSERT_USERS_SQL = "INSERT INTO u_data" + "  (u_id,u_name,u_address) VALUES "
			+ " (?, ?, ?);";
	private static final String SELECT_USER_BY_ID = "select u_id,u_name,u_address from u_data where u_id = ?;";
	private static final String SELECT_ALL_USERS = "select * from u_data;";
	private static final String DELETE_USERS_SQL = "delete from u_data where u_id = ?;";
	private static final String UPDATE_USERS_SQL = "update u_data set u_name = ?,u_address= ? where u_id = ?;";
	
	public UserData() {    }
	
	
	public void insertUser(Names user) throws SQLException, ClassNotFoundException {
		System.out.println(INSERT_USERS_SQL);
		
		try (Connection conn = DatabaseConnection.initializeDatabase();
				PreparedStatement preparedStatement = conn.prepareStatement(INSERT_USERS_SQL)) 
		
		{
			preparedStatement.setLong(1, user.getId());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setString(3, user.getAddress());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	
	public Names selectUser(int id) throws ClassNotFoundException {
		Names user = null;
		
		try  {Connection conn = DatabaseConnection.initializeDatabase();
		
		PreparedStatement preparedStatement = conn.prepareStatement(SELECT_USER_BY_ID);
			preparedStatement.setInt(1, id);
			
			
			ResultSet rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
				String name = rs.getString("u_name");
				String address = rs.getString("u_address");
				user = new Names(id, name, address);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}
	
	
	public List<Names> selectAllUsers() throws ClassNotFoundException {

		
		List<Names> users = new ArrayList<>();
		
		try (Connection connection = DatabaseConnection.initializeDatabase();

				
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
		
			ResultSet rs = preparedStatement.executeQuery();

			
			while (rs.next()) {
				int id = rs.getInt("u_id");
				String name = rs.getString("u_name");
				
				String address = rs.getString("u_address");
				users.add(new Names(id, name,address));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}
	
	public boolean deleteUser(int id) throws SQLException, ClassNotFoundException {
		boolean rowDeleted;
		try (Connection connection = DatabaseConnection.initializeDatabase();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateUser(Names user) throws SQLException, ClassNotFoundException {
		boolean rowUpdated;
		try (Connection connection = DatabaseConnection.initializeDatabase();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, user.getName());
			statement.setString(2, user.getAddress());
			
			statement.setInt(3, user.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
	
	
	
}
