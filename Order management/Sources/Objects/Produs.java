package Objects;
/**
 * Creates an object which is defined as a product in the store, with name, price and quantity.
 * @author Toth Szilveszter
 *
 */
public class Produs {
	private int id;
	private String nume;
	private int cantitate;
	private float pret;
	
	public Produs(int id, String nume, int cantitate, float pret) {
		super();
		this.id = id;
		this.nume = nume;
		this.cantitate = cantitate;
		this.pret = pret;
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

	public int getCantitate() {
		return cantitate;
	}

	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}

	public float getPret() {
		return pret;
	}

	public void setPret(float pret) {
		this.pret = pret;
	}
	
	
		
}
