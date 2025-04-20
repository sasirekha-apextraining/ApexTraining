package com.learn;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Calculator servlet
 */
@WebServlet("/calculator")
public class CalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		System.out.println("init() method invoked...");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doGet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charSet=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Calculator</title></head><body>");
		try {

			double num1 = Double.parseDouble(request.getParameter("textNum1"));
			double num2 = Double.parseDouble(request.getParameter("textNum2"));
			double result = 0;
			boolean flag = false;

			String op = request.getParameter("arithmaticOp");

			switch (op) {
			case "addition": {
				result = num1 + num2;
				break;
			}
			case "subtraction": {
				result = num1 - num2;
				break;
			}
			case "multiplication": {
				result = num1 * num2;
				break;
			}
			case "division": {
				if (num2 == 0) {
					flag = true;
					out.println("<h1> Can not divide a number with zero </h1>");
					break;
				} else {
					result = num1 / num2;
					break;
				}

			}

			}
			if (!flag) {
				out.println("<h1> Result = " + result + "</h1>");
			}
		} catch (NumberFormatException e) {
			out.println("<h3> Please enter valid numeric values</h3>");
		}
		out.println("</body></html>");
	}

	@Override
	public void destroy() {
		System.out.println("destroy() method invoked...");
	}

}
