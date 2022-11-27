package droid;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Droid {
    String name = "Droid";
    public int health = 100;
    public int damage = 10;
    public int stamina = 100, staminaDrop = 10;

    public Droid(){
        drawDroid();
        drawErrors();
    }
    public int attack(){ stamina -= staminaDrop; return damage; }
    public int defence(){ stamina += 5; return 0; }

    int frames = 3;
    public String[][] droid = new String[frames][40];
    public int[] height = { 0, 0, 0 };
    public int[][] rowsLength = new int[frames][];
    void drawDroid(){
        /*
        * 0 - Static
        * 1 - Attack
        * 2 - Defence
        */
        Scanner droid_file = openFile("E:\\Lab3_pp\\Errors\\Error_droid.txt");

        for (int i = 0; droid_file.hasNextLine(); i++){
            this.droid[0][i] = droid_file.nextLine();
        }

        for (int i = 0; i < this.frames; i++) {
            for (String abc: this.droid[i])
            { this.height[i]++; }
            this.rowsLength[i] = new int[this.height[i]];
        }

        for (int i = 0; i < this.frames; i++){
            int count = 0;
            for (String abc: this.droid[i]) {
                if(abc == null) break;
                this.rowsLength[i][count] = abc.length();
                count++;
            }
        }
    }

    int Error_frames = 1;
    public String[][] Error_popUps = new String[Error_frames][40];
    public int[] Error_height = { 0, 0, 0 };
    public int[][] Error_rowsLength = new int[Error_frames][];
    void drawErrors(){
        /*
         * 0 - Static
         * 1 - Attack
         * 2 - Defence
         */
        Scanner[] Error_file = new Scanner[Error_frames];
        Error_file[0] = openFile("E:\\Lab3_pp\\Errors\\Error_stamina.txt");

        for (int j = 0; j < Error_frames; j++) {
            for (int i = 0; Error_file[j].hasNextLine(); i++) {
                this.Error_popUps[0][i] = Error_file[j].nextLine();
            }
        }

        for (int i = 0; i < this.Error_frames; i++) {
            for (String abc: this.Error_popUps[i])
            { this.Error_height[i]++; }
            this.Error_rowsLength[i] = new int[this.Error_height[i]];
        }

        for (int i = 0; i < this.Error_frames; i++){
            int count = 0;
            for (String abc: this.Error_popUps[i]) {
                if(abc == null) break;
                this.Error_rowsLength[i][count] = abc.length();
                count++;
            }
        }
    }

    protected static Scanner openFile(String fileName) {
        Scanner read = null;
        try {
            File file = new File(fileName);
            read = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return read;
    }
}
