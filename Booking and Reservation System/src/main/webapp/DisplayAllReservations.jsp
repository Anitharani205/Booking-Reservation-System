<%@ page import="java.util.*, com.wipro.reservation.bean.ReservationBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reservations</title>
</head>
<body>

<%
List<ReservationBean> list = (List<ReservationBean>) request.getAttribute("list");
String message = (String) request.getAttribute("message");

if(list != null && !list.isEmpty()){
%>
<h3>All Reservations</h3>
<% for(ReservationBean bean : list){ %>
Record ID: <%= bean.getRecordId() %> <br>
Customer Name: <%= bean.getCustomerName() %> <br>
Booking Type: <%= bean.getBookingType() %> <br>
Booking Date: <%= bean.getBookingDate() %> <br>
Confirmation No: <%= bean.getConfirmationNo() %> <br>
Amount: <%= bean.getAmount() %> <br>
Remarks: <%= bean.getRemarks() %> <br>
<hr>
<% } %>
<% } else { %>
<h3><%= (message != null ? message : "No records available!") %></h3>
<% } %>

<br>
<a href="Menu.html">Back to Menu</a>

</body>
</html>
