package com.learn;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class FinalServlet
 */
@WebServlet("/finalServlet")
public class FinalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public FinalServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		response.setContentType("text/html");
		if(session != null) {
			String userName = (String) session.getAttribute("userName");
			
			response.getWriter().append("User Name is : ").append(userName);
		} else {
			response.getWriter().append("<a href='user.html'> User Form </a>");
		}
		
		
	}

}
