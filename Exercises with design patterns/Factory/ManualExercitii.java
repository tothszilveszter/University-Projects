public class ManualExercitii extends Carte{
    private String titlu;
    private String autor;
    private String nrPagini;

    public ManualExercitii(String titlu, String autor, String nrPagini) {
        this.titlu = titlu;
        this.autor = autor;
        this.nrPagini = nrPagini;
    }

    @Override
    public String getTitlu() {
        return this.titlu;
    }

    @Override
    public String getAutor() {
        return this.autor;
    }

    @Override
    public String getNrPag() {
        return this.nrPagini;
    }
}
