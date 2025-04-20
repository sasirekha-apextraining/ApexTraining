package com.learn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountsDAOImpl {

	public static void main(String[] args) {
		try {
			// Register JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Create Connection Object
			try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root",
					"root1234");

					// Create a Statement Object
					PreparedStatement statement = connection.prepareStatement("select * from accounts where id=?");) {

				statement.setInt(1, 2);
				
				ResultSet resultSet = statement.executeQuery();

				// Iterate over the resultSet
				while (resultSet.next()) {
					int id = resultSet.getInt(1);
					String firstName = resultSet.getString(2);
					String lastName = resultSet.getString(3);
					int balance = resultSet.getInt(4);

					System.out.println("ID = " + id + " FirstName = " + firstName + " LastName = " + lastName
							+ " Balance = " + balance);

				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
