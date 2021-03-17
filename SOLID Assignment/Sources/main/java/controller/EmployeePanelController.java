package controller;

import model.Client;
import model.validation.Notification;
import repository.EntityNotFoundException;
import service.ReportService;
import service.account.AccountService;
import service.client.ClientService;
import view.EmployeeView;
import view.PaymentPanelView;
import view.TransferPanelView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmployeePanelController {
    private Long currentEmployeeID;
    private final EmployeeView employeeView;
    private final ClientService clientService;
    private final AccountService accountService;
    private final ReportService reportService;

    public EmployeePanelController(Long currentEmployeeID, EmployeeView employeeView, ClientService clientService, AccountService accountService, ReportService reportService) {
        this.currentEmployeeID=currentEmployeeID;
        this.employeeView = employeeView;
        this.clientService = clientService;
        this.accountService = accountService;
        this.reportService=reportService;
        employeeView.setAddButtonListener(new AddButtonListener());
        employeeView.setUpdateClientButtonListener(new UpdateClientButtonListener());
        employeeView.setUpdateAccountButtonListener(new UpdateAccountButtonListener());
        employeeView.setDeleteButtonListener(new DeleteButtonListener());
        employeeView.setViewButtonListener(new ViewButtonListener());
        employeeView.setTransferButtonListener(new TransferButtonListener());
        employeeView.setPayButtonListener(new PayButtonListener());
    }

    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = employeeView.getName();
            String cardNumber = employeeView.getCardNumber();
            String CNP= employeeView.getCNP();
            String address= employeeView.getAddress();
            String type=employeeView.getAccountType();
            String date= employeeView.getDateOfCreation();
            String balance= employeeView.getBalance();

            String account=accountService.createAccount(type,date,balance);
            if (account != null) {
                Notification<Boolean> addNotification = null;
                addNotification = clientService.addClient(name, cardNumber, CNP, address, account);

                if (!addNotification.hasErrors()) {
                    String message = "Added client " + name + " with account" + account + " -> " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                    reportService.addEvent(currentEmployeeID, message);
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Registration successful!");
                }
                else
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), addNotification.getFormattedErrors());
            }
            else{

                JOptionPane.showMessageDialog(employeeView.getContentPane(), "Registration not successful. Check account data too.");
            }

        }
    }

    private class UpdateClientButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = employeeView.getID();
            String name = employeeView.getName();
            String cardNumber = employeeView.getCardNumber();
            String CNP= employeeView.getCNP();
            String address= employeeView.getAddress();
            if (!id.equals("")) {
                Client currentClient=null;
                try {
                    currentClient=clientService.findById(id);
                } catch (EntityNotFoundException entityNotFoundException) {
                    entityNotFoundException.printStackTrace();
                }
                if (currentClient!=null) {
                    if (clientService.updateClient(id, name, cardNumber, CNP, address)) {
                        String message = "Updated information at client with ID:" + id + " -> " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                        reportService.addEvent(currentEmployeeID, message);
                        JOptionPane.showMessageDialog(employeeView.getContentPane(), "Update successful!");
                    }
                } else {
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Update not successful.");
                }
            }
            else
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "ID required");
        }
    }

    private class UpdateAccountButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = employeeView.getID();
            String type=employeeView.getAccountType();
            String date= employeeView.getDateOfCreation();
            String balance= employeeView.getBalance();
            if (!id.equals("")) {
                Client client = null;
                try {
                    client = clientService.findById(id);
                } catch (EntityNotFoundException entityNotFoundException) {
                    entityNotFoundException.printStackTrace();
                }
                if (client!=null) {
                    if (accountService.updateAccount(client.getIdAccount(), type, date, balance)) {
                        String message = "Updated account information at client:" + id + " at account with ID:" + client.getIdAccount() + " -> " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                        reportService.addEvent(currentEmployeeID, message);
                        JOptionPane.showMessageDialog(employeeView.getContentPane(), "Update successful!");
                    }
                }else {
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Update not successful.");
                }
            }
            else
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "ID required");

        }
    }

    private class ViewButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            clientService.viewClients();
            accountService.viewAccounts();
            String message="Printed clients and accounts -> " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            reportService.addEvent(currentEmployeeID,message);
        }
    }

    private class DeleteButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String id = employeeView.getID();
            Client client=null;
            if (!id.equals("")) {
                try {
                    client = clientService.findById(id);
                } catch (EntityNotFoundException entityNotFoundException) {
                    entityNotFoundException.printStackTrace();
                }

                if (client != null) {
                    if (accountService.deleteAccount(client.getIdAccount())) {
                        String message = "Deleted a client" + " -> " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                        reportService.addEvent(currentEmployeeID, message);
                        JOptionPane.showMessageDialog(employeeView.getContentPane(), "Deleted!");
                    } else {
                        JOptionPane.showMessageDialog(employeeView.getContentPane(), "Not deleted.");
                    }
                }
                else
                    JOptionPane.showMessageDialog(employeeView.getContentPane(), "Did not find account with this ID.");
            }
            else
                JOptionPane.showMessageDialog(employeeView.getContentPane(), "ID required");

        }
    }

    private class TransferButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new TransferPanelController(currentEmployeeID,new TransferPanelView(), accountService, reportService);
        }
    }

    private class PayButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new PaymentPanelController(currentEmployeeID,new PaymentPanelView(),accountService, clientService,reportService);
        }
    }
}
