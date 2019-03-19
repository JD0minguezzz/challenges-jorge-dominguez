import java.util.ArrayList;
import java.util.Random;

public abstract class Agent {

    protected int id;
    private boolean availability;
    private ArrayList<ObserverAgent> observers = new ArrayList<>();
    private Customer client;

    public void setAvailability(boolean state) { availability = state; }

    public boolean getAvailability() { return availability; }

    public void attendClient(Customer client) {
        this.client = client;
        setAvailability(false);
        client.getTransaction().performTransaction(client, this.id);
        setAvailability(true);
        notifyAllObservers();
    }

    public void registerObserver(ObserverAgent observer) { observers.add(observer); }

    public void removeObserver(ObserverAgent observer) {
        int i = observers.indexOf(observer);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    public void notifyAllObservers(){
        for (ObserverAgent observer: observers) {
            observer.update();
        }
    }

}
