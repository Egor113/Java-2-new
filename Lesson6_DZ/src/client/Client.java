package client;

import java.io.*;
import java.net.Socket;

public class Client {
    private final String HOST = "localhost";
    private final int PORT = 8090;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Client(){
        try {
            socket = new Socket(HOST, PORT);
            System.out.println("Client start");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendMessage(){
        System.out.println("Enter a message for a client");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String messageOut = null;
        try {
            messageOut = reader.readLine();
            out.writeUTF(messageOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void receiveMessage(){
        try {
            System.out.println("from server: " + in.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
