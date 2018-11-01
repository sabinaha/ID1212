package se.kth.id1212.sockets.server.model;

public class Player {

    private static int playerCount = 0;
    private String ipAddress;
    private int playerId;
    private long threadID;

    public Player(long threadID){
        this.ipAddress = ipAddress;
        this.playerId = playerCount++;
        this.threadID = threadID;
    }

    public String getIpAddress(){
        return ipAddress;
    }

    public int getPlayerId(){
        return playerId;
    }

    public long getThreadID(){
        return threadID;
    }
}
