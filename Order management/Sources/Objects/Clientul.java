package Objects;
/**
 * Creates Client object with an id, name and town. 
 * @author Toth Szilveszter
 *
 */
public class Clientul {
	private int id;
	private String nume;
	private String oras;
	
	public Clientul(int id, String nume, String oras) {
		super();
		this.id = id;
		this.nume = nume;
		this.oras = oras;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getOras() {
		return oras;
	}

	public void setOras(String oras) {
		this.oras = oras;
	}
	
	
	
}
