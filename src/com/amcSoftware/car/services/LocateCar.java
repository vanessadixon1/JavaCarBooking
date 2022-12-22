package com.amcSoftware.car.services;

import com.amcSoftware.car.Car;
import com.amcSoftware.car.dao.CarDao;
import com.amcSoftware.car.interfaces.ILocateCar;

public class LocateCar implements ILocateCar {

    @Override
    public Car getCar(String regNumber) {
       Car car = null;
        try {
            for(int i = 0; i < CarDao.getCars().size(); i++) {
                if(CarDao.getCars().get(i).getRegNumber().equals(regNumber)) {
                    car = CarDao.getCars().get(i);
                }
            }
        } catch (Exception e) {

        }
        return car;
    }
}
