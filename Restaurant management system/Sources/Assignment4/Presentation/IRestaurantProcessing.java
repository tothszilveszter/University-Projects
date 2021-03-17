package Assignment4;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.Serializable;

import javax.swing.*;
/**
 * Here is initialized the GUI for the restaurant that contains mostly the implementaion of frames,panels and buttons/
 * @author Toth Szilveszter
 *
 */
@SuppressWarnings("serial")
public class IRestaurantProcessing implements Serializable{
	
	public void show(Admin admin, Waiter waiter) {
		
		JFrame frame = new JFrame("Restaurant");

		frame.setSize(650, 160);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,2));
		JButton btn1=new JButton("Admin");
		JButton btn2=new JButton("Waiter");
		panel.add(btn1);
		panel.add(btn2);
		frame.add(panel,BorderLayout.NORTH);
		
		JPanel panel2=new JPanel();
		JPanel panel3=new JPanel();
		JPanel panel4=new JPanel();
		
		panel2.setLayout(new GridLayout(2,1));
		panel4.setLayout(new GridLayout(1,4));
		
		ButtonController cont=new ButtonController(admin,waiter);
		btn1.addActionListener(e -> {
			
			panel2.removeAll();
			panel3.removeAll();
			panel4.removeAll();
			
			JLabel txt1=new JLabel("Logged as: Admin. Enter product you want to work with.");
			panel2.add(txt1);
			JLabel prod=new JLabel("Product", JLabel.CENTER);
			panel4.add(prod);
			JTextField productName=new JTextField("");
			panel4.add(productName);
			JLabel pric=new JLabel("Price", JLabel.CENTER);
			panel4.add(pric);
			JTextField productPrice=new JTextField("");
			panel4.add(productPrice);
			panel2.add(panel4);
			
			JButton btnAdmin1=new JButton("Add Menu Item");
			JButton btnAdmin2=new JButton("Delete Menu Item");
			JButton btnAdmin3=new JButton("Edit Menu Item");
			JButton btnAdmin4=new JButton("Show Menu");
			
			btnAdmin3.addActionListener(e2 -> {
				JButton finish=new JButton("Finish update");
				JFrame frame2=new JFrame();
				frame2.setSize(300, 100);
				frame2.setLayout(new GridLayout(3,2));
				frame2.setLocationRelativeTo(null);
				frame2.setVisible(true);
				JLabel nprod=new JLabel("New Name", JLabel.CENTER);
				frame2.add(nprod);
				JTextField productNewName=new JTextField("");
				frame2.add(productNewName);
				JLabel npric=new JLabel("New Price", JLabel.CENTER);
				frame2.add(npric);
				JTextField productNewPrice=new JTextField("");
				frame2.add(productNewPrice);
				frame2.add(finish);
				cont.update(finish,productName,productPrice,productNewName,productNewPrice);
				finish.addActionListener(e3 -> {
					frame2.dispose();
				});
			});
			JTextArea text=new JTextArea();
			JScrollPane frame_for_text=new JScrollPane(text,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			btnAdmin4.addActionListener(e2 -> {
		
				JFrame frame2=new JFrame();
				frame2.setSize(300, 400);
				frame2.setLocationRelativeTo(null);
				text.updateUI();
				frame2.add(frame_for_text);
				frame2.setVisible(true);
			});
			
			cont.add(btnAdmin1,productName,productPrice);
			cont.remove(btnAdmin2,productName,productPrice);
			cont.showMenu(btnAdmin4,text);
			
			panel3.add(btnAdmin1);
			panel3.add(btnAdmin2);
			panel3.add(btnAdmin3);
			panel3.add(btnAdmin4);
			panel2.updateUI();
			panel3.updateUI();
			panel4.updateUI();
		});
		
		btn2.addActionListener(e -> {
			panel2.removeAll();
			panel3.removeAll();
			JLabel txt1=new JLabel("Logged as: Waiter. Enter new order!");
			JButton btnWaiter=new JButton("Finish order");
			JButton btnWaiterShow=new JButton("Show orders");
			panel3.add(btnWaiter);
			panel3.add(btnWaiterShow);
			panel2.add(txt1);
			panel2.updateUI();
			panel3.updateUI();
			JFrame frame2=new JFrame();
			frame2.setSize(300, 300);
			frame2.setLocationRelativeTo(null);
			frame2.setVisible(true);
			JTextArea orders=new JTextArea("Table num: \nEnter order in newline below: (product x quantity)\n");
			frame2.add(orders);
			cont.order(btnWaiter, orders);
			btnWaiter.addActionListener(e2 -> {
				frame2.dispose();
				panel3.remove(btnWaiter);
				JLabel succesText=new JLabel("For new order, press the Waiter button");
				panel3.add(succesText);
				panel3.updateUI();
			});
			
			JTextArea text=new JTextArea();
			JScrollPane frame_for_text=new JScrollPane(text,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			cont.showOrder(btnWaiterShow,text);
			btnWaiterShow.addActionListener(e2 -> {
		
				JFrame frame3=new JFrame();
				frame3.setSize(300, 400);
				frame3.setLocationRelativeTo(null);
				text.updateUI();
				frame3.add(frame_for_text);
				frame3.setVisible(true);
			});
		});
		
		panel.add(panel2);
		frame.add(panel,BorderLayout.NORTH);
		frame.add(panel3,BorderLayout.SOUTH);
		frame.setVisible(true);
	}

}


