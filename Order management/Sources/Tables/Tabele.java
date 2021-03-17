package Tables;

import java.util.ArrayList;

import Objects.Clientul;
import Objects.Cumpara;
import Objects.Produs;

public class Tabele {
	public ArrayList<Clientul> clients=new ArrayList<>();
	public ArrayList<Cumpara> orders=new ArrayList<>();
	public ArrayList<Produs> products=new ArrayList<>();
	
	public Tabele(ArrayList<Clientul> clients, ArrayList<Cumpara> orders, ArrayList<Produs> products) {
		super();
		this.clients = clients;
		this.orders = orders;
		this.products = products;
	}

	public ArrayList<Clientul> getClients() {
		return clients;
	}

	public void setClients(ArrayList<Clientul> clients) {
		this.clients = clients;
	}

	public ArrayList<Cumpara> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Cumpara> orders) {
		this.orders = orders;
	}

	public ArrayList<Produs> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Produs> products) {
		this.products = products;
	}
	
}
