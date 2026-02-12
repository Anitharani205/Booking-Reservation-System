<%@ page import="com.wipro.reservation.bean.ReservationBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reservation</title>
</head>
<body>

<%
ReservationBean bean = (ReservationBean) request.getAttribute("reservation");
String message = (String) request.getAttribute("message");

if(bean != null){
%>
<h3>Reservation Details</h3>
Record ID: <%= bean.getRecordId() %> <br>
Customer Name: <%= bean.getCustomerName() %> <br>
Booking Type: <%= bean.getBookingType() %> <br>
Booking Date: <%= bean.getBookingDate() %> <br>
Confirmation No: <%= bean.getConfirmationNo() %> <br>
Amount: <%= bean.getAmount() %> <br>
Remarks: <%= bean.getRemarks() %> <br>
<% } else { %>
<h3><%= (message != null ? message : "No matching records exists! Please try again!") %></h3>
<% } %>

<br>
<a href="Menu.html">Back to Menu</a>

</body>
</html>
