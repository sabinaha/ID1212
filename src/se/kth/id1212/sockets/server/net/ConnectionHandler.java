package se.kth.id1212.sockets.server.net;

import se.kth.id1212.sockets.server.controller.GameController;

import java.net.ServerSocket;
import java.io.IOException;
import java.net.Socket;

public class ConnectionHandler {
    private static final int LINGER_TIME = 5000;

    private GameController gameController;

    public ConnectionHandler(int port, GameController gameController){
        this.gameController = gameController;
        try(ServerSocket serverSocket = new ServerSocket(port)){

            while(true){
                Socket socket = serverSocket.accept();
                socket.setSoLinger(true, LINGER_TIME);
                Thread handlerThread = new Thread(new ClientHandler(socket, this.gameController));
                handlerThread.setPriority(Thread.MAX_PRIORITY);
                handlerThread.start();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
