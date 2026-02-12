package com.wipro.reservation.servlets;

import com.wipro.reservation.bean.ReservationBean;
import com.wipro.reservation.service.Administrator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    Administrator admin = new Administrator();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");

        try {
        	
            if("newRecord".equals(operation)) 
            {
                ReservationBean bean = new ReservationBean();
                bean.setCustomerName(request.getParameter("customerName"));
                bean.setBookingType(request.getParameter("bookingType"));
                bean.setConfirmationNo(request.getParameter("confirmationNo"));
                bean.setAmount(Integer.parseInt(request.getParameter("amount")));
                bean.setRemarks(request.getParameter("remarks"));

                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("bookingDate"));
                
                bean.setBookingDate(date);

                String result = admin.addRecord(bean);
                
                if("FAIL".equals(result) || result.contains("Invalid")) {
                	
                    response.sendRedirect("Error.html");
                    
                } else {
                	
                    response.sendRedirect("Success.html");
                }

            } else if("viewRecord".equals(operation)) {
                String customerName = request.getParameter("customerName");
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("bookingDate"));
                
                ReservationBean bean = admin.viewRecord(customerName, date);

                if(bean == null) {
                	
                    request.setAttribute("message", "No matching records exist! Please try again!");
                } 
                else {
                	
                    request.setAttribute("reservation", bean);
                }
                
                RequestDispatcher rd = request.getRequestDispatcher("DisplayReservation.jsp");
                rd.forward(request, response);

            }
            else if("viewAllRecords".equals(operation)) {
            	
                List<ReservationBean> list = admin.viewAllRecords();
                if(list.isEmpty()) {
                    request.setAttribute("message", "No records available!");
                    
                } else {
                    request.setAttribute("list", list);
                    
                }
                RequestDispatcher rd = request.getRequestDispatcher("DisplayAllReservations.jsp");
                rd.forward(request, response);
            }
            
        } catch(Exception e) {
        	
            e.printStackTrace();
            response.sendRedirect("Error.html");
        }
    }
}
