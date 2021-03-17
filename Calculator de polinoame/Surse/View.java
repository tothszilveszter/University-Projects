import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class View {		//aici se afla toate lucrurile care tin de interfata grafica

	public static void main(String[] args) {
		JFrame frame = new JFrame("Polinoame");
		
		frame.setSize(650, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();

		panel.setLayout(new GridLayout(2, 3));
		JLabel lbl = new JLabel("Polynom 1   ", JLabel.RIGHT);
		panel.add(lbl);
		JTextField txt = new JTextField("");
		txt.setSize(10, 100);
		panel.add(txt);

		JLabel lblaux = new JLabel("ex: a*x^2 + b*x^1 + c*x^0", JLabel.CENTER);
		panel.add(lblaux);

		JLabel lbl2 = new JLabel("Polynom 2   ", JLabel.RIGHT);
		panel.add(lbl2);
		JTextField txt2 = new JTextField("");
		txt2.setSize(10, 100);
		panel.add(txt2);

		panel2.setLayout(new GridLayout(1, 6));
		JButton butAdd = new JButton("ADD");
		JButton butSub = new JButton("SUB");
		JButton butMul = new JButton("MUL");
		JButton butDiv = new JButton("DIV");
		JButton butDeriv = new JButton("DERIVATE");
		JButton butInteg = new JButton("INTEGRATE");

		panel3.setLayout(new GridLayout(2, 3));
		JLabel lbl3 = new JLabel("Result   ", JLabel.RIGHT);
		panel3.add(lbl3);
		JTextField txt3 = new JTextField("");
		txt.setSize(10, 100);
		panel3.add(txt3);

		JLabel lblspa = new JLabel("");
		panel3.add(lblspa);

		JLabel lbl4 = new JLabel("Rest (if it's the case)   ", JLabel.RIGHT);
		panel3.add(lbl4);
		JTextField txt4 = new JTextField("");
		txt4.setSize(10, 100);
		panel3.add(txt4);

		Controller cont = new Controller();
		cont.add(butAdd, txt, txt2, txt3);
		cont.sub(butSub, txt, txt2, txt3);
		cont.mul(butMul, txt, txt2, txt3);
		cont.div(butDiv, txt, txt2, txt3, txt4);
		cont.deriv(butDeriv, txt, txt3);
		cont.integ(butInteg, txt, txt3);

		panel2.add(butAdd);
		panel2.add(butSub);
		panel2.add(butMul);
		panel2.add(butDiv);
		panel2.add(butDeriv);
		panel2.add(butInteg);

		frame.add(panel, BorderLayout.NORTH);
		frame.add(panel2, BorderLayout.CENTER);
		frame.add(panel3, BorderLayout.SOUTH);
		frame.setVisible(true);
	}

}