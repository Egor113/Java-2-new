package Star1.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
    private List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());

    public Server(){
        try {
            server = new ServerSocket(8090);
            System.out.println("Server start");

//            socket = server.accept();
//            System.out.println("Client connected");

            while (true){
                socket = server.accept();
                System.out.println("Client connected");
                clients.add(new ClientHandler(this,socket));
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
            }

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
            System.out.println("Message from client: " + in.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadCast(String msg) {
        for (ClientHandler handler: clients
             ) {
            handler.sendMsg(msg);
        }
    }
}
