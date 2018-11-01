package se.kth.id1212.sockets.server.net;

import se.kth.id1212.sockets.server.controller.ServerController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestHandler implements Runnable{
    private Socket socket;
    private ServerController serverController;

    public RequestHandler(Socket socket, ServerController serverController){
        this.serverController = serverController;
        this.socket = socket;
        System.out.printf("I got a task (%s)\n", socket.toString());
    }

    @Override
    public void run(){
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))){
            String inputString = null;
            while((inputString = reader.readLine()) != null){
                System.out.println("Input: " + inputString);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
