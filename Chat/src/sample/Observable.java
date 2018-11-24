package sample;

public interface Observable {
    void addObserver(Observer o);
    void notifyObservers(String msg);
}
