package com.ps.design.designpatterns.observable;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
public class ObservabaleDesignPattern implements CommandLineRunner{
    public static void main(String[] args) {
        SpringApplication.run(ObservabaleDesignPattern.class,args);
    }

    @Override
    public void run(String... args) throws Exception {

        Observable whetherFeed = new WhetherFeed();
        Observer whetherBarDisplay = new WhetherBarDisplay();
        whetherFeed.subscribe(whetherBarDisplay);


        Thread whetherThread = new Thread((WhetherFeed) whetherFeed);
        whetherThread.start();

    }


}



interface Observable {
    boolean subscribe(Observer observer);
    boolean remove(Observer observer);
    void notifyObservers();
    List<Result> getResults();

}

interface Observer {
    void update(Observable observable);
}

interface Result {
    Object getResult();
}

abstract class AbstractObservable implements Observable {
    List<Observer> observerList = new LinkedList<>();
    List<Result> resultList = new LinkedList<>();


    @Override
    public boolean subscribe(Observer observer) {
        return observerList.add(observer);
    }

    @Override
    public boolean remove(Observer observer) {
        return observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observerList.stream().forEach(observer -> observer.update(this));
    }

    @Override
    public List<Result> getResults() {
        return resultList;
    }
}


// Whether update
class WhetherFeed extends AbstractObservable implements Runnable {
    Humidity humidityResult = new Humidity();

    WhetherFeed() {
        resultList.add(humidityResult);
    }

    @Override
    public void run() {
        while (true) {
            try {
                 Thread.sleep(1500);
                 humidityResult.updateHumidityNow();
                 notifyObservers();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class Humidity implements Result {

    Integer humidityValue;

    Humidity() {
        humidityValue = 0;
    }

    @Override
    public Object getResult() {
        return humidityValue;
    }

    public void updateHumidityNow() {
        humidityValue = (int) (Math.random() * 20);
    }

}

class WhetherBarDisplay implements Observer {
    private static final String BAR_CHAR = "=";

    @Override
    public void update(Observable observable) {
        observable.getResults().forEach(result -> display(result));
    }

    private void display(Result result) {
        System.out.println(result.getClass().getSimpleName() + " " + displayBar(result));
    }

    private String displayBar(Result result) {
        StringBuilder sb = new StringBuilder();
        int val = (Integer) result.getResult();
        for(int i=0 ; i <= val; i++)
        sb.append(BAR_CHAR);

        sb.append(":");
        sb.append(val);
        return sb.toString();
    }
}