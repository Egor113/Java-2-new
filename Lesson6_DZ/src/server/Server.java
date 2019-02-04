package server;

import com.sun.security.ntlm.Client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Server(){
        try {
            server = new ServerSocket(8090);
            System.out.println("Server start");

            socket = server.accept();
            System.out.println("Client connected");

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

    public void putSendMessage(){
        System.out.println("Enter a message for a client");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String messageOut = null;
        try {
            messageOut = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sendMessage(messageOut);
    }

    public void receiveMessage() throws IOException {
        System.out.println("Message from client: " + in.readUTF());
    }
}
