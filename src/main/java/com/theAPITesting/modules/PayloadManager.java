package com.theAPITesting.modules;

import com.google.gson.Gson;
import com.theAPITesting.pojos.Booking;
import com.theAPITesting.pojos.BookingDates;
import com.theAPITesting.pojos.BookingResponse;

public class PayloadManager {
    // Converting the JAVA object to the String
    Gson gson;
    public String createPayloadBookingAsString() {

        Booking booking = new Booking();
        booking.setFirstname("Garima");
        booking.setLastname("Naik");
        booking.setTotalprice(1500);
        booking.setDepositpaid(true);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2019-01-01");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");
        System.out.println(booking);

        gson = new Gson();
        String jsonStringpayload = gson.toJson(booking);
        System.out.println(jsonStringpayload);
        return jsonStringpayload;
    }
    // Converting the String to the JAVA Object
    public BookingResponse bookingResponseJava(String responseString) {
        gson = new Gson();
        BookingResponse bookingResponse = gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;
    }
}
