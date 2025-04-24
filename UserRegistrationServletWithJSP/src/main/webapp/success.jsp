<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
session = request.getSession(false);
response.setContentType("text/html");
if (session != null) {
	String firstName = (String) session.getAttribute("firstName");
	String lastName = (String) session.getAttribute("lastName");
	response.getWriter().append("User FirstName : " + firstName + "    & LastName : " + lastName);

	response.getWriter().append("<br><br><a href='logoutServlet'> Logout </a>");
} else {
	response.getWriter().append("<h3>Session is null</h3>");

}



%>
</body>
</html>