import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Customer> clients = new ArrayList<>();
        Customer client1 = new Client(new Account(1, 2389512), new DepositTransaction(100000), 1, "sa@gmail.com", "Sofía Álvarez");
        Customer client2 = new Client(new Account(2, 3412344), new WithdrawalTransaction(123800), 2, "jp@outlook.com", "Juan Pérez");
        Customer client3 = new Client(new Account(3, 223000), new CustomerServiceTransaction(0), 3, "ct@gmail.com", "Camilo Torres");
        Customer client4 = new Client(new Account(4, 112341), new DepositTransaction(123800), 4, "ac@gmail.com", "Andrea Castro");
        Customer client5 = new Client(new Account(5, 134534), new DepositTransaction(123800), 5, "cb@gmail.com", "Camila Beltrán");
        Customer client6 = new Client(new Account(6, 212312), new DepositTransaction(100000), 6, "ko@gmail.com", "Kevin Ore");
        Customer client7 = new Client(new Account(7, 1457888), new DepositTransaction(123800), 7, "acano@gmail.com", "Alejandro Cano");
        Customer client8 = new Client(new Account(8, 21281848), new DepositTransaction(100000), 8, "jt@gmail.com", "Jhon Torres");
        Customer client9 = new Client(new Account(9, 13789492), new DepositTransaction(123800), 9, "jd@gmail.com", "Jorge Domínguez");
        Customer client10 = new Client(new Account(10, 91274111), new DepositTransaction(123800), 10, "po@gmail.com", "Paula Ortiz");
        Customer client11 = new Client(new Account(11, 7561284), new DepositTransaction(100000), 11, "acortes@gmail.com", "Alejandra Cortés");
        Customer client12 = new Client(new Account(12, 691274), new DepositTransaction(123800), 12, "sa@gmail.com", "Santiago Arredondo");
        Customer client13 = new Client(new Account(13, 123458), new DepositTransaction(100000), 13, "ss@gmail.com", "Sebastián Sierra");
        Customer client14 = new Client(new Account(14, 113249), new DepositTransaction(123800), 14, "is@gmail.com", "Ivonne Soler");
        Customer client15 = new Client(new Account(15, 123466), new DepositTransaction(123800), 15, "ag@gmail.com", "Ángela Gómez");
        Customer client16 = new Client(new Account(16, 92398124), new DepositTransaction(100000), 16, "cg@gmail.com", "Camilo Gómez");

        clients.add(client1);
        clients.add(client2);
        clients.add(client3);
        clients.add(client4);
        clients.add(client5);
        clients.add(client6);
        clients.add(client7);
        clients.add(client8);
        clients.add(client9);
        clients.add(client10);
        clients.add(client11);
        clients.add(client12);
        clients.add(client13);
        clients.add(client14);
        clients.add(client15);
        clients.add(client16);

        ConcreteBank bank = new ConcreteBank(2, 1, 1);
        bank.receiveClients(clients);
    }

}
