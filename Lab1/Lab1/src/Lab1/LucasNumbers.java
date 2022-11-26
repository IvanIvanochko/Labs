package Lab1;

/**
 * Class for Luca's numbers
 */
public class LucasNumbers{
    int numbers;

    /**
     * Default constructor
     * @param numbers you pass numbers that dictates the size of Luca's numbers
     */
    public LucasNumbers(int numbers) {
        this.numbers = numbers;
    }

    /**
     * Creates Luca's numbers
     * @return Returns array of Luca's numbers
     */
    long[] createNumbers(){
        long arr[] = new long[this.numbers];
        long L1, L0, save;
        L1 = 2 + 1;
        L0 = 1;
        for(int i = 0; i < this.numbers; i++){
            if(i == 0) arr[0] = 2;
            if(i == 1) arr[1] = 1;
            if(i == 2) { arr[2] = L1; break; }
        }
        for(int i = 3; i < this.numbers; i++){
            save = L1;
            L1 = L1 + L0;
            L0 = save;

            arr[i] = L1;
        }
        return arr;
    }
    /**
     * Checks if the number is primal
     * @param num the number to check if it's primal or not
     * @return Returns if it's primal number or not
     */
    static boolean isSimpleNum(long num){
        double test;
        int[] checkNums = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53,
                59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131,
                137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211,
                223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293,
                307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389,
                397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479,
                487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587,
                593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673,
                677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773,
                787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881,
                883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997};

        for ( int arrNum : checkNums) {
            if(arrNum >= num) return true;
            long check = num % arrNum;
            if(check == 0) return false;
        }
        return true;
    }
}

