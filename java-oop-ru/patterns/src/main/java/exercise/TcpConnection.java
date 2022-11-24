package exercise;

import exercise.connections.Connection;
import exercise.connections.Disconnected;
import java.util.ArrayList;
import java.util.List;

public class TcpConnection {
    // Должна быть связь с классом состояния
    private Connection connection;
    private final String ip;
    private final Integer port;
    private final List<String> buffer = new ArrayList<>();

    public TcpConnection(String ip, Integer port) {
        this.connection = new Disconnected(this);
        this.ip = ip;
        this.port = port;
    }

    public String getCurrentState() {
        return connection.getCurrentState();
    }

    public void connect() {
        connection.connect();
    }

    public void disconnect() {
        connection.disconnect();
    }

    public void write(String data) {
        String d = getState().write(data);
        if (d != null) {
            buffer.add(d);
        }

//        System.out.println(buffer);
    }

    public Connection getState() {
        return connection;
    }

    public void setState(Connection connection) {
        this.connection = connection;
    }



}