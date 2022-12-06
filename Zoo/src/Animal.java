import java.io.Serializable;

public class Animal implements Serializable {//φτιάχνουμε τάξη Animal που κάνει implement το Serializable ώστε να μπορούμε να βάλουμε τα αντικείμενα μέσα σε αρχείο
    //Δηλώνουμε τις μεταβλητές αποτελούν τα στοιχεία που θα έχει ένα ζώο σε private μορφή για περισσότερη ασφάλεια
    private int id;
    private String name;
    private String omotajia;
    private double weight;
    private int age;
    private String likes;
    private boolean hibernates;

    //Δημιουργούμε ένα Constructor Animal που δέχεται ως ορίσματα μεταβλητές ώστε με την βοήθεια των αντίστοιχων Setters να αρχικοποιηθούν οι μεταβλητές που ορίσαμε παραπάνω
    public Animal(int id, String name,String omotajia,double weight,int age,String likes,boolean hibernates) {
        setId(id);
        setAge(age);
        setHibernates(hibernates);
        setLikes(likes);
        setName(name);
        setOmotajia(omotajia);
        setWeight(weight);
    }

    //Συνάρτηση toString() που μας επιστρέφει ουσιαστικά με την βοήθεια των Getters όλες τις ιδιότητες ενός αντικειμένου όταν καλούμε συναρτήσεις όπως η readFile()
    @Override//Το Override υπάρχει διότι χρησιμοποιούμε μία συνάρτηση που κληρονομήσαμε από την Object τάξη
    public String toString() {
        return getId() + "   " + getName() + "   " + getOmotajia() + "   " + getWeight() + "   " + getAge() + "   " + getLikes() + "   " + isHibernates();
    }

    //Όλοι οι Getters και οι Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOmotajia() {
        return omotajia;
    }

    public void setOmotajia(String omotajia) {
        this.omotajia = omotajia;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public boolean isHibernates() {
        return hibernates;
    }

    public void setHibernates(boolean hibernates) {
        this.hibernates = hibernates;
    }
}
