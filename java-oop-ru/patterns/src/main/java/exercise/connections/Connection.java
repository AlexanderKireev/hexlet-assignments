package exercise.connections;

public interface Connection { // State
    void connect();
    void disconnect();
    String getCurrentState();
    String write(String data);
}