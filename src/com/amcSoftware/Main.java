package com.amcSoftware;

import com.amcSoftware.services.CarService;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        CarService carService = new CarService();
        Scanner scanner = new Scanner(System.in);

        boolean isExit = false;

        menu();
        while(!isExit) {
            int value = Integer.parseInt(scanner.nextLine());
            switch (value) {
                case 1:
                    carService.getAvailableCars();
                    System.out.println("➡️ select car reg number");
                    String regNumber = scanner.nextLine();
                    carService.getUsers();
                    System.out.println("➡️ select user id");
                    String userId = scanner.nextLine();
                    String s = userId.replace("-", "");
                    UUID uuid = new UUID(
                            new BigInteger(s.substring(0,16), 16).longValue(),
                            new BigInteger(s.substring(16),16).longValue());
                    carService.bookCar(regNumber,uuid);
                    menu();
                    break;
                case 2:
                    carService.getUsers();
                    System.out.println("➡️ select user id");
                    String input = scanner.nextLine();
                    s = input.replace("-", "");
                    uuid = new UUID(
                            new BigInteger(s.substring(0,16), 16).longValue(),
                            new BigInteger(s.substring(16),16).longValue());

                    carService.getUserBookedCars(uuid);
                    System.out.println("\n");
                    menu();
                    break;
                case 3:
                    carService.getAllBookings();
                    System.out.println("\n");
                    menu();
                    break;
                case 4:
                    carService.getAvailableCars();
                    System.out.println("\n");
                    menu();
                    break;
                case 5:
                    carService.getAvailableElectricCars();
                    System.out.println("\n");
                    menu();
                    break;
                case 6:
                    carService.getUsers();
                    System.out.println("\n");
                    menu();
                    break;
                case 7:
                    isExit = true;
                    break;
                default:
                    System.out.println(value + " not a valid option\n\n");
                    menu();
            }
        }
    }

    private static void menu() {
        System.out.println
                ("1️⃣ - Book Car\n" +
                "2️⃣ - View All User Booked Cars\n" +
                "3️⃣ - View All Bookings\n" +
                "4️⃣ - View Available Cars\n" +
                "5️⃣ - View Available Electric Cars\n" +
                "6️⃣ - View all users\n" +
                "7️⃣ - Exit");
    }
}
