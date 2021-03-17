package Assignment4;
import java.io.Serializable;

@SuppressWarnings("serial")
/**
 * Here is the constructor of the restaurant object (along with the getters and setter) where it is specified it's fields: admin, 
 * waiter, chef, it's gui. Worth mentioning that the orders ,,belong,, to the waiter. 
 * @author Toth Szilveszter
 *
 */
public class Restaurant implements Serializable{
	public Admin admin;
	public Waiter waiter;
	public Chef chef;
	public IRestaurantProcessing view;
	
	public Restaurant(Admin admin, Waiter waiter, Chef chef, IRestaurantProcessing view) {
		super();
		this.admin = admin;
		this.waiter = waiter;
		this.chef = chef;
		this.view = view;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Waiter getWaiter() {
		return waiter;
	}

	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}

	public Chef getChef() {
		return chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public IRestaurantProcessing getView() {
		return view;
	}

	public void setView(IRestaurantProcessing view) {
		this.view = view;
	}
	
}
