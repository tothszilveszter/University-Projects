
public class Monom {  //clasa simpla, pt a putea lucra cu polinoamele in continuare
	private int coef;
	private int exp;
	public Monom(int coef, int exp) {
		super();
		this.coef = coef;
		this.exp = exp;
	}
	public double getCoef() {
		return coef;
	}
	public void setCoef(int coef) {
		this.coef = coef;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	 
}
