package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class TransferPanelView extends JFrame{
    private JTextField sourceID;
    private JTextField destID;
    private JTextField amount;
    private JLabel sourceIDL;
    private JLabel destIDL;
    private JLabel amountL;
    private JButton btnDone;



    public TransferPanelView() throws HeadlessException {
        setSize(300, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4,2));
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        add(sourceIDL);
        add(sourceID);
        add(destIDL);
        add(destID);
        add(amountL);
        add(amount);
        add(btnDone);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields() {
        sourceIDL = new JLabel("Source account ID");
        destIDL = new JLabel("Destination account ID");
        amountL = new JLabel("Amount of money");
        sourceID = new JTextField();
        destID = new JTextField();
        amount = new JTextField();
        btnDone = new JButton("Finish transaction");
    }

    public String getSourceID() {
        return sourceID.getText();
    }

    public String getDestID() {
        return destID.getText();
    }

    public String getAmount() {
        return amount.getText();
    }

    public void setDoneButtonListener(ActionListener doneButtonListener) {
        btnDone.addActionListener(doneButtonListener);
    }
}
