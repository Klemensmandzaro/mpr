import org.example.Car;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    @Test
    public void carTurnOnWhenItHasBatteryAndFuel()
    {
        Car car = new Car(20, 30, 30, 5);

        boolean result = car.turnOnOffCar();

        assertTrue(result);
    }

    @Test
    public void carNotTurnOnWhenItHasBatteryButNoFuel()
    {
        Car car = new Car(20, 30, 0, 5);

        boolean result = car.turnOnOffCar();

        assertFalse(result);
    }

    @Test
    public void carNotTurnOnWhenItHasNoBatteryButHasFuel()
    {
        Car car = new Car(0, 30, 30, 5);

        boolean result = car.turnOnOffCar();

        assertFalse(result);
    }

    @Test
    public void carNotTurnOnWhenItHasNoBatteryAndNoFuel()
    {
        Car car = new Car(0, 30, 0, 5);

        boolean result = car.turnOnOffCar();

        assertFalse(result);
    }


    @Test
    public void carRefuelWhenItHasEnoughtSpace()
    {
        Car car = new Car(20, 30, 5, 5);

        boolean result = car.refuel(20);

        assertTrue(result);
    }

    @Test
    public void carRefuelWhenItHasNotEnoughtSpace()
    {
        Car car = new Car(20, 30, 5, 5);

        boolean result = car.refuel(30);

        assertTrue(result);
        assertEquals(car.getFuelCapacity(), car.getFuel());
    }

    @Test
    public void carNotRefuelWhenItYouTankZero()
    {
        Car car = new Car(20, 30, 5, 5);

        boolean result = car.refuel(0);

        assertFalse(result);
    }

    @Test
    public void carNotRefuelWhenItYouTankMinus()
    {
        Car car = new Car(20, 30, 5, 5);

        boolean result = car.refuel(-10);

        assertFalse(result);
    }

    @Test
    public void carDriveWhenItHasFuelAndBatteryAndDistanceIsOk()
    {
        Car car = new Car(20, 30, 25, 5);
        boolean result = car.drive(300);
        assertTrue(result);
    }

    @Test
    public void carDriveWhenItHasFuelAndBatteryAndDistanceIsNotOk()
    {
        Car car = new Car(20, 30, 25, 5);
        boolean result = car.drive(600);
        assertFalse(result);
    }

    @Test
    public void carNotDriveWhenItHasNoFuelButHasBatteryAndDistanceIsOk()
    {
        Car car = new Car(20, 30, 0, 5);
        boolean result = car.drive(200);
        assertFalse(result);
    }

    @Test
    public void carNotDriveWhenItHasFuelButNoBatteryButDistanceIsOk()
    {
        Car car = new Car(0, 30, 25, 5);
        boolean result = car.drive(300);
        assertFalse(result);
    }

    @Test
    public void carNotDriveWhenItHasNoFuelAndNoBatteryButDistanceIsOk()
    {
        Car car = new Car(0, 30, 0, 5);
        boolean result = car.drive(300);
        assertFalse(result);
    }

    @Test
    public void carNotDriveWhenItHasFuelAndNoBatteryButDistanceIsNotOk()
    {
        Car car = new Car(0, 30, 20, 5);
        boolean result = car.drive(600);
        assertFalse(result);
    }

    @Test
    public void carNotDriveWhenItHasNoFuelButHasBatteryButDistanceIsNotOk()
    {
        Car car = new Car(50, 30, 0, 5);
        boolean result = car.drive(600);
        assertFalse(result);
    }

    @Test
    public void carNotDriveWhenItHasNoFuelAndNoBatteryANDDistanceIsNotOk()
    {
        Car car = new Car(0, 30, 0, 5);
        boolean result = car.drive(600);
        assertFalse(result);
    }

}
