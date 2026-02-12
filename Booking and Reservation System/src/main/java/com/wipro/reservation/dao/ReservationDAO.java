package com.wipro.reservation.dao;

import com.wipro.reservation.bean.ReservationBean;
import com.wipro.reservation.util.DBUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    public String createRecord(ReservationBean reservationBean) {
        try 
        {
        	 Connection con = DBUtil.getDBConnection();
        	 String query = "INSERT INTO RESERVATION_TB VALUES(?,?,?,?,?,?,?)";
             PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, reservationBean.getRecordId());
            ps.setString(2, reservationBean.getCustomerName());
            ps.setString(3, reservationBean.getBookingType());
            ps.setDate(4, new java.sql.Date(reservationBean.getBookingDate().getTime()));
            ps.setString(5, reservationBean.getConfirmationNo());
            ps.setInt(6, reservationBean.getAmount());
            ps.setString(7, reservationBean.getRemarks());

            int rows = ps.executeUpdate();
            if(rows > 0)
            {
            	return "SUCCESS";
            }
            else
            {
            	return "FAIL";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "FAIL";
        }
    }
    public ReservationBean fetchRecord(String customerName, java.util.Date bookingDate) {
        ReservationBean bean = null;
        try 
        {
        	Connection con = DBUtil.getDBConnection();
            String query = "SELECT * FROM RESERVATION_TB WHERE CUSTOMERNAME=? AND TRUNC(BOOKING_DATE)=TRUNC(?)";

             PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, customerName);
            ps.setDate(2, new java.sql.Date(bookingDate.getTime()));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bean = new ReservationBean();
                bean.setRecordId(rs.getString("RECORDID"));
                bean.setCustomerName(rs.getString("CUSTOMERNAME"));
                bean.setBookingType(rs.getString("BOOKINGTYPE"));
                bean.setBookingDate(rs.getDate("BOOKING_DATE"));
                bean.setConfirmationNo(rs.getString("CONFIRMATIONNO"));
                bean.setAmount(rs.getInt("AMOUNT"));
                bean.setRemarks(rs.getString("REMARKS"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public String generateRecordID(String customerName, java.util.Date bookingDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String datePart = sdf.format(bookingDate);
        String namePart = customerName.substring(0,2).toUpperCase();
        int seq = 10; 
        return datePart + namePart + String.format("%02d", seq);
    }

    public boolean recordExists(String customerName, java.util.Date bookingDate) {
        return fetchRecord(customerName, bookingDate) != null;
    }

    public List<ReservationBean> fetchAllRecords() {
        List<ReservationBean> list = new ArrayList<>();

        try 
        {
        	Connection con = DBUtil.getDBConnection();
        	String query = "SELECT * FROM RESERVATION_TB";
             PreparedStatement ps = con.prepareStatement(query);

             ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ReservationBean bean = new ReservationBean();
                bean.setRecordId(rs.getString("RECORDID"));
                bean.setCustomerName(rs.getString("CUSTOMERNAME"));
                bean.setBookingType(rs.getString("BOOKINGTYPE"));
                bean.setBookingDate(rs.getDate("BOOKING_DATE"));
                bean.setConfirmationNo(rs.getString("CONFIRMATIONNO"));
                bean.setAmount(rs.getInt("AMOUNT"));
                bean.setRemarks(rs.getString("REMARKS"));
                list.add(bean);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
