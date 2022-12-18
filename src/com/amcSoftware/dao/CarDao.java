package com.amcSoftware.dao;

import com.amcSoftware.Brand;
import com.amcSoftware.Car;

import java.math.BigDecimal;

public class CarDao {
    private static Car[] cars;

    static {
        cars = new Car[] {
                new Car("1234", BigDecimal.valueOf(150.59), Brand.JAGUAR,false),
                new Car("8537", BigDecimal.valueOf(59.99), Brand.HONDA,false),
                new Car("8789", BigDecimal.valueOf(59.99), Brand.HONDA,true),
        };
    }

    public static Car[] getCars() {
        return cars;
    }

}
