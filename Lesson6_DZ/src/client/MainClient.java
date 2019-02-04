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
        String messageIn = null;
        while (true) {
            System.out.println("Enter '1' to sentMessage or other symbol to close program");
            try {
                messageIn = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (messageIn) {
                case "1":
                    client.sendMessage();
                default: break;
            }
        }
    }
}
