package se.kth.id1212.sockets.server.controller;

import se.kth.id1212.sockets.server.net.ConnectionHandler;


public class ServerController {
    public ServerController(int serverPort){
        new ConnectionHandler(serverPort, this);
    }
}
