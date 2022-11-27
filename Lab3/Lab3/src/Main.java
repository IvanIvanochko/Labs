import droid.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Arena create
        int height_a = 40, length_a = 225;
        char[][] arena = new char[height_a][length_a];
        char[][] arena_Clr = new char[height_a][length_a];
        char[][] arena_Empty = new char[height_a][length_a];

        arenaCreate(height_a, length_a, arena);
        arenaCreate(height_a, length_a, arena_Clr);
        arenaCreate(height_a, length_a, arena_Empty);

        int youChoose = -1, enemyChoose = -1;
        //Choosing droids
        drawMenu(height_a, length_a, arena);
        /*Arena draws*/
        for (int i = 0; i < height_a; i++) {
            System.out.println(arena[i]);
        }

        Scanner myObj = new Scanner(System.in);
        youChoose = Integer.parseInt(myObj.nextLine()) - 1;
        enemyChoose = Integer.parseInt(myObj.nextLine()) - 1;

        /*Arena clears*/
        initArr(arena, arena_Clr, height_a, length_a);

        // Creating droids
        Droid you;
        switch (youChoose) {
            case 0: {
                you = new Warrior();
                break;
            }
            case 1: {
                you = new Shield_Bearer();
                break;
            }
            case 2: {
                you = new Archer();
                break;
            }
            default: {
                you = new Droid();
                break;
            }
        }

        Droid enemy;
        switch (enemyChoose) {
            case 0: {
                enemy = new Warrior();
                break;
            }
            case 1: {
                enemy = new Shield_Bearer();
                break;
            }
            case 2: {
                enemy = new Archer();
                break;
            }
            default: {
                enemy = new Droid();
                break;
            }
        }

        //Game start
        // Creating first frame and etc.
        statsShow(height_a, length_a, arena);
        statsShow(height_a, length_a, arena_Clr);
        drawOnArena1stHalf(height_a, length_a, you.height[0], you.rowsLength[0], you.droid[0], arena);
        drawOnArena2ndHalf(height_a, length_a, enemy.height[0], enemy.rowsLength[0], enemy.droid[0], arena);
        statsUpdate(you.health, you.stamina, you.damage,
                enemy.health, enemy.stamina, enemy.damage,
                height_a, length_a, arena);
        //Arena draws
        for (int i = 0; i < height_a; i++) {
            System.out.println(arena[i]);
        }
        initArr(arena, arena_Clr, height_a, length_a);
        //----------------------
        int moves = 1; // for replaying from file
        int[] prevMove = {0, 0};
        try {
            FileWriter replay = new FileWriter("GameReplay.txt");
            while (you.health > 0 && enemy.health > 0) {
                replay.write("Move #" + moves + "\n");

                boolean continueGame_you = false, continueGame_enemy = false;

                //Fight | 0 - static | 1 - attack | 2 - defence |
                /*You choose action*/
                while (continueGame_you == false) {
                    String youF = myObj.nextLine(); // !!!! Problem third input at the start | fixed
                    switch (youF) {
                        case "a": {
                            if (you.stamina >= you.staminaDrop) {
                                drawOnArena1stHalf(height_a, length_a, you.height[1], you.rowsLength[1], you.droid[1], arena);
                                enemy.health -= you.attack();
                                prevMove[0] = 1;
                                replay.write("a " + you.health + " " + you.stamina + "\n");
                                continueGame_you = true;
                            } else {
                                /* Error */
                                drawOnArena1stHalf(height_a, length_a, you.height[prevMove[0]], you.rowsLength[prevMove[0]], you.droid[prevMove[0]], arena);
                                drawOnArena1stHalf(height_a, length_a, you.Error_height[0], you.Error_rowsLength[0], you.Error_popUps[0], arena);
                                drawOnArena2ndHalf(height_a, length_a, enemy.height[prevMove[1]], enemy.rowsLength[prevMove[1]], enemy.droid[prevMove[1]], arena);
                                //Arena draws
                                for (int i = 0; i < height_a; i++) {
                                    System.out.println(arena[i]);
                                }
                            }
                        }
                        break;
                        case "d": {
                            drawOnArena1stHalf(height_a, length_a, you.height[2], you.rowsLength[2], you.droid[2], arena);
                            you.defence();
                            prevMove[0] = 2;
                            replay.write("d " + you.health + " " + you.stamina + "\n");
                            continueGame_you = true;
                        }
                        break;
                        default: {
                            drawOnArena1stHalf(height_a, length_a, you.height[0], you.rowsLength[0], you.droid[0], arena);
                        }
                        break;
                    }
                }

                /*Enemy random choose action*/
                while (continueGame_enemy == false) {
                    int enemyF = 1 + (int) (Math.random() * 2);
                    switch (enemyF) {
                        case 1: {
                            if (enemy.stamina >= enemy.staminaDrop) {
                                drawOnArena2ndHalf(height_a, length_a, enemy.height[1], enemy.rowsLength[1], enemy.droid[1], arena);
                                you.health -= enemy.attack();
                                replay.write("a " + enemy.health + " " + enemy.stamina + "\n");
                                prevMove[1] = 1;
                                continueGame_enemy = true;
                            }
                        }
                        break;
                        case 2: {
                            drawOnArena2ndHalf(height_a, length_a, enemy.height[2], enemy.rowsLength[2], enemy.droid[2], arena);
                            enemy.defence();
                            replay.write("a " + enemy.health + " " + enemy.stamina + "\n");
                            prevMove[1] = 2;
                            continueGame_enemy = true;
                        }
                        break;
                        default: {
                            drawOnArena2ndHalf(height_a, length_a, enemy.height[0], enemy.rowsLength[0], enemy.droid[0], arena);
                        }
                        break;
                    }
                }

                statsUpdate(you.health, you.stamina, you.damage,
                        enemy.health, enemy.stamina, enemy.damage,
                        height_a, length_a, arena);

                //Arena draws
                for (int i = 0; i < height_a; i++) {
                    System.out.println(arena[i]);
                }

//                // Delay
//                try {
//                    Thread.sleep(1 * 500);
//                } catch (InterruptedException ie) {
//                    Thread.currentThread().interrupt();
//                }

                // Clear Screen
                /*Arena clears*/
                initArr(arena, arena_Clr, height_a, length_a);
                try {
                    cls();
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                moves++;
            }
            replay.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        // End game
        drawVictory( (you.health > 0) ? true : false,height_a, length_a, arena);

        //Arena draws
        for (int i = 0; i < height_a; i++) {
            System.out.println(arena[i]);
        }
        myObj.nextLine(); // press anything to continue
        // Replay
        initArr(arena, arena_Clr, height_a, length_a);
        replayGame(moves,height_a, length_a, arena, arena_Clr, you, enemy);
    }


    // Height of arena, Length of arena, columns of an art, rows length of an art, droid itself, arena
    static void drawOnArena1stHalf(int height_a, int length_a, int columns,
                     int[] rows, String[] droid, char[][] arena){

        for (int i = 0; i < height_a; i++){

            if(i >= 2 && i <= (height_a - 2))
                for (int j = 0; j < length_a; j++) {
                    if((i - 2) < columns){
                        if(j >= 2 && j < rows[i - 2]){
                            if((j - 2) < (droid[i - 2].length()))
                                arena[i][j] = droid[i - 2].charAt(j - 2);
                        }
                    }

                }
        }
    }

    static void drawOnArena2ndHalf(int height_a, int length_a, int columns,
                                   int[] rows, String[] droid, char[][] arena){
        int start = 95;
        for (int i = 0; i < height_a; i++){
            if(i >= 2 && i <= (height_a - 2))
                for (int j = start; j < length_a; j++) {
                    if((i - 2) < columns){
                        if(j >= (start + 2) && j < ((start + 2) + rows[i - 2])){
                            if((j - (start + 2)) < (droid[i - 2].length() + (start + 2)))
                                arena[i][j] = droid[i - 2].charAt(j - (start + 2));
                        }
                    }
                }
        }
    }

    static void arenaCreate(int height_a, int length_a, char[][] arena){
        int minusL = 25;
        for(int i =0;i<height_a; i++){
            if(i==0 || i==height_a - 1){
                for (int j = 0; j < length_a; j++) {
                    arena[i][j] = '-';
                }
            }
            else {
                for (int j = 0; j < length_a; j++) {
                    if(j == 0 || j == (length_a - minusL) - 1 ||
                            j == length_a - 1)
                        arena[i][j] = '|';
                    else
                        arena[i][j] = ' ';
                    if(j == 95 && i % 2 == 0)
                        arena[i][j] = '#';
                }
            }

        }
    }

    static void statsShow(int height_a, int length_a, char[][] arena){
        int minusL = 24;
        String[] you = {"You(left)", "Health:", "Stamina:", "Damage:"};
        String[] enemy = {"Enemy(right)", "Health:", "Stamina:", "Damage:"};

        for(int i =0;i<height_a; i++){
            if(i >= 4 && i < 8){
                int length = you[i - 4].length();
                for (int j = 0; j < length; j++) {
                    arena[i][(length_a - minusL + j)] = you[i - 4].charAt(j);
                }
            }

            if(i >= 12 && i < 16){
                int length = enemy[i - 12].length();
                for (int j = 0; j < length; j++) {
                    arena[i][(length_a - minusL + j)] = enemy[i - 12].charAt(j);
                }
            }
        }
    }
    static void statsUpdate(int hp_you, int st_you, int dg_you,
                            int hp_enm, int st_enm, int dg_enm,
                            int height_a, int length_a, char[][] arena){
        int minusL = 24;
        String[] you = {"You(left)", "Health:", "Stamina:", "Damage:"};
        String[] enemy = {"Enemy(right)", "Health:", "Stamina:", "Damage:"};

        String[] youStat = {String.valueOf(hp_you), String.valueOf(st_you), String.valueOf(dg_you)};
        String[] enemyStat = {String.valueOf(hp_enm), String.valueOf(st_enm), String.valueOf(dg_enm)};

        for(int i =0;i<height_a; i++){
            if(i >= 5 && i < 8){
                int length = you[(i + 1) - 5].length();

                int spaceNeeded = length_a - minusL + length + 1;
                for (int j = 0; j < youStat[i - 5].length(); j++) {
                    arena[i][(spaceNeeded + j)] = youStat[i - 5].charAt(j);
                }
            }

            if(i >= 13 && i < 16){
                int length = enemy[(i + 1) - 13].length();

                int spaceNeeded = length_a - minusL + length + 1;
                for (int j = 0; j < enemyStat[i - 13].length(); j++) {
                    arena[i][(spaceNeeded + j)] = enemyStat[i - 13].charAt(j);
                }
            }
        }
    }

    public static void drawMenu(int heightMax, int lengthMax, char[][] arena) {
        // 1st half
        String[] droid_d = new String[heightMax];

        Scanner droid = null;
        try {
            File file = new File("E:\\Lab3_pp\\Menu\\Game_start.txt");
            droid = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        int height = 0;
        for (; droid.hasNextLine(); height++){
            droid_d[height] = droid.nextLine();
        }
        int[] width = new int[height];
        for (int i = 0; i < height; i++) {
            width[i] = droid_d[i].length();
        }

        drawOnArena1stHalf(heightMax, lengthMax, height, width, droid_d, arena);

        //2nd half
        String[] droid_d2 = new String[heightMax];

        Scanner droid2 = null;
        try {
            File file2 = new File("E:\\Lab3_pp\\Menu\\Droids_menu.txt");
            droid2 = new Scanner(file2);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        int height2 = 0;
        for (; droid2.hasNextLine(); height2++){
            droid_d2[height2] = droid2.nextLine();
        }
        int[] width2 = new int[height2];
        for (int i = 0; i < height2; i++) {
            width2[i] = droid_d2[i].length();
        }

        drawOnArena2ndHalf(heightMax, lengthMax, height2, width2, droid_d2, arena);
    }

    public static void drawVictory(boolean you, int heightMax, int lengthMax, char[][] arena){
        String[][] droid_d = new String[2][heightMax];

        Scanner[] droid = new Scanner[2];
        droid[0] = null;
        droid[1] = null;
        try {
            File[] file = new File[2];
            file[0] = new File("E:\\Lab3_pp\\Menu\\Victory.txt");
            droid[0] = new Scanner(file[0]);
            file[1] = new File("E:\\Lab3_pp\\Menu\\Replay.txt");
            droid[1] = new Scanner(file[1]);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        int[] height = {0,0};
        for (int i = 0; i < 2; i++) {
            for (; droid[i].hasNextLine(); height[i] += 1){
                droid_d[i][height[i]] = droid[i].nextLine();
            }
        }

        int[][] width = new int[2][];
        width[0] = new int[height[0]];
        width[1] = new int[height[1]];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < height[i]; j++) {
                width[i][j] = droid_d[i][j].length();
            }
        }


        if(you == true) {
            drawOnArena1stHalf(heightMax, lengthMax, height[0], width[0], droid_d[0], arena);
            drawOnArena2ndHalf(heightMax, lengthMax, height[1], width[1], droid_d[1], arena);
        }
        else {
            drawOnArena2ndHalf(heightMax, lengthMax, height[0], width[0], droid_d[0], arena);
            drawOnArena1stHalf(heightMax, lengthMax, height[1], width[1], droid_d[1], arena);
        }
    }

    static public void replayGame(int moves,int height, int length, char[][] arena, char[][] arena_Clr,Droid you, Droid enemy){
        //String[] replayString = new String[moves * 3];
        Scanner hitToReplay = new Scanner(System.in);
        Scanner replayFile = null;
        try {
            File file = new File("GameReplay.txt");
            replayFile = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        while (replayFile.hasNextLine()){
            String[] replayString = new String[2];
            String[][] characteristics = new String[2][3];
            replayFile.nextLine(); // Skips 'Move #(...)'
            for (int i = 0; i < 2; i++) {
                replayString[i] = replayFile.nextLine();
            }
            for (int i = 0; i < 2; i++) {
                characteristics[i] = replayString[i].split(" ", 3);
            }

            statsUpdate(Integer.parseInt(characteristics[0][1]), Integer.parseInt(characteristics[0][2]), you.damage,
                    Integer.parseInt(characteristics[1][1]), Integer.parseInt(characteristics[1][2]), enemy.damage,
                    height, length, arena);

            for (int i = 0; i < 2; i++) {
                if(i == 0) // you
                {
                    if(replayString[i].charAt(0) == 'a'){
                        drawOnArena1stHalf(height, length, you.height[1], you.rowsLength[1], you.droid[1], arena);
                    }
                    if(replayString[i].charAt(0) == 'd'){
                        drawOnArena1stHalf(height, length, you.height[2], you.rowsLength[2], you.droid[2], arena);
                    }
                }
                if(i == 1) // enemy
                {
                    if(replayString[i].charAt(0) == 'a'){
                        drawOnArena2ndHalf(height, length, enemy.height[1], enemy.rowsLength[1], enemy.droid[1], arena);
                    }
                    if(replayString[i].charAt(0) == 'd'){
                        drawOnArena2ndHalf(height, length, enemy.height[2], enemy.rowsLength[2], enemy.droid[2], arena);
                    }
                }
            }
            //Arena draws
            for (int i = 0; i < height; i++) {
                System.out.println(arena[i]);
            }
            initArr(arena, arena_Clr, height, length);
            hitToReplay.nextLine();
        }
    }
    public static void initArr(char[][] toInit, char[][] init, int height, int length){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                toInit[i][j] = init[i][j];
            }
        }
    }

    public static void cls() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}
