package com.learn;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection connection = null;
	private PreparedStatement ps = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
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

		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		
		if ((!userName.isEmpty() && userName != null) && (!password.isEmpty() && password != null)
				&& (!firstName.isEmpty() && firstName != null) && (!lastName.isEmpty() && lastName != null)) {
			// Received all user data, store user data to database.
			try {
				ps = connection
						.prepareStatement("insert into userinfo (username, pwd, firstname, lastname) values (?,?,?,?)");

				ps.setString(1, userName);
				ps.setString(2, password);
				ps.setString(3, firstName);
				ps.setString(4, lastName);

				ps.executeUpdate();
				// Display msg to saying user data stored and give link to login page
				writer.append("Successfully User Registered");
				writer.append("<br><br><a href='login.html'> Login </a>");

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {

			// display error message saying enter all fields and share link to registerPage
			writer.append("Please enter all fields ");
			writer.append("<br><br><a href='registerPage.html'> User Registration Form </a>");

		}
		writer.close();
	}

	@Override
	public void destroy() {
		try {
			ps.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
