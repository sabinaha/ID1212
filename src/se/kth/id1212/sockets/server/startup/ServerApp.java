package se.kth.id1212.sockets.server.startup;

import se.kth.id1212.sockets.server.controller.ServerController;

public class ServerApp {
    private static final int SERVER_PORT = 54321;

    public static void main(String[] args){
        new ServerController(SERVER_PORT);
    }
}
