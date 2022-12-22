package com.amcSoftware.carBooking;


import java.util.ArrayList;
import java.util.List;

public class CarBookingDao {
    private static List<CarBooking> carBookings;

    static {
        carBookings = new ArrayList<>();
    }

    public static List<CarBooking> getCarBookings() {
        return carBookings;
    }
}

