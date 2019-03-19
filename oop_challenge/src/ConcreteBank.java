import java.util.ArrayList;

public class ConcreteBank implements Bank, ObserverDispatcher {

    private ArrayList<ArrayList<Agent>> agents;
    private Dispatcher dispatcher;
    private ArrayList<Customer> currentClients;

    public ConcreteBank(int numCashiers, int numSupervisors, int numDirectors) {
        agents = new ArrayList<>();
        dispatcher = Dispatcher.getInstance();
        currentClients = new ArrayList<>();
        dispatcher.registerObserver(this);
        assignCashier(numCashiers);
        assignSupervisor(numSupervisors);
        assignDirector(numDirectors);
    }

    private void assignCashier(int numCashiers) {
        ArrayList<Agent> agent = new ArrayList<>();
        for (int i = 0; i < numCashiers; i++) {
            agent.add(new Cashier(1000 * (i + 1)));
            agent.get(i).registerObserver(dispatcher);
        }
        agents.add(agent);
    }

    private void assignSupervisor(int numSupervisor) {
        ArrayList<Agent> agent = new ArrayList<>();
        for (int i = 0; i < numSupervisor; i++) {
            agent.add(new Supervisor(100 * (i + 1)));
            agent.get(i).registerObserver(dispatcher);
        }
        agents.add(agent);
    }

    private void assignDirector(int numDirector) {
        ArrayList<Agent> agent = new ArrayList<>();
        for (int i = 0; i < numDirector; i++) {
            agent.add(new Cashier(10 * (i + 1)));
            agent.get(i).registerObserver(dispatcher);
        }
        agents.add(agent);
    }

    public void receiveClients (ArrayList<Customer> clients){
        currentClients.addAll(clients);
        sendClientToDispatcher();
    }

    private void sendClientToDispatcher() {
        if (!currentClients.isEmpty() && dispatcher.getAvailability()) {
            if (currentClients.size() >= 10) {
                dispatcher.attend(new ArrayList<>(currentClients.subList(0, 10)), this.agents);
            } else {
                dispatcher.attend(currentClients, this.agents);
            }
        }
    }

    @Override
    public void update(){
        if (currentClients.size() >= 10) {
            currentClients.subList(0, 10).clear();
        } else {
            currentClients.clear();
        }
        sendClientToDispatcher();
    }

}
