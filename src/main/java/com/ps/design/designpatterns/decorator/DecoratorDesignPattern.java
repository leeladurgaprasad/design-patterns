package com.ps.design.designpatterns.decorator;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DecoratorDesignPattern implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(DecoratorDesignPattern.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        Car basicCar = new CarImpl("Nano Car",Car.type.BASIC);
        System.out.println("\n Basic Car : " + basicCar);

        Car suvCar = new CarImpl("Range Rover", Car.type.SUV);
        suvCar = new TurboCharger(suvCar);
        suvCar = new SportRim(suvCar,"Blue");
        System.out.println("\n My Custom Suv " + suvCar);

        Car sportsCar = new CarImpl("Lambo",Car.type.SPORTS);
        sportsCar = new Spoiler(sportsCar,"Track");
        sportsCar = new SportRim(sportsCar,"Red");
        sportsCar = new TwinTurboCharger(sportsCar);
        System.out.println("\n My SportsCar"+ sportsCar);
        System.out.println();

    }

}




interface Car {
    String name();
    double topSpeed();
    double acceleration();
    double millage();
    double cost();
    double weight();
    type type();
    enum type {
        SPORTS(3),SUV(2),BASIC(1);
        int value;
        type(int value) {
            this.value = value;
        }
    }
}


class CarImpl implements Car {
    String name;
    type type;
    double acceleration,millage,cost,topSpeed,weight;

    public CarImpl(String name, type type) {
        this(name, 120 * type.value, 2000 + 100 * type.value
                , 10.5 - (type.value * 2), 21.5 - (type.value* 2), 500000 * type.value,type);
    }

    public CarImpl(String name, double topSpeed, double weight, double acceleration, double millage, double cost, type type) {
        this.name = name;
        this.topSpeed = topSpeed;
        this.weight = weight;
        this.acceleration = acceleration;
        this.millage = millage;
        this.cost = cost;
        this.type = type;
    }

    @Override
    public type type() {
        return type;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public double topSpeed() {
        return topSpeed;
    }

    @Override
    public double acceleration() {
        return acceleration;
    }

    @Override
    public double millage() {
        return millage;
    }

    @Override
    public double cost() {
        return cost;
    }

    @Override
    public double weight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Car {" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", acceleration=" + acceleration +
                ", millage=" + millage +
                ", cost=" + cost +
                ", topSpeed=" + topSpeed +
                ", weight=" + weight +
                '}';
    }
}

abstract class CarPart implements Car {
    protected Car car;

    public CarPart(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Car {" +
                "name='" + name() + '\'' +
                ", type=" + type() +
                ", acceleration=" + acceleration() +
                ", millage=" + millage() +
                ", cost=" + cost() +
                ", topSpeed=" + topSpeed() +
                ", weight=" + weight() +
                '}';
    }
}

class Spoiler extends CarPart {

    private String spoilerType;

    public Spoiler(Car car, String spoilerType) {
        super(car);
        this.spoilerType = spoilerType;
    }

    @Override
    public String name() {
        return car.name() + " - " + spoilerType + " Spoiler ";
    }

    @Override
    public double topSpeed() {
        return car.topSpeed() + 10.5;
    }

    @Override
    public double acceleration() {
        return car.acceleration() - 0.5;
    }

    @Override
    public double millage() {
        return car.millage() - 0.5;
    }

    @Override
    public double cost() {
        return car.cost() + 50000;
    }

    @Override
    public double weight() {
        return car.weight() + 100;
    }

    @Override
    public type type() {
        return car.type();
    }
}

class SportRim extends CarPart {
    private String rimPaintColor;
    public SportRim(Car car,String rimPaintColor) {
        super(car);
        this.rimPaintColor = rimPaintColor;
    }

    @Override
    public String name() {
        return car.name() + " - " + rimPaintColor + " Color SportRim ";
    }

    @Override
    public double topSpeed() {
        return car.topSpeed();
    }

    @Override
    public double acceleration() {
        return car.acceleration();
    }

    @Override
    public double millage() {
        return car.millage();
    }

    @Override
    public double cost() {
        return car.cost() + 20000;
    }

    @Override
    public double weight() {
        return car.weight() + 200;
    }

    @Override
    public type type() {
        return car.type();
    }
}


class TurboCharger extends CarPart {
    public TurboCharger(Car car) {
        super(car);
    }

    @Override
    public String name() {
        return car.name() + " - Turbo charger ";
    }

    @Override
    public double topSpeed() {
        return car.topSpeed() + 40;
    }

    @Override
    public double acceleration() {
        return car.acceleration() - 0.6;
    }

    @Override
    public double millage() {
        return car.millage() - 2;
    }

    @Override
    public double cost() {
        return car.cost() + 2000000;
    }

    @Override
    public double weight() {
        return car.weight() + 500;
    }

    @Override
    public type type() {
        return car.type();
    }
}

class TwinTurboCharger extends TurboCharger {
    public TwinTurboCharger(Car car) {
        super(car);
    }

    @Override
    public String name() {
        return car.name() + " - Twin Turbo Charger ";
    }

    @Override
    public double acceleration() {
        return super.acceleration() - 0.3;
    }

    @Override
    public double cost() {
        return super.cost() + 10000;
    }
}
