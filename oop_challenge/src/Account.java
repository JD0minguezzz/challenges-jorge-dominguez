public class Account {

    public int id;
    public double balance;

    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public void withdrawal(double value) {
        if (balance >= value) {
            balance = balance - value;
        } else {
            System.out.println("Not enough balance to make withdrawal.");
        }
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double value) { balance = balance + value; }

}
