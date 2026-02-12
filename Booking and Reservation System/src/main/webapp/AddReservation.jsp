<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reservation</title>
</head>
<body>

<h2>Add Reservation</h2>

<form action="MainServlet" method="post">

    <input type="hidden" name="operation" value="newRecord">
    Customer Name:
     <input type="text" name="customerName"><br><br>
    Booking Type:
     <input type="text" name="bookingType"><br><br>
    Booking Date: 
    <input type="date" name="bookingDate"><br><br>
    Confirmation No: 
    <input type="text" name="confirmationNo"><br><br>
    Amount:
     <input type="text" name="amount"><br><br>
    Remarks:
     <input type="text" name="remarks"><br><br>
    <input type="submit" value="Add Record">
</form>

<br>
<a href="Menu.html">Back to Menu</a>

</body>
</html>
