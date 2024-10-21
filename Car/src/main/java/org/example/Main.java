package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Car car = new Car(70, 50, 20, 8);
        car.refuel(25);
        car.turnOnOffCar();
        System.out.println(car.drive(50));
        System.out.println(car);
    }
}