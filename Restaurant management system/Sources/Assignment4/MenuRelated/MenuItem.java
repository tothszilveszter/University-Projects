package Assignment4;
/**
 * Here is the items in the menu defined. It has a name,price, and the third field (quantity) is used at the orders only. So when it's
 * freshly added to the menu, it's quantity will be always 0. Here I would like to mention, that this items are all like in title of
 * menues in real life (spaghetti bolognese, cheeseburger etc) so there aren't shown their composers (pasta, cheese etc.). I wasn't 
 * really sure how it should look like with the baseProduct and compositeProduct, so I thought that my solution would be quite 
 * appropriate too. 
 * @author Toth Szilveszter
 *
 */
public class MenuItem {
	
	public String name;
	public String orderQuantity;
	public String price;
	
	public MenuItem(String name, String orderQuantity, String price) {
		super();
		this.name = name;
		this.orderQuantity = orderQuantity;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(String orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
}
