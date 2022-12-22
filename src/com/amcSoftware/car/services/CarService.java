package com.amcSoftware.car.services;

import com.amcSoftware.car.Car;
import com.amcSoftware.carBooking.CarBooking;
import com.amcSoftware.carBooking.CarBookingDao;
import com.amcSoftware.car.dao.CarDao;
import com.amcSoftware.user.services.LocateUser;
import com.amcSoftware.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarService {

    private static List<Car> unavailableCars = new ArrayList<>();
    private static List<User> bookedUsers = new ArrayList<>();

    private LocateCar locateCar;
    private LocateUser locateUser;

    public CarService(LocateCar locateCar,LocateUser locateUser ) {
        this.locateCar = locateCar;
        this.locateUser = locateUser;
    }

    public void bookCar(String regNumber, UUID userId) {
        try {
            Car car = this.locateCar.getCar(regNumber);
            User user = this.locateUser.getUser(userId);
            if(isBooked(user)) {
                System.out.println("❌ This user already has an existing booking. Only one booking per user allowed!");
            }else {
                UUID bookingRef = UUID.randomUUID();
                CarBooking carBooking = new CarBooking(bookingRef, user, car, false);
                CarBookingDao.getCarBookings().add(carBooking);
                System.out.println("🎉 Successfully booked car with reg number " + car.getRegNumber() + " for user " +
                        car + "\nBooking ref: " + bookingRef);
                bookedUsers.add(user);
                unavailableCars.add(car);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean isBooked(User user) {
        return bookedUsers.contains(user);
    }

    public void getAllBookings() {
        try {
            for(int i = 0; i < CarBookingDao.getCarBookings().size(); i++) {
                System.out.println(CarBookingDao.getCarBookings().get(i));
            }
            if(CarBookingDao.getCarBookings().size() == 0) {
                System.out.println("No bookings available 😕");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAvailableCars() {
        try {

            for (Car car: carsWithNoBookings()) {
                System.out.println(car);
            }

            if(carsWithNoBookings().size() == 0) {
                System.out.println("❌ no cars are available for renting");
            }
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
    }

    public List<Car> carsWithNoBookings() {
        List<Car> availableCars = new ArrayList<>();
        if(unavailableCars.size() == 0) {
            availableCars.addAll(CarDao.getCars());
        } else {
            for (int i = 0; i < CarDao.getCars().size(); i++) {
                if(!unavailableCars.contains(CarDao.getCars().get(i))) {
                    availableCars.add(CarDao.getCars().get(i));
                }
            }
        }
        return availableCars;
    }

    public void getAvailableElectricCars() {
        try {
            int electricCars = 0;

            for (Car car : carsWithNoBookings()) {
                if((car.isElectric())) {
                    System.out.println(car);
                    electricCars++;
                }
            }

            if(electricCars == 0) {
                System.out.println("❌ No electric cars available for renting");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
