import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Model {
	//sunt definite pe rand toate metodele necesare pt a prelucra stringurile, de ex: transpunerea
	//lor in monoame, operatiile de adunare, scadere etc.
	
	ArrayList<Monom> polinom1 = new ArrayList<>();
	ArrayList<Monom> polinom2 = new ArrayList<>();
	
	public String Reface(String p1) {
		for (int i = 1; i < p1.length(); i++) {
			if (p1.charAt(i) == '-' && i != 0) {
				p1 = p1.substring(0, i) + "+" + p1.substring(i);
				i++;
			}
		}
		return p1;
	}
	
	public ArrayList<Monom> Descompune(String p1) {
		ArrayList<Monom> polaux = new ArrayList<>();
		ErrorTest err=new ErrorTest();
		err.Test(p1);
		String[] arr = p1.split("\\+", -2);
		int c = 0;
		int e = 0;
		int db = 0;
		for (String a : arr) {
			{
				String[] arr1 = a.split("\\^", -3);
				db = 0;
				c = 0;
				e = 0;
				for (String a1 : arr1) {
					if (db == 0) {
						if (a1.charAt(0) == '-') {
							for (int i = 1; i < a1.length() - 2; i++) {
								c *= 10;
								c = c + Integer.parseInt(String.valueOf(a1.charAt(i)));
							}
							c = -c;
						} else {
							for (int i = 0; i < a1.length() - 2; i++) {
								c *= 10;
								c = c + Integer.parseInt(String.valueOf(a1.charAt(i)));
							}
						}

					} else {
						for (int i = 0; i < a1.length(); i++) {
							e *= 10;
							e = e + Integer.parseInt(String.valueOf(a1.charAt(i)));

						}
					}
					db++;
				}
				Monom m = new Monom(c, e); 
				polaux.add(m);
			}
		}
		return polaux;
	}


	class Criteriu implements Comparator<Monom> {
		public int compare(Monom a, Monom b) {
			return b.getExp() - a.getExp();
		}
	}

	public void AdunareScadere(String p1, String p2, JTextField txt3, int o) {
		p1 = Reface(p1);
		p2 = Reface(p2);
		polinom1 = Descompune(p1);
		polinom2 = Descompune(p2);
		ArrayList<Monom> rez = new ArrayList<>();
		rez=AS_prop(polinom1, polinom2,o);
		String fin = "";
		for (Monom i : rez)
			if (i.getCoef() != 0) {
				if (i.getCoef() > 0)
					fin += "+";
				fin += Integer.toString((int) i.getCoef()) + "*x^" + Integer.toString((int) i.getExp());
			}
		txt3.setText(fin);
	}
	
	public ArrayList<Monom> AS_prop(ArrayList<Monom> p1,ArrayList<Monom> p2, int o)
	{
		ArrayList<Monom> rez = new ArrayList<>();
		int c = 0, e = 0;
		for (Monom i : p1) {
			for (Monom j : p2) {
				if (i.getExp() == j.getExp()) {
					if (o == 0)
						c = (int) (i.getCoef() + j.getCoef());
					else
						c = (int) (i.getCoef() - j.getCoef());
					e = i.getExp();
					i.setExp(-1);
					j.setExp(-1);
					Monom m = new Monom(c, e);
					rez.add(m);
				}
			}
		}
		for (Monom i : p1)
			if (i.getExp() >= 0)
				rez.add(i);
		for (Monom i : p2)
			if (i.getExp() >= 0) {
				if (o == 1) {
					int aux = (int) i.getCoef();
					aux = -aux;
					i.setCoef(aux);
				}
				rez.add(i);
			}
		Collections.sort(rez, new Criteriu());
		return rez;
	}
	
	public void Inmultire(String p1, String p2, JTextField txt3) {
		p1 = Reface(p1);
		p2 = Reface(p2);
		polinom1 = Descompune(p1);
		polinom2 = Descompune(p2);
		ArrayList<Monom> rez = new ArrayList<>();
		rez=Inmultire_prop(polinom1, polinom2);
		String fin = "";
		for (Monom i : rez)
			if (i.getCoef() != 0) {
				if (i.getCoef() > 0)
					fin += "+";
				fin += Integer.toString((int) i.getCoef()) + "*x^" + Integer.toString((int) i.getExp());
			}
		txt3.setText(fin);
	}
	
	public ArrayList<Monom> Inmultire_prop(ArrayList<Monom> p1,ArrayList<Monom> p2)
	{
		ArrayList<Monom> rez = new ArrayList<>();
		int c = 0, e = 0;
		for (Monom i : p1) {
			for (Monom j : p2) {
				c = (int) (i.getCoef() * j.getCoef());
				e = (int) (i.getExp() + j.getExp());
				Monom m = new Monom(c, e);
				rez.add(m);
			}
		}
		int ii = 0, jj = 0;
		for (Monom i : rez) {
			jj = 0;
			for (Monom j : rez) {
				if (i.getExp() == j.getExp() && ii != jj) {
					int m = (int) (i.getCoef() + j.getCoef());
					i.setCoef(m);
					j.setCoef(0);
				}
				jj++;
			}
			ii++;
		}
		Collections.sort(rez, new Criteriu());
		return rez;
	}
	
	public int grad(ArrayList<Monom> pol1)
	{
		int x=0;
		for (Monom i : pol1)
		{
			if (x<i.getExp()) {
				x=i.getExp();
			}
		}
		return x;
	}
	
	public void Inpartire(String p1, String p2, JTextField txt3, JTextField txt4) {
		p1=Reface(p1);
		p2=Reface(p2);
		polinom1=Descompune(p1);
		polinom2=Descompune(p2);
		ArrayList<Monom> cat = new ArrayList<>();
		ArrayList<Monom> cat2 = new ArrayList<>();
		ArrayList<Monom> rez = new ArrayList<>();
		while(grad(polinom1)>=grad(polinom2))
		{
			Monom impartit=polinom1.get(0);
			Monom impartitor=polinom2.get(0);
			Monom aux=new Monom((int)impartit.getCoef()/(int)impartitor.getCoef(),impartit.getExp()-impartitor.getExp());
			cat.add(aux);
			if (cat2.size()!=0)
				cat2.remove(0);
			cat2.add(aux);
			rez=Inmultire_prop(cat2,polinom2);
			polinom1=AS_prop(polinom1,rez,1);
			if (impartit.getCoef()==0)
					polinom1.remove(0);
			
		}
		String fin="";
		String fin1=""; 
		for (Monom i : cat)
			if (i.getCoef() != 0) {
				if (i.getCoef() > 0)
					fin += "+";
				fin += Integer.toString((int) i.getCoef()) + "*x^" + Integer.toString((int) i.getExp());
			}
		for (Monom i : polinom1)
			if (i.getCoef() != 0) {
				if (i.getCoef() > 0)
					fin1 += "+";
				fin1 += Integer.toString((int) i.getCoef()) + "*x^" + Integer.toString((int) i.getExp());
			}
		txt3.setText(fin);
		txt4.setText(fin1);
	}

	public void Derivare(String p1, JTextField txt3) {
		p1 = Reface(p1);
		polinom1 = Descompune(p1);
		for (Monom i : polinom1) {
			if (i.getExp() == 0)
				i.setCoef(0);
			int c = 0, e = 0;
			c = (int) i.getCoef();
			e = (int) i.getExp();
			i.setCoef(c * e);
			e--;
			i.setExp(e);

		}
		String fin = "";
		for (Monom i : polinom1)
			if (i.getCoef() != 0) {
				if (i.getCoef() > 0)
					fin += "+";
				fin += Integer.toString((int) i.getCoef()) + "*x^" + Integer.toString((int) i.getExp());
			}
		txt3.setText(fin);
	}

	public int lnko(int a, int b) {
		if (b == 0)
			return a;
		else
			return lnko(b, a % b);
	}
	
	public int abs(int a)
	{
		if (a<0) return -a;
		else return a;
	}

	public void Integrare(String p1, JTextField txt3) {
		p1 = Reface(p1);
		String fin = "";
		polinom1 = Descompune(p1);
		for (Monom i : polinom1) {
			int c = 0, e = 0;
			c = (int) i.getCoef();
			
			e = (int) i.getExp();
			e++;
			i.setExp(e);
			if (i.getCoef() > 0)
				fin += "+";
			int aux = lnko(abs(c), abs(e));
			e /= aux;
			c /= aux;
			if (e > 1)
				fin += Integer.toString((int) c) + "/" + Integer.toString((int) e) + "*x^"
						+ Integer.toString((int) i.getExp());
			else
				fin += Integer.toString((int) c) + "*x^" + Integer.toString((int) i.getExp());
		} 
		txt3.setText(fin);
	}	
}
