package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainServer {
    public static void main(String[] args) {
        Server server = new Server();
        server.putSendMessage();
    }
}
