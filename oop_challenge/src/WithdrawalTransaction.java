import java.util.ArrayList;

public class WithdrawalTransaction implements Transaction, Topic {
    public ArrayList<ObserverModules> observers;
    private Customer client;
    private int agentId;
    private double transactionValue;
    private final String transactionType = "WithdrawalTransaction";

    public WithdrawalTransaction(double transactionValue) {
        this.transactionValue = transactionValue;
        observers = new ArrayList<>();
        ObserverModules audit = new Audit();
        ObserverModules marketingService = new MarketingService();
        registerObserver(audit);
        registerObserver(marketingService);
    }

    @Override
    public void performTransaction(Customer client, int idAgent) {
        client.getAccount().withdrawal(this.transactionValue);
        this.agentId = idAgent;
        this.client = client;
        System.out.println("Withdrawal successful.");
        sendMessage();
    }

    @Override
    public double getTransactionValue() { return transactionValue; }

    @Override
    public void registerObserver(ObserverModules o) { observers.add(o); }

    @Override
    public void sendMessage() {
        Message message = new ClientMessage();
        message.setMessage(client, agentId);
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).receiveMessage(message);
        }
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

