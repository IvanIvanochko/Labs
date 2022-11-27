package droid;

import java.util.Scanner;

public class Warrior extends Droid{
    public Warrior(){
        this.health = 100;
        this.damage = 20;
        this.stamina = 50;
        this.staminaDrop = 10;
        this.name = "Warrior";

        drawDroid();
    }
    @Override
    public int attack(){ this.stamina -= this.staminaDrop; return this.damage;}
    @Override
    public int defence(){ this.stamina += 15; return  this.health -= 5; }
    @Override
    void drawDroid(){
        Scanner[] droid_file = new Scanner[this.frames];
        droid_file[0] = openFile("E:\\Lab3_pp\\Warrior\\WarriorStatic.txt");
        droid_file[1] = openFile("E:\\Lab3_pp\\Warrior\\WarriorAttack.txt");
        droid_file[2] = openFile("E:\\Lab3_pp\\Warrior\\WarriorDefence.txt");

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
