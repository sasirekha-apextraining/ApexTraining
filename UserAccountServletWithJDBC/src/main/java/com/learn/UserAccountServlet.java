package com.learn;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserAccountServlet
 */
@WebServlet("/userAccount")
public class UserAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection connection = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// Register JDBC Driver
		try {
			// Register JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			String connectionURL = config.getServletContext().getInitParameter("connectionURL");
			String userName = config.getServletContext().getInitParameter("userName");
			String password = config.getServletContext().getInitParameter("password");

			// Create Connection Object
			connection = DriverManager.getConnection(connectionURL, userName, password);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();

		try {
			int id = Integer.parseInt(request.getParameter("id"));
			displayUserDetails(id, writer);

		} catch (NumberFormatException e) {
			writer.append("<h3> Please enter valid id</h3>");
		}

	}

	private void displayUserDetails(int id, PrintWriter writer) {
		try (PreparedStatement ps = connection.prepareStatement("select * from accounts where id = " + id);) {

			ResultSet resultSet = ps.executeQuery();

			// Iterate over the resultSet
			if (resultSet.next()) {
				String firstName = resultSet.getString(2);
				String lastName = resultSet.getString(3);
				int balance = resultSet.getInt(4);
				writer.append("<h3> ID = " + id + "<br> FirstName = " + firstName + "<br> LastName = " + lastName
						+ "<br> Balance = " + balance + "</h3>");

			} else {
				writer.append("<h3> There is no user with id = " + id + "</h3>");
			}
			ps.close();
			writer.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
