package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class PaymentPanelView extends JFrame{
    private JTextField sourceID;
    private JTextField name;
    private JTextField amount;
    private JLabel sourceIDL;
    private JLabel nameL;
    private JLabel amountL;
    private JButton btnDone;

    public PaymentPanelView() throws HeadlessException {
        setSize(300, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4,2));
        initializeFields();
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        add(nameL);
        add(name);
        add(sourceIDL);
        add(sourceID);
        add(amountL);
        add(amount);
        add(btnDone);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields() {
        sourceIDL = new JLabel("Client's ID");
        nameL = new JLabel("Bill to pay");
        amountL = new JLabel("Amount of money");
        sourceID = new JTextField();
        name = new JTextField();
        amount = new JTextField();
        btnDone = new JButton("Finish transaction");
    }

    public String getSourceID() {
        return sourceID.getText();
    }

    public String getName() {
        return name.getText();
    }

    public String getAmount() {
        return amount.getText();
    }

    public void setDoneButtonListener(ActionListener doneButtonListener) {
        btnDone.addActionListener(doneButtonListener);
    }
}
