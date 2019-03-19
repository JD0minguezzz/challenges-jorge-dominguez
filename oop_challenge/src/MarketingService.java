public class MarketingService implements ObserverModules {

    @Override
    public void receiveMessage(Message msg){

    }

    public void updateInformation(String customerID, Double transactionValue, String email){
        System.out.println("Check value :"+ transactionValue);
        this.sendAD(customerID, email);

    }

    public void sendAD(String customerID, String email){

    }

}
