package Lab1;

import java.util.Scanner;

/**
 * Лабораторна робота 1 | варіант 6
 * @author Іваночко Іван КН-204
 */
public class Lab1 {
    /**
     * Main function
     */
    public static void main(String[] args){
        System.out.println("How many objects would you like?");
        Scanner input1 = new Scanner(System.in);
        int lucaSize = input1.nextInt();


        LucasNumbers lucaArr[] = new LucasNumbers[lucaSize];
        long arrLong[][] = new long[lucaSize][];

        for(int i = 0; i < lucaSize; i++){
            System.out.println("Object #" + (i + 1) + " How many numbers would you like? 91 is max"); // max value for long is 9,223,372,036,854,775,807
            Scanner input2 = new Scanner(System.in);
            int numbers = input2.nextInt();
            lucaArr[i] = new LucasNumbers(numbers);
            arrLong[i] = lucaArr[i].createNumbers();
        }

        for (int i = 0; i < lucaSize; i++){
            System.out.println("- object #" + (i + 1) + " out of " + lucaSize + " -");
            for (long a: arrLong[i])
            {
            System.out.print(a);
            System.out.println(LucasNumbers.isSimpleNum(a) ? " - prime" : "");
            }
            System.out.println();
        }
    }
}
