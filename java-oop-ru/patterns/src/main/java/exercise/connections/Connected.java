package exercise.connections;

import exercise.TcpConnection;

public class Connected implements Connection {

    private TcpConnection connection;

    public Connected(TcpConnection connection) {
        this.connection = connection;
    }

    @Override
    public String getCurrentState() {
        return "connected";
    }

    @Override
    public void connect() {
        System.out.println("Error! Connection already connected");
    }

    @Override
    public void disconnect() {
        this.connection.setState(new Disconnected(this.connection));
    }

    public String write(String data) {
//        System.out.println("wc");
        return data;
    }
}