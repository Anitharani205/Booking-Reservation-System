<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View All Reservations</title>
</head>
<body>

<h2>View All Reservations</h2>

<form action="MainServlet" method="post">
    <input type="hidden" name="operation" value="viewAllRecords">
    <input type="submit" value="View All Records">
</form>

<br>
<a href="Menu.html">Back to Menu</a>

</body>
</html>
