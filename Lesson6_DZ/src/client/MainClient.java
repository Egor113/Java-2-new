package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainClient {

    public static void main(String[] args) {
        Client client = new Client();
        new Thread(() ->{
            while (true){
                client.receiveMessage();
            }
        } ).start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String messageOut = null;
            try {
                messageOut = reader.readLine();
                client.sendMessage(messageOut);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
