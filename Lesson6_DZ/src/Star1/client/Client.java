package Star1.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
    public void sendMessage(String message){
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void receiveMessage(){
        try {
            System.out.println("Message from server: " + in.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
