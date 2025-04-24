package com.learn;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class EntrySessionServlet
 */
@WebServlet("/userSession")
public class EntrySessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public EntrySessionServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// If session does not exist then getSession will give null
		// HttpSession session = request.getSession(false);

		// Session will never be null, getSession will always return session
		HttpSession session = request.getSession();
		//Timeout
		session.setMaxInactiveInterval(15);

		String userName = request.getParameter("userName");
		session.setAttribute("userName", userName);

		response.setContentType("text/html");

		PrintWriter writer = response.getWriter();
		writer.append("<a href='finalServlet'> Final Page Link </a>");
		writer.close();

	}

}
