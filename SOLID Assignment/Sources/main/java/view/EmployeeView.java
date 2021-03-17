package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class EmployeeView extends JFrame {

    private JTextField id;
    private JTextField name;
    private JTextField cardNumber;
    private JTextField CNP;
    private JTextField address;
    private JTextField type;
    private JTextField date;
    private JTextField balance;
    private JLabel idL;
    private JLabel nameL;
    private JLabel cardNumberL;
    private JLabel CNPL;
    private JLabel addressL;
    private JLabel typeL;
    private JLabel dateL;
    private JLabel balanceL;
    private JLabel info;
    private JButton btnAdd;
    private JButton btnUpdateClient;
    private JButton btnUpdateAccount;
    private JButton btnView;
    private JButton btnDel;
    private JButton btnTrans;
    private JButton btnPay;
    private JPanel buttons;
    private JPanel textBoxes;

    public EmployeeView() throws HeadlessException{

        setSize(600, 500);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new GridLayout(2,1));
        buttons.setLayout(new GridLayout(8,1));
        textBoxes.setLayout(new GridLayout(8,2));
        buttons.add(info);
        buttons.add(btnAdd);
        buttons.add(btnUpdateClient);
        buttons.add(btnUpdateAccount);
        buttons.add(btnDel);
        buttons.add(btnView);
        buttons.add(btnTrans);
        buttons.add(btnPay);
        textBoxes.add(idL);
        textBoxes.add(id);
        textBoxes.add(nameL);
        textBoxes.add(name);
        textBoxes.add(cardNumberL);
        textBoxes.add(cardNumber);
        textBoxes.add(CNPL);
        textBoxes.add(CNP);
        textBoxes.add(addressL);
        textBoxes.add(address);
        textBoxes.add(typeL);
        textBoxes.add(type);
        textBoxes.add(dateL);
        textBoxes.add(date);
        textBoxes.add(balanceL);
        textBoxes.add(balance);
        add(buttons);
        add(textBoxes);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields() {
        id= new JTextField();
        name= new JTextField();
        cardNumber= new JTextField();
        CNP= new JTextField();
        address= new JTextField();
        type= new JTextField();
        date= new JTextField();
        balance= new JTextField();
        idL= new JLabel("Client ID to handle personal data or their account");
        nameL= new JLabel("Name");
        cardNumberL= new JLabel("CardNumber");
        CNPL= new JLabel("CNP");
        addressL= new JLabel("Address");
        typeL= new JLabel("Account type(credit/debit)");
        dateL= new JLabel("Account creation date (yyyy/MM/dd)");
        balanceL= new JLabel("Account balance");
        buttons = new JPanel();
        textBoxes = new JPanel();
        info = new JLabel("Logged as employee");
        btnAdd = new JButton("Add client and account");
        btnUpdateClient = new JButton("Update client");
        btnUpdateAccount = new JButton("Update client's account");
        btnView = new JButton("List client and account");
        btnDel = new JButton("Delete account");
        btnTrans = new JButton("Money transfer");
        btnPay=new JButton("Pay bill");

    }

    public String getID() { return id.getText();}
    public String getName() { return name.getText();}
    public String getCardNumber() { return cardNumber.getText();}
    public String getCNP() { return CNP.getText();}
    public String getAddress() { return address.getText();}
    public String getAccountType(){ return type.getText();}
    public String getDateOfCreation() { return date.getText();}
    public String getBalance() {return balance.getText();}

    public void setAddButtonListener(ActionListener addButtonListener){
        btnAdd.addActionListener(addButtonListener);
    }

    public void setUpdateClientButtonListener(ActionListener updateClientButtonListener){
        btnUpdateClient.addActionListener(updateClientButtonListener);
    }

    public void setUpdateAccountButtonListener(ActionListener updateAccountButtonListener){
        btnUpdateAccount.addActionListener(updateAccountButtonListener);
    }

    public void setDeleteButtonListener(ActionListener deleteButtonListener){
        btnDel.addActionListener(deleteButtonListener);
    }

    public void setViewButtonListener(ActionListener viewButtonListener){
        btnView.addActionListener(viewButtonListener);
    }

    public void setTransferButtonListener(ActionListener transferButtonListener){
        btnTrans.addActionListener(transferButtonListener);
    }

    public void setPayButtonListener(ActionListener payButtonListener){
        btnPay.addActionListener(payButtonListener);
    }
}
