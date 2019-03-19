import java.util.ArrayList;

public class CustomerServiceTransaction implements Transaction, Topic {

    public ArrayList<ObserverModules> observers;
    private Customer client;
    private int agentId;
    private double transactionValue;
    private final String transactionType = "CustomerServiceTransaction";


    public CustomerServiceTransaction(double transactionValue) {
        this.transactionValue = transactionValue;
        observers = new ArrayList<>();
        ObserverModules audit = new Audit();
        ObserverModules marketingService = new MarketingService();
        registerObserver(audit);
        registerObserver(marketingService);
    }

    @Override
    public void performTransaction(Customer client, int agentId) {
        this.agentId = agentId;
        this.client = client;
        System.out.println("Customer service transaction successful.");
        sendMessage();
        ObserverModules audit = new Audit();
        ObserverModules marketingService = new MarketingService();
        registerObserver(audit);
        registerObserver(marketingService);
    }

    @Override
    public double getTransactionValue() {
        return transactionValue;
    }

    public void sendMessage() {
        Message message = new ClientMessage();
        message.setMessage(client, agentId);
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).receiveMessage(message);
        }
    }

    @Override
    public void registerObserver(ObserverModules o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(ObserverModules o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    @Override
    public String getTransactionType() {
        return transactionType;
    }

}
