public class Lab2 {
    public static void main(String[] args){
        int sizeArr = 100;
        int timeLimit = 8000;
        int rangeMin = 56_000_000, rangeMax = 99_999_999;
        Phone phoneArr[] = new Phone[sizeArr];

        for (int i = 0; i < sizeArr; i++) {
            phoneArr[i] = new Phone();
            Phone.generateRandom(phoneArr[i]);
            System.out.println((i+1)+". "+phoneArr[i].toString());
        }

        System.out.println("\nList of users that exceed time limit of "+timeLimit);
        int exceedC = 0;
        for (int i = 0; i < sizeArr; i++)
            if(Phone.townCall(timeLimit, phoneArr[i])){
                System.out.println((i + 1)+". "+phoneArr[i].getName() +" "+
                        phoneArr[i].getSurname()+" id:"+ phoneArr[i].getId()+
                    " - exceeds time "+phoneArr[i].getTownCall()+"/"+timeLimit);
                exceedC++;
            }
        System.out.println("| Total users: "+exceedC+"/"+sizeArr);

        System.out.println("\nList of users that are using long distance calls");
        int dcallsC = 0;
        for (int i = 0; i < sizeArr; i++)
            if(Phone.longDistanceCall(phoneArr[i])){
                System.out.println((i + 1)+". "+phoneArr[i].getName() +" "+
                        phoneArr[i].getSurname()+" id:"+ phoneArr[i].getId()+
                        " - has been using long distance calls for "+phoneArr[i].getLongDistanceCall()
                        +"m.");
                dcallsC++;
            }
        System.out.println("| Total users: "+dcallsC+"/"+sizeArr);

        System.out.println("\nList of users that are out of range "+rangeMin+"/"+rangeMax);
        int rangeC = 0;
        for (int i = 0; i < sizeArr; i++)
            if(Phone.rangePhoneNumber(rangeMin,rangeMax, phoneArr[i])){
                System.out.println((i + 1)+". "+phoneArr[i].getName() +" "+
                        phoneArr[i].getSurname()+" id:"+ phoneArr[i].getId()+
                        " - is in range | number: "+phoneArr[i].getPhoneNumber());
                rangeC++;
            }
        System.out.println("| Total users: "+rangeC+"/"+sizeArr);
    }
}
