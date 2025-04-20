package com.learn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection connection = null;

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

		String id = request.getParameter("id");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String balance = request.getParameter("balance");

		try (PreparedStatement ps = connection
				.prepareStatement("insert into accounts (id, firstname, lastname, balance) values (?,?,?,?)")) {

			ps.setInt(1, Integer.parseInt(id));
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setInt(4, Integer.parseInt(balance));

			int count = ps.executeUpdate();

			if (count > 0) {
				// success
				response.sendRedirect("success.html");
			} else {
				// failure
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("failure.html");
				requestDispatcher.forward(request, response);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("failure.html");
			requestDispatcher.forward(request, response);
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
