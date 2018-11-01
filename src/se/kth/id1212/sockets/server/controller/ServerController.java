package se.kth.id1212.sockets.server.controller;

import se.kth.id1212.sockets.server.net.ConnectionHandler;

public class ServerController {

    private GameController gameController;

    public ServerController(int serverPort) {
        // Create the game controller for the application
        this.gameController = new GameController();
        // Start the main connection listener/accepter
        new ConnectionHandler(serverPort, this.gameController);
    }

}
