package com.viet.builder;

public class Main {
    public static void main(String[] args) {
        Car car = new Car.CarBuilder("SUV")
                .setBodyStyle("Sporty")
                .setPower("300 HP")
                .setEngine("V8")
                .setBreaks("ABS")
                .setSeats("Leather")
                .setWindows("Tinted")
                .setFuelType("Petrol")
                .build();

        System.out.println(car);
    }
}
