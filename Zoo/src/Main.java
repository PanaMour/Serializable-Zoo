import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        boolean flag = true;//αρχικοποιούμε το flag με true
        while (flag) {//βρόχος μέχρι να γίνει το flag false δηλαδή όταν ο χρήστης επιλέξει την έξοδο
            System.out.println("Εφαρμογή Zoo");
            System.out.println("Παρακαλώ επιλέξτε από το παρακάτω menu επιλογών, γράφοντας τον αριθμό που αντιστοιχεί στην επιλογή σας.");
            System.out.println("--------------------------------------------------------------------------------------------------------");
            System.out.println("1.  Προβολή όλων των διαθέσιμων ζώων του ζωολογικού κήπου.");
            System.out.println("2.  Προσθήκη νέου ζώου.");
            System.out.println("3.  Αναζήτηση ζώου βάσει ονόματος.");
            System.out.println("4.  Αναζήτηση ζώου βάσει κωδικού.");
            System.out.println("5.  Επεξεργασία ζώου βάσει κωδικού.");
            System.out.println("6.  Διαγραφή ζώου βάσει κωδικού.");
            System.out.println("7.  Έξοδος από την εφαρμογή.");
            System.out.println("--------------------------------------------------------------------------------------------------------");

            Scanner scanner = new Scanner(System.in);//δημιουργούμε ένα αντικείμενο scanner για να πάρουμε input για την ενέργεία που θέλει να κάνει ο χρήστης
            int action = 0;
            while (action < 1 || action > 7) {//βρόχος μέχρι ο χρήστης να δώσει τιμή ανάμεσα στο 1 και 7
                while (!scanner.hasNextInt()) {//ελέγχει αν το input του χρήστη είναι int αν δεν είναι
                    System.out.println("Λάθος δεδομένα. Πρέπει να εισάγετε έναν αριθμό ανάμεσα στο 1 και στο 7.");//Εμφανίζει κατάλληλο μήνυμα
                    scanner.nextLine();//και διαβάζει το επόμενο του input μέχρι να είναι int
                }
                action = scanner.nextInt();//όταν το input του χρήστη είναι int το αποθηκεύουμε σε μία μεταβλητή
                if (action < 1 || action > 7){
                    System.out.println("Λάθος δεδομένα. Πρέπει να εισάγετε έναν αριθμό ανάμεσα στο 1 και στο 7.");
                }
            }
            switch (action) {//ανάλογα με το input του χρήστη μεταφερόμαστε στη κατάλληλη διαδικασία
                case 1://αν το input είναι 1
                    try {
                        readFile();//εκτελείται η συνάρτηση readFile()
                    } catch (IOException e) {//βάζουμε τα κατάλληλα exceptions
                        e.printStackTrace();
                        System.out.println("Δε βρέθηκαν ζώα μέσα στο αρχείο.");//ενημερώνω το χρήστη οτι υπάρχει error διοτι το αρχείο είναι άδειο
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;//βάζουμε break ώστε να μην γίνουν και τα άλλα cases
                case 2://αν το input είναι 2
                    createAnimal();//εκτελείται η συνάρτηση createAnimal()
                    break;
                case 3://αν το input είναι 3
                    System.out.println("Παρακαλώ εισάγετε το όνομα του ζώου του οποίου θέλετε να εμφανιστεί.");
                    String nam = scanner.next();//παίρνει input απο τον χρήστη
                    while(true) {//ατέρμονος βρόχος
                        if (nam.matches("(.*)(\\d+)(.*)")) {//αν το input του χρήστη δεν είναι σε string pattern (με βάση το regular expression)
                            System.out.println("Λάθος δεδομένα. Παρακαλώ εισάγετε το όνομα του ζώου.");//Εμφανίζει κατάλληλο μήνυμα
                            nam = scanner.next();//και διαβάζει το επόμενο του input μέχρι να είναι string
                        }else {
                            break;//όταν το input του χρήστη είναι της μορφής String τοτε βγαίνουμε από τον ατέρμονο βρόχο
                        }
                    }
                    try {
                        readName(nam);//εκτελείται η συνάρτηση readName()
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Δε βρέθηκαν ζώα μέσα στο αρχείο.");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4://αν το input είναι 4
                    System.out.println("Παρακαλώ εισάγετε τον κωδικό του ζώου του οποίου θέλετε να εμφανιστεί.");
                    while (!scanner.hasNextInt()) {//ελέγχει αν το input του χρήστη είναι int αν δεν είναι
                        System.out.println("Λάθος δεδομένα. Πρέπει να εισάγετε έναν φυσικό αριθμό.");//εμφανίζει κατάλληλο μήνυμα
                        scanner.nextLine();//και διαβάζει το επόμενο του input μέχρι να είναι int
                    }
                    int code = scanner.nextInt();//όταν το input του χρήστη είναι int το αποθηκεύουμε σε μία μεταβλητή
                    try {
                        readCode(code);//εκτελείται η συνάρτηση readCode()
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Δε βρέθηκαν ζώα μέσα στο αρχείο.");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 5://αν το input είναι 5
                    System.out.println("Παρακαλώ εισάγετε τον κωδικό του ζώου που θέλετε να επεξεργαστείτε.");
                    while (!scanner.hasNextInt()) {//ελέγχει αν το input του χρήστη είναι int αν δεν είναι
                        System.out.println("Λάθος δεδομένα. Πρέπει να εισάγετε έναν φυσικό αριθμό.");//εμφανίζει κατάλληλο μήνυμα
                        scanner.nextLine();//και διαβάζει το επόμενο του input μέχρι να είναι int
                    }
                    int editID = scanner.nextInt();//όταν το input του χρήστη είναι int το αποθηκεύουμε σε μία μεταβλητή
                    try {
                        EditAnimal(editID);//εκτελείται η συνάρτηση EditAnimal()
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Δε βρέθηκαν ζώα μέσα στο αρχείο.");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 6://αν το input είναι 6
                    System.out.println("Παρακαλώ εισάγετε τον κωδικό του ζώου του οποίου θέλετε να διαγράψετε.");
                    while (!scanner.hasNextInt()) {//ελέγχει αν το input του χρήστη είναι int αν δεν είναι
                        System.out.println("Λάθος δεδομένα. Πρέπει να εισάγετε έναν φυσικό αριθμό.");//εμφανίζει κατάλληλο μήνυμα
                        scanner.nextLine();//και διαβάζει το επόμενο του input μέχρι να είναι int
                    }
                    int delID = scanner.nextInt();//όταν το input του χρήστη είναι int το αποθηκεύουμε σε μία μεταβλητή
                    try {
                        deleteAnimal(delID);//εκτελείται η συνάρτηση deleteAnimal()
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Δε βρέθηκαν ζώα μέσα στο αρχείο.");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case 7://αν το input είναι 7
                    System.out.println("Έξοδος από την εφαρμογή.");
                    flag = false;//κάνουμε το flag false ώστε να βγούμε από τον βρόχο και να τελειώσει το πρόγραμμα
            }
            TimeUnit.SECONDS.sleep(3);//βάζω ένα μικρό delay ώστε να μην εμφανίζεται κατευθείαν το menu στον χρήστη και να έχει χρόνο να επεξεργαστεί τα δεδομένα που πήρε από τη συνάρτηση που επέλεξε
        }
    }

    //Συνάρτηση για προσπέλαση των ζώων μέσα στο αρχείο
    public static void writeToFile(Animal a) throws IOException {//δημιουργώ static συναρτήσεις για να μπορω να τις χρησιμοποιήσω στην main και βάζω τα κατάλληλα exceptions
        //δημιουργώ ένα αντικείμενο ObjectOutputStream και του περνάω μέσα το όνομα του αρχείου στο οποίο θέλω να μπουν τα στοιχεία μου και του δίνω στο append την τιμή true ώστε όταν θέλω να ξαναπεράσω στοιχεία να μη διαγράφονται τα προηγούμενα
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Animal.ser",true));
        objectOutputStream.writeObject(a);//γράφω το ζώο που μου έχει δώσει ο χρήστης μέσα στο αρχείο
        objectOutputStream.close();//κλείνω το OOS
    }

    public static void writeToFile(Animal a,String s) throws IOException {//υπερφόρτωση συναρτήσεων/ουσιαστικά είναι η writeToFile αλλα απλά βάζω να μπορεί να αλλάζει ο χρήστης το file location
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(s,true));
        objectOutputStream.writeObject(a);
        objectOutputStream.close();
    }

    //Συνάρτηση που επιστρέφει στον χρήστη τα ζώα που υπάρχουν στο αρχείο
    public static void readFile() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("Animal.ser");//δημιουργώ ένα αντικείμενο FileInputStream ώστε να δω απο που θα διαβάσω τις πληροφορίες μου
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);// και το περνάω μέσα σε ένα αντικείμενο ObjectInputStream

        try {
            Animal animal = null;//δημιουργώ ένα κενό αντικείμενο Animal
            while ((animal = (Animal) objectInputStream.readObject()) != null) {//δίνω τιμή στο κενό αντικείμενο να διαβάσει το πρώτο αντικείμενο που δεν ειναι κενό από το αρχειό(πρέπει να γίνει cast σε (Animal))
                System.out.println(animal);//εμφανίζω το ζώο αυτό
                objectInputStream = new ObjectInputStream(fileInputStream);//δημιουργώ καινούριο αντικείμενο objectInputStream ώστε να μπορεί να πάρει νέα τιμή το animal
            }

        } catch (EOFException e) {
            objectInputStream.close();
            }
    }

    //Συνάρτηση για δημιουργία ζώου από τον χρήστη
    public static void createAnimal(){
        Scanner scanner = new Scanner(System.in);//Φτιάχνω ένα αντικείμενο Scanner για να παίρνω δεδομένα από τον χρήστη
        System.out.println("Παρακαλώ εισάγετε τον κωδικό του ζώου.");
        while (!scanner.hasNextInt()) {//ελέγχει αν το input του χρήστη είναι int αν δεν είναι
            System.out.println("Λάθος δεδομένα. Πρέπει να εισάγετε έναν φυσικό αριθμό.");//εμφανίζει κατάλληλο μήνυμα
            scanner.nextLine();//και διαβάζει το επόμενο του input μέχρι να είναι int
        }
        int id = scanner.nextInt();//όταν το input του χρήστη είναι int το αποθηκεύουμε σε μία μεταβλητή
        System.out.println("Παρακαλώ εισάγετε το όνομα του ζώου");
        String name = scanner.next();//παίρνει input απο τον χρήστη
        while(true) {//ατέρμονος βρόχος
            if (name.matches("(.*)(\\d+)(.*)")) {//αν το input του χρήστη δεν είναι σε string pattern (με βάση το regular expression)
                System.out.println("Λάθος δεδομένα. Παρακαλώ εισάγετε το όνομα του ζώου.");//Εμφανίζει κατάλληλο μήνυμα
                name = scanner.next();//και διαβάζει το επόμενο του input μέχρι να είναι string
            }else {
                break;//όταν το input του χρήστη είναι της μορφής String τοτε βγαίνουμε από τον ατέρμονο βρόχο
            }
        }
        System.out.println("Παρακαλώ εισάγετε την ομοταξία του ζώου.");//ίδια διαδικασία με το name
        String omotajia = scanner.next();
        while(true) {
            if (omotajia.matches("(.*)(\\d+)(.*)")) {
                System.out.println("Λάθος δεδομένα. Παρακαλώ εισάγετε την ομοταξία του ζώου.");
                omotajia = scanner.next();
            }else {
                break;
            }
        }
        System.out.println("Παρακαλώ εισάγετε το βάρος του ζώου.(Η μονάδα μέτρησης θεωρείται το KG)");
        double weight = 0;//αρχικοποιώ το weight που δε μπορεί να πάρει (δεν υπάρχει ζωο με 0 βάρος)
        while(weight<=0) {//αρχίζουμε βρόχο ώστε να βρούμε γνήσια θετική τιμή
            while (!scanner.hasNextDouble()) {//διαβάζουμε το input του χρήστη μέχρι να είναι double
                System.out.println("Λάθος δεδομένα. Πρέπει να εισάγετε έναν αριθμό που αντιπροσωπεύει το μέσο βάρος του ζώου.");
                scanner.nextLine();
            }
            weight = scanner.nextDouble();//αποθηκεύουμε την τιμή μέχρι να είναι θετική
            if (weight <= 0){
                System.out.println("Λάθος δεδομένα. Πρέπει να εισάγετε έναν αριθμό που αντιπροσωπεύει το μέσο βάρος του ζώου.");
            }
        }
        System.out.println("Παρακαλώ εισάγετε τη μέγιστη ηλικία του ζώου.");
        int age = -1;//ίδια διαδικασία με το weight
        while(age<0) {
            while (!scanner.hasNextInt()) {
                System.out.println("Λάθος δεδομένα. Πρέπει να εισάγετε έναν φυσικό αριθμό.");
                scanner.nextLine();
            }
            age = scanner.nextInt();
            if (age < 0){
                System.out.println("Λάθος δεδομένα. Πρέπει να εισάγετε έναν φυσικό αριθμό.");
            }
        }
        System.out.println("Παρακαλώ εισάγετε το αγαπημένο φαγητό του ζώου.");
        String likes = scanner.next();//ίδια διαδικασία με το name
        while(true) {
            if (likes.matches("(.*)(\\d+)(.*)")) {
                System.out.println("Λάθος δεδομένα. Παρακαλώ εισάγετε το αγαπημένο φαγητό του ζώου.");
                likes = scanner.next();
            }else {
                break;
            }
        }
        System.out.println("Παρακαλώ εισάγετε αν το ζώο πέφτει σε χειμερία νάρκη.(Απαντήστε με YES ή NO)");
        String hibernates = scanner.next();//παίρνουμε το input του χρήστη
        boolean hibernation = false;
        while(!"YES".equals(hibernates) || !"NO".equals(hibernates)) {//περιμένουμε τιμή μέχρι να παραλάβουμε YES ή NO
            if (hibernates.equals("YES")) {
                hibernation = true;// αν είναι YES τοτε βάζουμε την κατάλληλη τιμή στο hibernation
                break;
            } else if (hibernates.equals("NO")) {
                hibernation = false;//και αντίστοιχα αν είναι NO
                break;
            }else{//αν δεν είναι τίποτα από τα δύο τοτε κανουμε τον χρήστη να μας ξαναδώσει τιμή
                System.out.println("Λάθος δεδομένα. Πρέπει να απαντήσετε με YES ή NO.");
                hibernates = scanner.next();
            }
        }
        //δημιουργούμε ένα καινούριο αντικείμενο Animal με τα στοιχεία που λάβαμε από τον χρήστη
        Animal createdAnimal = new Animal(id, name, omotajia, weight, age, likes, hibernation);

        try {
            writeToFile(createdAnimal);//χρησιμοποιούμε τη συνάρτηση writeToFile ώστε να προσθέσουμε το καινούριο ζώο στο αρχείο
            System.out.println("Τα στοιχεία του ζώου καταγράφηκαν επιτυχώς.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Συνάρτηση που διαβάζει τον κωδικό που δίνει ο χρήστης και αναζητά αν υπάρχει ζώο με αυτόν το κωδικό στο αρχείο και το εμφανίζει
    public static void readCode(int code) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("Animal.ser");//δημιουργώ ένα αντικείμενο FileInputStream ώστε να δω απο που θα διαβάσω τις πληροφορίες μου
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);// και το περνάω μέσα σε ένα αντικείμενο ObjectInputStream

        boolean i = false;//flag για να δούμε αν υπάρχει ζώο με το συγκεκριμένο κωδικό
        //Πολλά από τα βήματα είναι ίδια με τη συνάρτηση readFile()
        try {
            Animal animal = null;//δημιουργώ ένα κενό αντικείμενο Animal
            while ((animal = (Animal) objectInputStream.readObject()) != null) {//δίνω τιμή στο κενό αντικείμενο να διαβάσει το πρώτο αντικείμενο που δεν ειναι κενό από το αρχειό(πρέπει να γίνει cast σε (Animal))

                if (animal.getId() == code) {//ελέγχω αν ο κωδικός που μου έδωσε ο χρήστης αντιστοιχεί σε κάποιο κωδικό απο τα αντικείμενα που έχω στο αρχείο
                    System.out.println(animal);//και εμφανίζω όλα τα στοιχεία του στο χρήστη
                    i = true;//αν βρεθεί ζώο κάνουμε το i true ώστε να μην μας βγάλει μήνυμα ότι δεν βρέθηκε κανένα ζώο με τον κωδικό αυτό
                }
                objectInputStream = new ObjectInputStream(fileInputStream);//δημιουργώ καινούριο αντικείμενο objectInputStream ώστε να μπορεί να πάρει νέα τιμή το animal
            }

        } catch (EOFException e) {
            objectInputStream.close();
        }

        if(i == false){
            System.out.println("Δε βρέθηκε ζώο με τον κωδικό αυτό.");
        }
    }

    //Συνάρτηση που διαβάζει το όνομα που δίνει ο χρήστης και αναζητά αν υπάρχει ζώο με αυτό το όνομα στο αρχείο και το εμφανίζει
    public static void readName(String name) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("Animal.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        boolean i = false;//flag για να δούμε αν υπάρχει ζώο με το συγκεκριμένο όνομα
        //ουσιάστικα είναι η ίδια συνάρτηση με την readCode() απλά ελέγχει το όνομα αντί για τον κωδικό
        try {
            Animal animal = null;
            while ((animal = (Animal) objectInputStream.readObject()) != null) {

                if ((animal.getName()).equals(name)) {//ελέγχει αν το όνομα που έδωσε ο χρήστης αντιστοιχεί στο όνομα κάποιου ζώου στο αρχείο
                    System.out.println(animal);//αν ναι το εμφανίζει
                    i = true;
                }
                objectInputStream = new ObjectInputStream(fileInputStream);
            }

        } catch (EOFException e) {
            objectInputStream.close();
        }

        if(i == false){
            System.out.println("Δε βρέθηκε ζώο με το όνομα αυτό.");
        }
    }

    //Συνάρτηση που διαβάζει τον κωδικό που δίνει ο χρήστης και διαγράφει όποιο ζώο έχει τον ίδιο κωδικό από το αρχείο
    public static void deleteAnimal(int code) throws IOException, ClassNotFoundException {
        File oldFile = new File("Animal.ser");//όνομα αρχείου που είναι αποθηκευμένα όλα τα ζώα
        File newFile = new File("temp.ser");//προσωρινό αρχείο που θα αποθηκεύσουμε τα ζώα εκτός από αυτά που θέλουμε να διαγραφούν
        FileInputStream fileInputStream = new FileInputStream(oldFile);//δημιουργώ ένα αντικείμενο FileInputStream ώστε να δω απο που θα διαβάσω τις πληροφορίες μου
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);// και το περνάω μέσα σε ένα αντικείμενο ObjectInputStream

        int j = 0;//αρχικοποιώ έναν μετρητή
        try {
            Animal animal = null;//δημιουργώ ένα κενό αντικείμενο Animal
            int i = 0;//αρχικοποιώ δύο μετρητές για τις λίστες μου
            while ((animal = (Animal) objectInputStream.readObject()) != null) {//δίνω τιμή στο κενό αντικείμενο να διαβάσει το πρώτο αντικείμενο που δεν ειναι κενό από το αρχειό(πρέπει να γίνει cast σε (Animal))

                if (animal.getId() != code) {//όπως και στο readCode() ελέγχω αν ο κωδικός που έδωσε ο χρήστης αντιστοιχεί σε κάποιον από τους κωδικούς που έχουν τα ζώα του αρχείου
                    writeToFile(animal,"temp.ser");//και χρησιμοποιώντας την συνάρτηση writeToFile περνάω τα ζώα μέσα στο αρχείο temp.ser (το οποίο είναι αρχικά άδειο)
                }else {//αν ο κωδικός αντιστοιχεί με κάποιο ζώο του αρχείου τότε
                    System.out.println("Διαγράφτηκε το ζώο: " + animal);//και μέσω αυτής εμφανίζουμε στον χρήστη το ζώο που διαγράψαμε από το αρχείο
                    j++;//αυξάνω τον μετρητή
                }
                objectInputStream = new ObjectInputStream(fileInputStream);//δημιουργώ καινούριο αντικείμενο objectInputStream ώστε να μπορεί να πάρει νέα τιμή το animal
            }// στο τέλος της διαδικασίας αυτής στο αρχείο temp.ser θα είναι όλα τα ζώα εκτός απο αυτά που διαγράψαμε

        } catch (EOFException e) {
            objectInputStream.close();

        }

        oldFile.delete();//διαγράφω το αρχείο Animal.ser που είχε όλα τα ζώα αρχικά πριν διαγράψω
        File nameFile = new File("Animal.ser");//εφόσον δίεγραψα το Animal.ser δημιουργώ ένα καινούριο με το όνομα του που είναι άδειο
        newFile.renameTo(nameFile);//και μετονομάζω το αρχείο temp.ser σε Animal.ser ώστε να φαίνεται στον χρήστη σα να μη δημιουργήθηκε ποτέ ξεχωριστό αρχείο και απλά διεγραψα το ζώο από το τωρινό αρχείο


        if(j==0){//αν το j = 0 δηλαδή δεν βρεθεί ζώο με κωδικό να αντιστοιχεί στον κωδικό που έδωσε ο χρήστης τότε εμφανίζεται κατάλληλο μήνυμα
            System.out.println("Δεν βρέθηκε ζώο με τον συγκεκριμένο κωδικό.");
        }
    }

    //Συνάρτηση που διαβάζει τον κωδικό που δίνει ο χρήστης και επεξεργάζεται όποιο ζώο έχει τον ίδιο κωδικό από το αρχείο
    public static void EditAnimal(int code) throws IOException, ClassNotFoundException {
        File oldFile = new File("Animal.ser");//όνομα αρχείου που είναι αποθηκευμένα όλα τα ζώα
        File newFile = new File("temp.ser");//προσωρινό αρχείο που θα αποθηκεύσουμε τα ζώα εκτός από αυτά που θέλουμε να διαγραφούν
        FileInputStream fileInputStream = new FileInputStream(oldFile);//δημιουργώ ένα αντικείμενο FileInputStream ώστε να δω απο που θα διαβάσω τις πληροφορίες μου
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);// και το περνάω μέσα σε ένα αντικείμενο ObjectInputStream

        //Πολλά από τα βήματα είναι ίδια με τη συνάρτηση deleteAnimal()
        int j =0;
        try {
            Animal animal = null;//δημιουργώ ένα κενό αντικείμενο Animal
            while ((animal = (Animal) objectInputStream.readObject()) != null) {//δίνω τιμή στο κενό αντικείμενο να διαβάσει το πρώτο αντικείμενο που δεν ειναι κενό από το αρχειό(πρέπει να γίνει cast σε (Animal))

                if (animal.getId() != code) {//όπως και στο readCode() ελέγχω αν ο κωδικός που έδωσε ο χρήστης αντιστοιχεί σε κάποιον από τους κωδικούς που έχουν τα ζώα του αρχείου
                    writeToFile(animal,"temp.ser");//και χρησιμοποιώντας την συνάρτηση writeToFile περνάω τα ζώα μέσα στο αρχείο temp.ser (το οποίο είναι αρχικά άδειο)
                }else {//αν ο κωδικός αντιστοιχεί με κάποιο ζώο του αρχείου τότε
                    System.out.println("Επεξεργαστείτε το ζώο: " + animal);//και μέσω αυτής εμφανίζουμε στον χρήστη το ζώο που διαγράψαμε από το αρχείο
                    j++;//αυξάνω τον μετρητή
                }
                objectInputStream = new ObjectInputStream(fileInputStream);//δημιουργώ καινούριο αντικείμενο objectInputStream ώστε να μπορεί να πάρει νέα τιμή το animal
            }// στο τέλος της διαδικασίας αυτής στο αρχείο temp.ser θα είναι όλα τα ζώα εκτός απο αυτά που διαγράψαμε

        } catch (EOFException e) {
            objectInputStream.close();

        }

        oldFile.delete();//διαγράφω το αρχείο Animal.ser που είχε όλα τα ζώα αρχικά πριν διαγράψω
        File nameFile = new File("Animal.ser");//εφόσον δίεγραψα το Animal.ser δημιουργώ ένα καινούριο με το όνομα του που είναι άδειο
        newFile.renameTo(nameFile);//και μετονομάζω το αρχείο temp.ser σε Animal.ser ώστε να φαίνεται στον χρήστη σα να μη δημιουργήθηκε ποτέ ξεχωριστό αρχείο και απλά διεγραψα το ζώο από το τωρινό αρχείο


        if(j==0){//αν το j = 0 δηλαδή δεν βρεθεί ζώο με κωδικό να αντιστοιχεί στον κωδικό που έδωσε ο χρήστης τότε εμφανίζεται κατάλληλο μήνυμα
            System.out.println("Δεν βρέθηκε ζώο με τον συγκεκριμένο κωδικό.");
        }
        if(j!=0) {//αν έχει αλλάξει τιμή ο μετρητής δηλαδη δεν έχει βρει ζώο με τον αντίστοιχο κωδικό δεν εκτελείται το createAnimal
            System.out.println("Εισάγετε τα νέα στοιχεία που θέλετε να έχει το ζώο αυτό.");
            createAnimal();//και μέσω της συνάρτησης createAnimal() δημιουργώ ένα νέο ζώο με τα στοιχεία που βάζει ο χρήστης
            //έτσι φαίνεται σα να επεξεργάστηκε ο χρήστης το ζώο με τον κωδικό που έβαλε
        }
    }
}