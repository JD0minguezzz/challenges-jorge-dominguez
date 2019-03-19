public class Client implements Customer {

    private Account account;
    private Transaction transaction;
    private String email, name;
    private int id;

    public Client(Account account, Transaction transaction, int id, String email, String name) {
        this.account = account;
        this.transaction = transaction;
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public void setAccount(Account a) {
        account = a;
    }

    public Account getAccount () {return account;}

    public int getId() {
        return id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}