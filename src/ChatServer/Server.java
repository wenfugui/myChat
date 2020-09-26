package ChatServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.startServer(8888);
    }
    public void startServer(int port)throws IOException{
        ServerSocket serverSocket = new ServerSocket(port);

        while (true){
            Socket socket = serverSocket.accept();
            ChatThread chatThread = new ChatThread(socket);
            chatThread.start();
        }
    }
}
