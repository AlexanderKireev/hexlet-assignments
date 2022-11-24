package exercise.connections;

import exercise.TcpConnection;

public class Disconnected implements Connection {

    private TcpConnection connection;

    public Disconnected(TcpConnection connection) {
        this.connection = connection;
    }

    @Override
    public String getCurrentState() {
        return "disconnected";
    }

    @Override
    public void connect() {
//        System.out.println("ccc");
        this.connection.setState(new Connected(this.connection));
    }

    @Override
    public void disconnect() {
        System.out.println("Error! Connection already disconnected");
//        this.connection.disconnect();


    }

    public String write(String data) {
        System.out.println("Error");
        return null;
    }
}