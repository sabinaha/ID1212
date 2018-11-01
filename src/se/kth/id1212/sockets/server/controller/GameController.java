package se.kth.id1212.sockets.server.controller;

import se.kth.id1212.sockets.server.model.Game;
import se.kth.id1212.sockets.server.model.Player;
import se.kth.id1212.sockets.server.net.ClientHandler;
import se.kth.id1212.sockets.utils.GameCommand;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GameController {
    private Map<Long, Game> games;
    private ClientHandler clientHandler;

    public GameController(){
        this.games = new HashMap<>();
    }

    public boolean makeCharGuess(){
        return false;
    }

    public boolean makeWordGuess(String guess){
        Game game;
        synchronized(this){
            game = this.games.get(Thread.currentThread().getId());
        }
        return game.guessWord(guess);
    }

    public void startGame(){
        long threadID = Thread.currentThread().getId();
        Player player = new Player(threadID);
        synchronized (this){
            this.games.put(threadID, new Game(player));
        }
    }

    public String getGameInfo(){
        return null;
    }

    public void parseCommand(String command, ClientHandler clientHandler){
        String[] commandsArray = command.split(" ");
        GameCommand gameCommand = GameCommand.INVALID_COMMAND;
        try{
            gameCommand = GameCommand.valueOf(commandsArray[0]);
        } catch(IllegalArgumentException e){

        }

        switch(gameCommand){
            case START_GAME:
                startGame();
                try{
                    clientHandler.writeToClient("Game started\n");
                } catch(IOException e){
                    e.printStackTrace();
                }
                break;
            case MAKE_WORD_GUESS:
                try{
                    clientHandler.writeToClient(Boolean.toString(makeWordGuess(commandsArray[1])) + "\n");
                } catch (IOException e){
                    e.printStackTrace();
                }
                System.out.println("Make word guess");
                break;
            case MAKE_CHAR_GUESS:
                System.out.println("Make char guess");
                break;
            case EXIT:
                long threadID = Thread.currentThread().getId();
                System.out.println("Exiting... " + threadID);
                synchronized (this){
                    this.games.remove(threadID);
                }
                clientHandler.exit();
                break;
            default:
                System.out.println("Invalid command!");
                break;
        }
    }
}
