package lesson7.server;

import lesson7.server.impl.CsvAuthService;
import lesson7.server.model.Client;
import lesson7.server.task.AuthenticationTask;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {

    private List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());

    public Server() {
        ServerSocket server = null;
        Socket socket = null;
        try {

            server = new ServerSocket(8090);
            System.out.println("server start");

            new Thread(() -> {
                try{
                    while (true) {
                        for (int i = 0; i < clients.size(); i++) {
                            if (System.currentTimeMillis() - this.clients.get(i).getClient().getStartTime() > 10000) {
                                //unsubscribe(clients.get(i));
                                //System.out.println("client " + clients.get(i).getClient().getNick() + " closed");
                                clients.get(i).getOut().writeUTF("/close");
                            }
                        }
                    }
                }
                catch (Exception ex){
                    System.out.println("client " + " closed");
                }

            }).start();

            while (true) {
                socket = server.accept();
                System.out.println("client connected");
                new AuthenticationTask(socket, this, new CsvAuthService()).start();
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

    public void broadCast(ClientHandler from, String msg) {
        for (ClientHandler handler : clients) {
            if (!handler.getClient().getBlockList().contains(from.getClient().getNick())) {
                handler.sendMsg(msg);
            }
        }
    }

    public void userList() {
        StringBuilder sb = new StringBuilder();
        sb.append("/users ");
        for (ClientHandler clientHandler: clients){
            sb.append(clientHandler.getClient().getNick()).append(" ");
        }
        for (ClientHandler clientHandler: clients){
            clientHandler.sendMsg(sb.toString());
        }
    }

    public void subscribe(ClientHandler client) {
        clients.add(client);
        userList();
    }

    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
        userList();
    }

    public void sendTo(ClientHandler fromHandler, String toNick, String message) {
        clients.forEach(clientHandler -> {
            if (clientHandler.getClient().getNick().equals(toNick)) {
                fromHandler.sendMsg("to " + toNick + ": " + message);
                clientHandler.sendMsg("from " + fromHandler.getClient().getNick() + ": " + message);
            }
        });
    }
}
