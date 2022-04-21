<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
	<h1> Welcome to JAVA web application (from jsp)!!</h1><br>
	<h2> LOGIN PAGE:</h2>
	<form action = "<%=request.getContextPath()%>/home" method = "post">
		<input type = "text" name = "uname" placeholder = "type userName"/><br><br>
		<input type = "password" name = "pwd" placeholder = "type your pasword"/><br><br>
		<input type = "submit"  value = "login here"/><br><br>
		
	</form>
	
</body>
</html>