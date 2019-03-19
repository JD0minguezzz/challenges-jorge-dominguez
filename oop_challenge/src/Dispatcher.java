import java.util.ArrayList;
import java.util.Random;

public class Dispatcher extends Agent implements ObserverAgent, Runnable {

    private static Dispatcher uniqueInstance;
    private static ArrayList<ObserverDispatcher> observers;
    private static ArrayList<Customer> currentClients;
    private static ArrayList<ArrayList<Agent>> currentAgents;
    private static Customer currentClient;
    private static Agent currentAgent;

    private Dispatcher() {
        setAvailability(true);
        observers = new ArrayList<>();
        currentClients = new ArrayList<>();
    }

    public static Dispatcher getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Dispatcher();
        }
        return uniqueInstance;
    }

    public void attend (ArrayList<Customer> clients, ArrayList<ArrayList<Agent>> agents){
        currentClients = clients;
        currentAgents = agents;
        setAvailability(false);
        sendToAgents();
    }

    public void sendToAgents() {
        boolean cashierAvailability;
        boolean supervisorAvailability;
        boolean directorAvailability;

        for (int i = 0 ; i<currentClients.size(); i++) {
            cashierAvailability = agentsGroupIsAvailable(currentClients.get(i), currentAgents.get(0));
            if(!cashierAvailability){
                supervisorAvailability = agentsGroupIsAvailable(currentClients.get(i), currentAgents.get(1));
                if(!supervisorAvailability){
                    directorAvailability = agentsGroupIsAvailable(currentClients.get(i), currentAgents.get(2));
                    if (!directorAvailability){break;}
                }
            }
        }

        if (currentClients.isEmpty()){
            setAvailability(true);
            notifyAllObservers();
        }
    }


    private boolean agentsGroupIsAvailable(Customer client, ArrayList<Agent> agents) {
        for (int i = 0; i < agents.size(); i++) {
            if (agents.get(i).getAvailability()) {
                agents.get(i).setAvailability(false);
                //Thread t = new Thread(this);
                currentClient = client;
                currentAgent = agents.get(i);
                //t.start();
                agents.get(i).attendClient(client);

                return true;
            }
        }
        return false;
    }

    public void run() {

        try {
            currentAgent.attendClient(currentClient);

            Random r = new Random();
            int timeToWait = r.ints(10000, 15000).limit(1).findFirst().getAsInt();
            Thread.sleep(timeToWait);
            //System.out.println("Hola desde un thread " + timeToWait);
            currentAgent.setAvailability(true);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void registerObserver(ObserverDispatcher observer) { observers.add(observer); }

    public void removeObserver(ObserverDispatcher observer) {
        int i = observers.indexOf(observer);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    public void notifyAllObservers(){
        for (ObserverDispatcher observer: observers) {
            observer.update();
        }
    }

    @Override
    public void update(){
        currentClients.remove(currentClient);
        currentAgent.setAvailability(true);
        sendToAgents();
    }

}
