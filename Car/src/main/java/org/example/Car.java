package org.example;

public class Car {
    private int battery;
    private int fuelCapacity;
    private double fuel;
    private double ratio;
    private boolean turnON;

    public Car(int battery, int fuelCapacity, double fuel, double ratio) {
        this.battery = battery;
        this.fuelCapacity = fuelCapacity;
        this.fuel = fuel;
        this.ratio = ratio;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(Long fuel) {
        this.fuel = fuel;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(Long ratio) {
        this.ratio = ratio;
    }

    public boolean turnOnOffCar(){
        if (turnON)
        {
            turnON=false;
            return false;
        }
        else
        {
            if (battery < 10)
            {
                System.out.println("Podladuj akumulator");
                return false;
            }
            if (fuel < 1)
            {
                System.out.println("Za mało paliwa by uruchomić");
                return false;
            }

            if (battery >=10 && fuel >=1)
            {
                System.out.println("Tututututu");
                turnON=true;
                return true;
            }
            return false;

        }
    }

    public boolean drive(int km){
        if (turnON)
        {
            if (ratio*(km*0.01)<fuel)
            {
                fuel-=ratio*(km*0.01);
                return true;
            }
            else
            {
                System.out.println("Dotankuj");
                return false;
            }
        }
        else
        {
            this.turnOnOffCar();
            if (turnON)
            {
                boolean result = this.drive(km);
                if (result)
                {
                    return true;
                }
                else
                {
                    return false;
                }

            }
            else
            {
                return false;
            }
        }
    }

    public boolean refuel(double liters)
    {
        if (liters<=0)
        {
            return false;
        }
        if (fuel+liters>fuelCapacity)
        {
            fuel=fuelCapacity;
            return true;
        }
        else
        {
            fuel+=liters;
            return true;
        }
    }
}
