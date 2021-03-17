public class Factory {
    public static Carte printCarte(String tip, String titlu, String autor, String nrPagini){
        if (tip.equals("ManualExercitii")){
            return new ManualExercitii(titlu,autor,nrPagini);
        }
        else if (tip.equals("Dictionar")){
            return new Dictionar(titlu,autor,nrPagini);
        }
        return null;
    }
}
