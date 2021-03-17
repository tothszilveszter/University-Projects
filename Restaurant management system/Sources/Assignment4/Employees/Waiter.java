package Assignment4;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Observable;

import javax.swing.*;
/**
 * 	This class is responsable for storing the orders in the restaurant and it also contains all the necesar methods in order to 
 * initialize new orders, which can be seen from the GUI. Also, in this class are generated the bills in form of .txt files. From 
 * here is the chef notified there food is needed.
 * @author Toth Szilveszter
 *
 */
@SuppressWarnings({ "deprecation", "serial" })
public class Waiter extends Observable implements Serializable{
	
	LinkedHashMap<Integer,Order> totalOrders=new LinkedHashMap<Integer,Order>();
	public Admin admin;
	
	
	public Waiter(Admin admin) {
		super();
		this.admin = admin;
	}

	public String getPrice(String name, ArrayList<MenuItem> menu) {
		String price=null;
		for (MenuItem m:menu) {
			if (m.getName().equals(name)==true) {
				price=m.getPrice();
			}
		}
		return price;
	}
	
	public static int billID=1;
	/**
	 * In these parameters is the necessary information in order to compute the bills, such as the order's id, the order itself and
	 * also the menu, from where we can find out the price of a product.
	 * @param id
	 * @param order
	 * @param restaurantMenu
	 * @throws IOException
	 */
	public void generateBill(int id, Order order,ArrayList<MenuItem> restaurantMenu) throws IOException
	{
		String name="Bill"+Integer.toString(billID)+".txt";
		FileWriter output=new FileWriter(name);
		String answer="";
		int totalPay=0;
		answer+="Order ID: ";
		answer+=Integer.toString(id);
		answer+="\n";
		answer+="Table num: ";	
		answer+=order.getIdTable();
		answer+="\n";
			for (MenuItem m: order.getMenu()) {
				answer += "Name: " + m.getName() + "; Quantity: " + m.getOrderQuantity() + "; Price: " + m.getPrice() + "\n";
				totalPay+=(int)(Integer.parseInt(m.getOrderQuantity()) * Integer.parseInt(m.getPrice()));
			}
		answer += "With a total payment of: ";
		answer += Integer.toString(totalPay);
		answer+="\n";
		output.write(answer);
		output.close();
		billID++;
	}
	
	public boolean checkExist(String name, ArrayList<MenuItem> menu)
	{
		for(MenuItem m : menu) {
			if (m.getName().equals(name))
				return true;
		}
		return false;
	}
	/**
	 * Basically here is the creation of a new order along with a multiple exception testings, like wrong input formats, empty order
	 * etc. In that method parameter should be the overall text which is from the textbox in the GUI.
	 * @param orderText
	 */
	public void executeOrder(String orderText) {
		
		ArrayList<MenuItem> restaurantMenu=admin.getMenu();
		String checkText="Table num: \nEnter order in newline below: (product x quantity)\n";
		if (orderText.equals(checkText))
		{
			System.out.println("You have to enter something in order to place the order!");
		}
		else
		{
		String[] arr=orderText.split("\n",-2);
		String tableNum=arr[0].substring(11);
		ArrayList<MenuItem> orderList = new ArrayList<MenuItem>();
		MenuItem x;
		int db=0;
		for (int i=2; i<arr.length; i++) {
			String[] arr2=arr[i].split(" x ",-2);
			
			if (checkExist(arr2[0],restaurantMenu))
			{
				db++;
				String price=getPrice(arr2[0],restaurantMenu);
				x=new MenuItem(arr2[0],arr2[1],price);
				orderList.add(x);
			}
			else
			{
				System.out.println(arr2[0] + " is currently not in menu!");
			}
		}
		if (db==0)
		{
			System.out.println("There are no other items to be served!");
		}
		else
		{
			if(tableNum.matches("[0-9]+")==false)
			{
				System.out.println("Table number must be digit! Retry!");
				
			}
			else {
			int tableN=Integer.parseInt(tableNum);
			Order order=new Order(tableN,orderList);
			int ID=order.hashCode();
			totalOrders.put(ID,order);
			try {
				generateBill(ID,order,restaurantMenu);
			} catch (IOException e) {
				e.printStackTrace();
			}
			setChanged();
			notifyObservers();
			}
		}
		
	}
}
	/**
	 * Shows orders in the textbox.
	 * @param text
	 */
	public void showOrders(JTextArea text) {
		String answer="";
		for (Integer i : totalOrders.keySet()) {
			answer+="Order ID: ";
			answer+=Integer.toString(i);
			answer+="\n";
			Order value=totalOrders.get(i);
			answer+="Table num: ";	
			answer+=value.getIdTable();
			answer+="\n";
			answer+="Ordered items: (item x quantity)\n";
				for (MenuItem m: value.getMenu()) {
					answer += m.getName() + " x " + m.getOrderQuantity() + "\n";
				}
				answer+="\n";
			}
		
		text.setText(answer);
	}
}
