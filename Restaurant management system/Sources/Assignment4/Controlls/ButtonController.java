package Assignment4;

import javax.swing.*;
/**
 * This class realizes the connection between the ,,view,, which in case is the iRestaurantProcessing and the ,,model,, classes,
 * namely the Admin and the Waiter, if we compare to a MVC construction.
 * @author Toth Szilveszter
 *
 */
public class ButtonController {
	public Admin admin;
	public Waiter waiter;
	
	
	
	public ButtonController(Admin admin, Waiter waiter) {
		super();
		this.admin = admin;
		this.waiter = waiter;
	}

	

	public void showMenu(JButton btnAdmin4, JTextArea text) {
		btnAdmin4.addActionListener(e -> {
			admin.showMenu(text);
		});
	}
	
	public void showOrder(JButton btnWaiterShow, JTextArea text) {
		btnWaiterShow.addActionListener(e -> {
			waiter.showOrders(text);
		});
	}
	
	public void add(JButton btnAdmin1, JTextField txt1, JTextField txt2) {
		
		btnAdmin1.addActionListener(e -> {
			String productName=txt1.getText();
			String productPrice=txt2.getText();
			admin.addProduct(productName,productPrice);
		});
	}
	
	public void remove(JButton btnAdmin2, JTextField txt1, JTextField txt2) {
		btnAdmin2.addActionListener(e -> {
			String productName=txt1.getText();
			String productPrice=txt2.getText();
			admin.deleteProduct(productName,productPrice);
		});
	}
	
	public void update(JButton finish, JTextField txt1, JTextField txt2,JTextField txt3,JTextField txt4) {
		finish.addActionListener(e -> {
			String productName=txt1.getText();
			String productPrice=txt2.getText();
			String productNewName=txt3.getText();
			String productNewPrice=txt4.getText();
			admin.updateProduct(productName,productPrice,productNewName,productNewPrice);
		});
	}
	
	public void order(JButton button, JTextArea txt1) {
		button.addActionListener(e -> {
			String myOrder=txt1.getText();
			waiter.executeOrder(myOrder);
		});
	}
}
