package server;

import com.sun.security.ntlm.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public Server(){
        ServerSocket server = null;
        Socket socket = null;
        try {
            server = new ServerSocket(8090);
            System.out.println("Server start");

            socket = server.accept();
            System.out.println("Client connected");

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String line = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
