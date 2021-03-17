import javax.swing.JButton;
import javax.swing.JTextField;

public class Controller {	//controllerul face legatura intre View si Model
	//pt fiecare buton este asociat un action listener care va apela metoda corespunzaotare din Model

	Model button = new Model();

	public void add(JButton butAdd, JTextField txt, JTextField txt2, JTextField txt3) {
		butAdd.addActionListener(e -> {
			String p1 = txt.getText();
			String p2 = txt2.getText();
			button.AdunareScadere(p1, p2, txt3, 0);
		});
	}

	public void sub(JButton butSub, JTextField txt, JTextField txt2, JTextField txt3) {
		butSub.addActionListener(e -> {
			String p1 = txt.getText();
			String p2 = txt2.getText();
			button.AdunareScadere(p1, p2, txt3, 1);
		});
	}

	public void mul(JButton butMul, JTextField txt, JTextField txt2, JTextField txt3) {
		butMul.addActionListener(e -> {
			String p1 = txt.getText();
			String p2 = txt2.getText();
			button.Inmultire(p1, p2, txt3);
		});
	}

	public void div(JButton butDiv, JTextField txt, JTextField txt2, JTextField txt3, JTextField txt4) {
		butDiv.addActionListener(e -> {
			String p1 = txt.getText();
			String p2 = txt2.getText();
			button.Inpartire(p1, p2, txt3, txt4);
		});
	}

	public void deriv(JButton butDeriv, JTextField txt, JTextField txt3) {
		butDeriv.addActionListener(e -> {
			String p1 = txt.getText();
			button.Derivare(p1, txt3);
		});
	}

	public void integ(JButton butInteg, JTextField txt, JTextField txt3) {
		butInteg.addActionListener(e -> {
			String p1 = txt.getText();
			button.Integrare(p1, txt3);
		});
	}

}
