package Assignment4;
import java.util.ArrayList;
/**
 * The order itself has 3 attributes: the table where the food should be brought and the menu from where it was chosen. Here is computed
 * it's id using the hashCode function.
 * @author Toth Szilveszter
 *
 */
public class Order {

	public int idTable;
	public ArrayList<MenuItem> menu;
	public Order(int idTable, ArrayList<MenuItem> menu) {
		super();
		this.idTable = idTable;
		this.menu = menu;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTable;
		result = prime * result + ((menu.get(0) == null) ? 0 : menu.get(0).hashCode());
		return result;
	}


	public int getIdTable() {
		return idTable;
	}
	public void setIdTable(int idTable) {
		this.idTable = idTable;
	}
	public ArrayList<MenuItem> getMenu() {
		return menu;
	}
	public void setMenu(ArrayList<MenuItem> menu) {
		this.menu = menu;
	}

}
