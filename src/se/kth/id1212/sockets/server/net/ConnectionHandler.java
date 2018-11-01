package se.kth.id1212.sockets.server.net;

import se.kth.id1212.sockets.server.controller.ServerController;

import java.net.ServerSocket;
import java.io.IOException;
import java.net.Socket;

public class ConnectionHandler {
    private static final int LINGER_TIME = 5000;

    private ServerController serverController;

    public ConnectionHandler(int port, ServerController serverController){
        this.serverController = serverController;
        try(ServerSocket serverSocket = new ServerSocket(port)){

            while(true){
                Socket socket = serverSocket.accept();
                socket.setSoLinger(true, LINGER_TIME);
                Thread handlerThread = new Thread(new RequestHandler(socket, serverController));
                handlerThread.setPriority(Thread.MAX_PRIORITY);
                handlerThread.start();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
