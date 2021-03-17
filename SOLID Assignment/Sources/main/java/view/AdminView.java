package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdminView extends JFrame {

    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnView;
    private JButton btnDel;
    private JButton btnRep;
    private JTextField userName;
    private JTextField id;
    private JTextField password;
    private JLabel userNameL;
    private JLabel passwordL;
    private JLabel idL;
    private JLabel info;
    private JPanel buttons;
    private JPanel textBoxes;

    public AdminView() throws HeadlessException {

        setSize(600, 500);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new GridLayout(2,1));
        buttons.setLayout(new GridLayout(6,1));
        textBoxes.setLayout(new GridLayout(3,2));
        buttons.add(info);
        buttons.add(btnAdd);
        buttons.add(btnUpdate);
        buttons.add(btnDel);
        buttons.add(btnView);
        buttons.add(btnRep);
        textBoxes.add(idL);
        textBoxes.add(id);
        textBoxes.add(userNameL);
        textBoxes.add(userName);
        textBoxes.add(passwordL);
        textBoxes.add(password);
        add(buttons);
        add(textBoxes);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields() {
        userName= new JTextField();
        password= new JTextField();
        id=new JTextField();

        info = new JLabel("Logged as admin");
        userNameL= new JLabel("User(email)");
        passwordL= new JLabel("Password");
        idL=new JLabel("ID (for update or to generate report)");
        buttons = new JPanel();
        textBoxes = new JPanel();

        btnAdd = new JButton("Add employee");
        btnUpdate = new JButton("Update employee");
        btnView = new JButton("List employee");
        btnDel = new JButton("Delete employee");
        btnRep = new JButton("Generate report");

    }

    public String getId(){ return id.getText();}
    public String getUserName() { return userName.getText();}
    public String getPassword() { return password.getText();}

    public void setAddButtonListener(ActionListener addButtonListener){
        btnAdd.addActionListener(addButtonListener);
    }

    public void setUpdateButtonListener(ActionListener updateButtonListener){
        btnUpdate.addActionListener(updateButtonListener);
    }


    public void setDeleteButtonListener(ActionListener deleteButtonListener){
        btnDel.addActionListener(deleteButtonListener);
    }

    public void setViewButtonListener(ActionListener viewButtonListener){
        btnView.addActionListener(viewButtonListener);
    }

    public void setReportButtonListener(ActionListener reportButtonListener){
        btnRep.addActionListener(reportButtonListener);
    }
}
