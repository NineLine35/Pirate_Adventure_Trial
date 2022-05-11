package Player;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Class to read in high score text file and add player results to the file at the end of the game
 */

// 8.1,2 & 9.1 - File manipulation
public class HighScoreTracker {

    public static void readHighScores(){

        Path path = Paths.get("src/Player/hs.txt");

       try{

           final List<String> lines = Files.readAllLines(path);
           for(String line : lines){
               System.out.println(line);
           }


       }catch (IOException ex){
           System.out.println("Missing file");
       }


    }

    public static void writeHighScores() throws IOException {
        String highScore = Player.getInstance().getName() + " - " + Player.getInstance().getChest() +" coin\n";

        File file = new File("src/Player/hs.txt");

        FileWriter fw = new FileWriter(file,true);

        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(highScore);
        bw.close();

    }


}
