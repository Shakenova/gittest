package BigProject;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args){
        DBManager db = new DBManager();
        db.connect();
        System.out.println("Waiting client");
        try{
            ServerSocket server = new ServerSocket(5555);
            int id = 0;
            while(true){
                Socket socket = server.accept();
                id++;
                System.out.println("Client #"+id+" connected");
                ClientHandler ch = new ClientHandler(socket,id, db);
                ch.start();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
