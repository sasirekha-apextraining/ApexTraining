package com.learn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDAO {

	public static void main(String[] args) {
		try {
			// Register JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Create Connection Object
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root",
					"root1234");

			// Create a Statement Object
			Statement statement = connection.createStatement();

			// Execute a SQL Command using statement object
			int insertStatusCode = statement.executeUpdate(
					"insert into accounts (id, firstname, lastname, balance) values (4, 'Sasi', 'Rekha', 100)");
			System.out.println("Execute Update for Insert statement: " + insertStatusCode);

			int updateStatusCode = statement.executeUpdate("update accounts set lastname = 'Sasi'");
			System.out.println("Update  statement status code: " + updateStatusCode);

			ResultSet resultSet = statement.executeQuery("select * from accounts");

			// Iterate over the resultSet
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String firstName = resultSet.getString(2);
				String lastName = resultSet.getString(3);
				int balance = resultSet.getInt(4);

				System.out.println("ID = " + id + " FirstName = " + firstName + " LastName = " + lastName
						+ " Balance = " + balance);

			}

			// Close Statement and Connection
			statement.close();
			connection.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
