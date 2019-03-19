import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Message {
    private int customerId;
    private String customerEmail;
    private int agentId;
    private int accountId;
    private Date transactionDate;
    private Double transactionValue;
    private String transactionType;

    public Message() { }

    public void setMessage(Customer client, int agentId){
        this.customerId = client.getId();
        this.customerEmail = client.getEmail();
        this.agentId = agentId;
        this.accountId = client.getAccount().getId();
        Date date = new Date();
        this.transactionDate = date;
        this.transactionValue = client.getTransaction().getTransactionValue();
        this.transactionType = client.getTransaction().getTransactionType();
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getAccountId() {
        return accountId;
    }

    public Double getTransactionValue() {
        return transactionValue;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

}
