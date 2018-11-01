package se.kth.id1212.sockets.server.model;

import se.kth.id1212.sockets.server.integration.LineReader;

public class Game {

    private Player p1;
    private int score;
    private int remainingGuesses;
    private String soughtWord;

    public Game(Player player){
        this.p1 = player;
        this.score = 0;
        soughtWord = LineReader.getRandomLineFromFile();
        System.out.println("DEBUG | WORD: " + soughtWord);
        this.remainingGuesses = soughtWord.length();
    }

    public boolean guessWord(String word){
        boolean correct = this.soughtWord.toLowerCase().equalsIgnoreCase(word);
        if(correct){
            return true;
        }
        remainingGuesses--;
        return false;
    }

    public boolean guessChar(char guess){
        return false;
    }

    public String chooseWord(){
        return null;
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
