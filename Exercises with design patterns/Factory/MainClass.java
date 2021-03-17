public class MainClass {
    public static void main(String[] args) {
        Factory editura=new Factory();
        Carte dictionar=editura.printCarte("Dictionar", "Romana-Engleza", "Enescu", "123");
        Carte manualExercitii=editura.printCarte("ManualExercitii", "ExercitiiClasa8", "Eminem", "100");
        System.out.println(dictionar.getTitlu());
        System.out.println(manualExercitii.getTitlu());
    }
}
