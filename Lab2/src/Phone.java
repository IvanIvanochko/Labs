import java.util.concurrent.ThreadLocalRandom;

public class Phone {
    public Phone(){}
    private String id, surname, name;
    private long phoneNumber;
    private int townCall, longDistanceCall;

    // Setters
    public void setId(String id) { this.id = id; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setName(String name) { this.name = name; }
    public void setPhoneNumber(long phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setTownCall(int townCall) { this.townCall = townCall; }
    public void setLongDistanceCall(int longDistanceCall) { this.longDistanceCall = longDistanceCall; }

    // Getters
    public String getId() { return id; }
    public String getSurname() { return surname; }
    public String getName() { return name; }
    public long getPhoneNumber() { return phoneNumber; }
    public int getTownCall() { return townCall; }
    public int getLongDistanceCall() { return longDistanceCall; }

    @Override
    public String toString(){
        return id+" | "+ surname +" "+name+" phone number:"+phoneNumber+
            " | "+townCall+"m "+longDistanceCall+"m";
    }

    static public boolean townCall(int setTime, Phone user){
        if(setTime < user.getTownCall())
            return true;
        return false;
    }

    static public boolean longDistanceCall(Phone user){
        if(user.getLongDistanceCall() > 0)
            return true;
        return false;
    }

    static public boolean rangePhoneNumber(long minN, long maxN, Phone user){
        boolean minB = false, maxB = false;
        long userL = user.getPhoneNumber();

        if(minN <= userL) minB = true;
        if(maxN >= userL) maxB = true;
        if(minB == true && maxB == true) return true;
        return false;
    }

    static public Phone generateRandom(Phone user){
        String nameArr[] = { "Aaran", "Aaren", "Aarez", "Aarman", "Aaron",
                  "Annan", "Anndra", "Anselm", "Anthony", "Anthony-John", "Antoine", "Anton", "Antoni", "Antonio", "Antony", "Antonyo", "Anubhav", "Aodhan",
                "Aon", "Aonghus", "Apisai", "Arafat", "Aran", "Arandeep", "Arann", "Aray", "Arayan", "Archibald", "Archie", "Arda", "Ardal", "Ardeshir", "Areeb",
                "Areez", "Aref", "Arfin", "Argyle", "Argyll", "Ari", "Aria", "Arian", "Arihant", "Aristomenis", "Aristotelis", "Arjuna", "Arlo", "Armaan",
                "Arman", "Armen", "Arnab", "Arnav", "Arnold", "Aron", "Aronas", "Arran", "Arrham", "Arron", "Arryn", "Arsalan", "Artem", "Arthur", "Artur",
                "Cillian", "Cillin", "Cinar", "CJ", "C-Jay", "Clark", "Clarke", "Clayton", "Clement", "Clifford", "Clyde", "Cobain", "Coban", "Coben",
                "Cobi", "Cobie", "Coby", "Codey", "Codi", "Codie", "Cody", "Cody-Lee", "Coel", "Cohan", "Cohen", "Colby", "Cole", "Colin", "Coll", "Colm",
                "Edward", "Edwin", "Edwyn", "Eesa", "Efan", "Efe", "Ege", "Ehsan", "Ehsen", "Eiddon", "Eidhan", "Eihli", "Eimantas", "Eisa", "Eli", "Elias",
                "Elijah", "Eliot", "Elisau", "Eljay", "Eljon", "Elliot", "Elliott", "Ellis", "Ellisandro", "Elshan", "Elvin", "Elyan", "Emanuel", "Emerson",
                "Emil", "Emile", "Emir", "Emlyn", "Emmanuel", "Emmet", "Eng", "Eniola", "Enis", "Ennis", "Enrico", "Enrique", "Enzo", "Eoghain", "Eoghan",
                "Eoin", "Eonan", "Erdehan", "Eren", "Erencem", "Eric", "Ericlee", "Erik", "Eriz", "Ernie-Jacks", "Eroni", "Eryk", "Eshan", "Essa", "Esteban",
                "Ethan", "Etienne", "Etinosa", "Euan", "Eugene", "Evan", "Evann", "Ewan", "Ewen", "Ewing", "Exodi", "Ezekiel", "Ezra", "Fabian", "Fahad",
                "Faheem", "Faisal", "Faizaan", "Famara", "Fares", "Farhaan", "Farhan", "Farren", "Farzad", "Fauzaan", "Favour", "Fawaz", "Fawkes", "Faysal",
                "Fearghus", "Feden", "Felix", "Fergal", "Fergie", "Fergus", "Ferre", "Fezaan", "Fiachra", "Fikret", "Filip", "Filippo", "Finan", "Findlay",
                "Findlay-James", "Findlie", "Finlay", "Finley", "Finn", "Finnan", "Finnean", "Finnen", "Finnlay", "Finnley", "Fintan", "Fionn", "Firaaz",
                "Fletcher", "Flint", "Florin", "Flyn", "Flynn", "Fodeba", "Folarinwa", "Forbes", "Forgan", "Forrest", "Fox", "Francesco", "Francis", "Francisco",
                "Franciszek", "Franco", "Frank", "Frankie", "Franklin", "Franko", "Fraser", "Frazer", "Fred", "Freddie", "Frederick", "Fruin", "Fyfe", "Fyn",
                "Fynlay", "Fynn", "Gabriel", "Gallagher", "Gareth", "Garren", "Garrett", "Garry", "Gary", "Gavin", "Gavin-Lee", "Gene", "Geoff", "Geoffrey",
                "Geomer", "Geordan", "Geordie", "George", "Georgia", "Georgy", "Gerard", "Ghyll", "Giacomo", "Gian", "Giancarlo", "Gianluca", "Gianmarco",
                "Gideon", "Gil", "Gio", "Girijan", "Girius", "Gjan", "Glascott", "Glen", "Glenn", "Gordon", "Grady", "Graeme", "Graham", "Grahame", "Grant",
                "Grayson", "Greg", "Gregor", "Gregory", "Greig", "Griffin", "Griffyn", "Grzegorz", "Guang", "Guerin", "Guillaume", "Gurardass", "Gurdeep",
                "Gursees", "Gurthar", "Gurveer", "Gurwinder", "Gus", "Gustav", "Guthrie", "Guy", "Gytis", "Habeeb", "Hadji", "Hadyn", "Hagun", "Haiden",
                "Haider", "Hamad", "Hamid", "Hamish", "Hamza", "Hamzah", "Han", "Hansen", "Hao", "Hareem", "Hari", "Harikrishna", "Haris", "Harish",
                "Harjeevan", "Harjyot", "Harlee", "Harleigh", "Harley", "Harman", "Harnek", "Harold", "Haroon", "Harper", "Harri", "Harrington", "Harris",
                "Harrison", "Harry", "Harvey", "Harvie", "Harvinder", "Hasan", "Haseeb", "Hashem", "Hashim", "Hassan", "Hassanali", "Hately", "Havila",
                "Hayden", "Haydn", "Haydon", "Haydyn", "Hcen", "Hector", "Heddle", "Heidar", "Heini", "Hendri", "Henri", "Henry", "Herbert", "Heyden", "Hiro",
                "Hirvaansh", "Hishaam", "Hogan", "Honey", "Hong", "Hope", "Hopkin", "Hosea", "Howard", "Howie", "Hristomir", "Hubert", "Hugh", "Hugo", "Humza",
                "Hunter", "Husnain", "Hussain", "Hussan", "Hussnain", "Hussnan", "Hyden", "I", "Iagan", "Iain", "Ian", "Ibraheem", "Ibrahim", "Idahosa",
                "Idrees", "Idris", "Iestyn", "Ieuan", "Igor", "Ihtisham", "Ijay", "Ikechukwu", "Ikemsinachukwu", "Ilyaas", "Ilyas", "Iman", "Immanuel",
                "Inan", "Indy", "Ines", "Innes", "Ioannis", "Ireayomide", "Ireoluwa", "Irvin", "Irvine", "Isa", "Isaa", "Isaac", "Isaiah", "Isak", "Isher",
                "Ishwar", "Isimeli", "Isira", "Ismaeel", "Ismail", "Israel", "Issiaka", "Ivan", "Ivar", "Izaak", "J", "Jaay", "Jac", "Jace", "Jack", "Jacki",
                "Jackie", "Jack-James", "Jackson", "Jacky", "Jacob", "Jacques", "Jad", "Jaden", "Jadon", "Jadyn", "Jae", "Jagat", "Jago", "Jaheim", "Jahid",
                "Jahy", "Jai", "Jaida", "Jaiden", "Jaidyn", "Jaii", "Jaime", "Jai-Rajaram", "Jaise", "Jak", "Jake", "Jakey", "Jakob", "Jaksyn", "Jakub",
                "Jamaal", "Jamal", "Jameel", "Jameil"}; // 467 names

        String surnameArr[] = { "Aaberg", "Aaby", "Aadland", "Aagaard", "Aakre", "Aaland", "Aalbers", "Aalderink", "Aalund", "Aamodt", "Aamot",
                "Aanderud", "Aanenson", "Aanerud", "Aarant", "Aardema", "Aarestad", "Aarhus", "Aaron", "Aarons", "Aaronson", "Aarsvold", "Aas", "Aasby",
                "Aase", "Aasen", "Aavang", "Abad", "Abadi", "Abadie", "Abair", "Abaja", "Abajian", "Abalos", "Abaloz", "Abar", "Abarca", "Abare", "Abascal",
                "Abasta", "Abate", "Abati", "Abatiell", "Abato", "Abatti", "Abaunza", "Abaya", "Abbadessa", "Abbamonte", "Abbas", "Abbasi", "Abbassi", "Abbate",
                "Abbatiello", "Abbay", "Abbe", "Abbed", "Abbenante", "Abbey", "Abbinanti", "Abbington", "Abbitt", "Abbot", "Abbott", "Abboud", "Abbruzzese",
                "Abbs", "Abby", "Abdalla", "Abdallah", "Abdel", "Abdelal", "Abdelaziz", "Abdeldayen", "Abdelhamid", "Abdella", "Abdelmuti", "Abdelrahman",
                "Abdelwahed", "Abdi", "Abdin", "Abdo", "Abdon", "Abdool", "Abdou", "Abdul", "Abdula", "Abdulaziz", "Abdulkarim", "Abdulla", "Abdullah",
                "Abdullai", "Abdulmateen", "Abdulmuniem", "Abdur", "Abe", "Abeb", "Abed", "Abedelah", "Abedi", "Abee", "Abegg", "Abeita", "Abel", "Abela",
                "Abelar", "Abelardo", "Abele", "Abeles", "Abell", "Abella", "Abellera", "Abelman", "Abeln", "Abels", "Abelson", "Aben", "Abend", "Abendroth",
                "Aber", "Abercombie", "Abercrombie", "Aberle", "Abernatha", "Abernathy", "Abernethy", "Aberson", "Abes", "Abeta", "Abete", "Abetrani", "Abeyta",
                "Abide", "Abigantus", "Abila", "Abilay", "Abild", "Abilez", "Abina", "Abington", "Abitong", "Abke", "Abkemeier", "Ablang", "Ablao", "Able",
                "Ableman", "Abler", "Ables", "Ablin", "Abling", "Abner", "Abnet", "Abney", "Abo", "Abolafia", "Abolt", "Abood", "Aboshihata", "Aboud", "Aboudi",
                "Aboulahoud", "Aboulissan", "Abousaleh", "Aboytes", "Abplanalp", "Abrachinsky", "Abraham", "Abrahamian", "Abrahams", "Abrahamsen", "Abrahamson",
                "Abram", "Abramek", "Abramian", "Abramoff", "Abramov", "Abramovich", "Abramovitz", "Abramowitz", "Abramowski", "Abrams", "Abramson", "Abrantes",
                "Abreau", "Abrecht", "Abrego", "Abrell", "Abreo", "Abreu", "Abrev" };

        user.setSurname(surnameArr[ThreadLocalRandom.current().nextInt(0, 191)]);
        user.setName(nameArr[ThreadLocalRandom.current().nextInt(0, 467)]);

        user.setPhoneNumber(ThreadLocalRandom.current().nextLong(10_000_000, 99_999_999 + 1));
        user.setId(String.valueOf(ThreadLocalRandom.current().nextInt(10_000, 99_999 + 1)));

        user.setTownCall(ThreadLocalRandom.current().nextInt(1_000, 9_999 + 1));
        if(ThreadLocalRandom.current().nextInt(0, 3 + 1) == 3)
            user.setLongDistanceCall(ThreadLocalRandom.current().nextInt(1_000, 9_999 + 1));
        else
            user.setLongDistanceCall(0);

        return user;
    }
}
