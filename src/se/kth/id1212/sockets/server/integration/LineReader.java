package se.kth.id1212.sockets.server.integration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class LineReader {
    public static String getRandomLineFromFile(){
        int length = 0;
        List<String> lines = null;
        try{
            lines = Files.readAllLines(Paths.get("res/words.txt"));
            length = lines.size();
        }catch(IOException e){
            System.out.println("Ajaj nollan. Mycket dåligt (@LineReader)");
            e.printStackTrace();
        }
        Random r = new Random();
        return lines.get(r.nextInt(length));
    }
}
