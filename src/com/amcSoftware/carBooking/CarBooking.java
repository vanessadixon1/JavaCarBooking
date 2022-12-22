package com.amcSoftware.carBooking;

import com.amcSoftware.car.Car;
import com.amcSoftware.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class CarBooking {
    private UUID bookingId;
    private User users;
    private Car car;
    private boolean isCanceled;
    private LocalDateTime bookingTime;

    public CarBooking(UUID bookingId, User users, Car car, boolean isCanceled) {
        this.bookingId = bookingId;
        this.users = users;
        this.car = car;
        this.isCanceled = isCanceled;
        this.bookingTime = LocalDateTime.now();
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    @Override
    public String toString() {
        return "CarBooking{" +
                "bookingId=" + bookingId +
                ", users=" + users +
                ", car=" + car +
                ", isCanceled=" + isCanceled +
                ", bookingTime=" + bookingTime +
                '}';
    }
}
