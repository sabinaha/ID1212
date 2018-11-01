package se.kth.id1212.sockets.server.net;

import se.kth.id1212.sockets.server.controller.GameController;

import java.io.*;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * This is the main class for handling a single persistent connection
 */
public class ClientHandler implements Runnable{
    private static final String PROMPT = "† ";
    private Socket socket;
    private GameController gameController;
    private boolean connected = true;
    private BufferedReader reader;
    private BufferedWriter writer;

    public ClientHandler(Socket socket, GameController gameController) throws IOException {
        this.gameController = gameController;
        this.socket = socket;
        this.writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        System.out.printf("I got a task (%s)\n", socket.toString());
    }

    public void sendGameState(String gameState){
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()))){
            writer.write(gameState);
            writer.flush();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Fel i sendGameState");
        }
    }

    public String readFromClient() throws IOException {
        return this.reader.readLine();
    }

    public void writeToClient(String stringToWrite) throws IOException {
        writer.write(stringToWrite);
        writer.flush();
    }

    @Override
    public void run(){
        while(connected){
            String inputString;
            try{
                printPrompt();
                inputString = readFromClient();
                gameController.parseCommand(inputString, this);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void printPrompt() {
        try{
            writeToClient(PROMPT);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void exit(){
        this.connected = false;
        try{
            this.socket.close();
        } catch (IOException e){
            System.out.println("Exit i ClientHandler");
        }
    }
}
