package droid;

import java.util.Scanner;

public class Archer extends Droid{
    public Archer(){
        this.health = 100;
        this.damage = 20;
        this.stamina = 50;
        this.staminaDrop = 5;
        this.name = "Archer";

        drawDroid();
    }
    @Override
    public int attack(){ this.stamina -= this.staminaDrop; return this.damage;}
    @Override
    public int defence(){ stamina += 10; return health -= 10; }
    @Override
    void drawDroid(){
        Scanner[] droid_file = new Scanner[this.frames];
        droid_file[0] = openFile("E:\\Lab3_pp\\Archer\\ArcherStatic.txt");
        droid_file[1] = openFile("E:\\Lab3_pp\\Archer\\ArcherAttack.txt");
        droid_file[2] = openFile("E:\\Lab3_pp\\Archer\\ArcherDefence.txt");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; droid_file[i].hasNextLine(); j++){
                this.droid[i][j] = droid_file[i].nextLine();
            }
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
}
