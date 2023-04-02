<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<%
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	if(username.equals("Marco") && password.equals("Prova01")){
		out.println("Accesso consentito "+username);
	} else {
		out.println("Accesso negato");
	}
%>

</body>
</html>