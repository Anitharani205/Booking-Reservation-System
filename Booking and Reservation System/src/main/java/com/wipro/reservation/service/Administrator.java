package com.wipro.reservation.service;

import com.wipro.reservation.bean.ReservationBean;
import com.wipro.reservation.dao.ReservationDAO;

import java.util.List;

public class Administrator {

    ReservationDAO dao = new ReservationDAO();

    public String addRecord(ReservationBean bean) {
        if(bean == null || bean.getCustomerName() == null || bean.getBookingDate() == null)
            return "Invalid Input";

        if(bean.getCustomerName().length() < 2)
            return "Invalid Customer Name";

        if(dao.recordExists(bean.getCustomerName(), bean.getBookingDate()))
            return "Already Exists";

        bean.setRecordId(dao.generateRecordID(bean.getCustomerName(), bean.getBookingDate()));
        return dao.createRecord(bean);
    }

    public ReservationBean viewRecord(String customerName, java.util.Date bookingDate) {
        return dao.fetchRecord(customerName, bookingDate);
    }

    public List<ReservationBean> viewAllRecords() {
        return dao.fetchAllRecords();
    }
}
