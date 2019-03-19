public class Audit implements ObserverModules {

    Invoker invoker;

    public Audit() {
        invoker = new Invoker();
        Command writeFile = new WriteFile();
        invoker.takeCommand(writeFile);
    }

    @Override
    public void receiveMessage(Message msg){
        invoker.placeCommand(msg);
    }

}
