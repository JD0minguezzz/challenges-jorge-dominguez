public interface Transaction {
    double getTransactionValue();
    String getTransactionType();
    void performTransaction(Customer client, int agentId);
    void sendMessage();
}
