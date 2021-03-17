package Assignment4;
import java.io.Serializable;
import java.util.ArrayList;


import javax.swing.*;
/**
 * In this class is stored the menu, because only the admin has right to modify its content (add, delete, update). This are the main
 * methods in this class along with auxuliary classes to eventually check if an item is already in the menu (so it's not necessary to
 * insert it again). Also it contains a method in order to show the entire menu and its products in it with their prices.
 * @author Toth Szilveszter
 *
 */
@SuppressWarnings("serial")
public class Admin implements Serializable {

	public ArrayList<MenuItem> menu=new ArrayList<MenuItem>();
	
	

	public ArrayList<MenuItem> getMenu() {
		return menu;
	}
	
	
	public boolean checkExist(String name)
	{
		for(MenuItem m : menu) {
			if (m.getName().equals(name))
				return true;
		}
		return false;
	}
	
	public void showMenu(JTextArea text) {
		String answer="The menu: (item - price)\n";
		
		for (MenuItem m : menu)
		{
			answer+=m.getName() + " - " + m.getPrice();
			answer+="\n";
		}
		text.setText(answer);
	}
	/**
	 * In these parameters should be the product name and the price.
	 * @param name
	 * @param price
	 */
	public void addProduct(String name, String price) {
		if(price.matches("[0-9]+")==false)
		{
			System.out.println("Price must be digits! Check input data!");
			
		}
		else
		{
			if (checkExist(name)==true) {
				System.out.println("Item already exists!");
			}
			else
			{
				MenuItem item=new MenuItem(name,"0",price);
				menu.add(item);	
				System.out.println("Item succesfully added!");
			}
			
		}
		
	}
	
	public int getIndex(MenuItem t) {
		int index=0;
		for (MenuItem m: menu) {
			if (m.getName().equals(t.getName())==true && m.getPrice().equals(t.getPrice())==true) {
				return index;
			}
			else
				index++;
		}
		return -1;
	}
	public void deleteProduct(String name, String price)
	{
		MenuItem item=new MenuItem(name,"0",price);
		int index=getIndex(item);
		if (index==-1)
			System.out.println("No such element in menu or doesn't match perfectly!");
		else
			{
				System.out.println("Item removed succesfully!");
				menu.remove(index);
			}
		
	}
	/**
	 * Here are two-two string that should contain the old name nad price and the new ones.
	 * @param productName
	 * @param productPrice
	 * @param productNewName
	 * @param productNewPrice
	 */
	public void updateProduct(String productName, String productPrice, String productNewName, String productNewPrice) {
		int ok=0;
		for(MenuItem m : menu)
		{
			if (m.getName().equals(productName)==true && m.getPrice().equals(productPrice)==true) {
				m.setName(productNewName);
				m.setPrice(productNewPrice);
				ok=1;
				System.out.println("Item updated succesfully!");
				break;
			}
		}
		if (ok==0)
			System.out.println("No such element in menu or doesn't match perfectly!");
		
	}
	
}
