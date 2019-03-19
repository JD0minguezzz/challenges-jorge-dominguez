public interface Topic {
    void sendMessage();
    void registerObserver(ObserverModules observer);
    void removeObserver(ObserverModules observer);
}
