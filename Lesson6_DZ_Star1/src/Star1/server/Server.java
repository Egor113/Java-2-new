package Star1.server;

import Star1.client.Client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    private ServerSocket server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private final int ID = 0;
    private List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());

    public Server(){
        ServerSocket server = null;
        Socket socket = null;
        try {

            server = new ServerSocket(8090);
            System.out.println("lesson6.server start");

            int i = 0;
            while (true) {
                i++;
                socket = server.accept();
                System.out.println("client connected");
                clients.add(new ClientHandler(this, socket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void broadCast(String msg){
        for (ClientHandler handler: clients){
            handler.sendMsg(msg);
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
            System.out.println("Message from client: " + in.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
