package server;

import client.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainServer {

    public static void main(String[] args) {
        Server server = new Server();
        new Thread(() -> {
            while (true){
                server.receiveMessage();
            }
        }).start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String messageOut = null;
            try {
                messageOut = reader.readLine();
                server.sendMessage(messageOut);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
