package com.learn;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection connection = null;

	/**
	 * Default constructor.
	 */
	public LoginServlet() {
		// TODO Auto-generated constructor stub
	}

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

		HttpSession session = request.getSession();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();

		// verify login and password with db data
		try (PreparedStatement preparedStatement = connection
				.prepareStatement("select * from userinfo where username = ? and pwd =  ?");) {
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String firstName = resultSet.getString(3);
				String lastName = resultSet.getString(4);

				session.setAttribute("userName", userName);
				session.setAttribute("password", password);
				session.setAttribute("firstName", firstName);
				session.setAttribute("lastName", lastName);

				// redirect to successServlet
				response.sendRedirect("http://localhost:8080/UserRegistrationServlet/successServlet");
				

			} else {
				//Send to failure page 
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("failure.html");
				requestDispatcher.forward(request, response);
			}
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
