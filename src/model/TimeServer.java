package model;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Observable;
import java.util.Observer;

public class TimeServer extends Observable {

    Timer timer;
    int timeState;

    public TimeServer(){
        super();
        timeState=0;
        timer=new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                timeState++;
                notifyAllObservers();
            }
        };
        timer.schedule(task, 0, 1000);
    }

    public void notifyAllObservers() {
        setChanged();
        super.notifyObservers(timeState);
    }

    public void attach(Observer obs) {
        super.addObserver(obs);
    }

    public void detach(Observer obs) {
        super.deleteObserver(obs);
    }

    public void detachAll() {
        super.deleteObservers();
    }

    public int getState() {
        return timeState;
    }
}

