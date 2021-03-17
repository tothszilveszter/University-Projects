package Assignment4;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * This class implements the observer, which means that a signal is received when in the other classes happens something (in our case,
 * when an order is placed). The notification is shown in a new frame.
 * @author Toth Szilveszter
 *
 */
@SuppressWarnings({ "deprecation", "serial" })
public class Chef implements Observer, Serializable{

	@Override
	public void update(Observable o, Object arg) {
		JFrame frame2=new JFrame();
		frame2.setSize(350, 100);
		frame2.setLocationRelativeTo(null);
		JLabel text=new JLabel("The chef was notified and he's preparing the food!", JLabel.CENTER);
		frame2.add(text);
		frame2.setVisible(true);
		
	}

}
