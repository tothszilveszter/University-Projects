package controller;

import model.Account;
import model.Client;
import repository.EntityNotFoundException;
import service.ReportService;
import service.account.AccountService;
import service.client.ClientService;
import view.PaymentPanelView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PaymentPanelController {
    private Long currentEmployeeID;
    private final PaymentPanelView paymentPanelView;
    private final AccountService accountService;
    private final ClientService clientService;
    private final ReportService reportService;

    public PaymentPanelController(Long currentEmployeeID, PaymentPanelView paymentPanelView, AccountService accountService, ClientService clientService, ReportService reportService) {
        this.currentEmployeeID=currentEmployeeID;
        this.paymentPanelView = paymentPanelView;
        this.accountService = accountService;
        this.clientService = clientService;
        this.reportService=reportService;
        paymentPanelView.setDoneButtonListener(new DoneButtonListener());
    }

    private class DoneButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String clientID=paymentPanelView.getSourceID();
            String name = paymentPanelView.getName();
            String amount = paymentPanelView.getAmount();
            if (!clientID.equals("") && !amount.equals("")) {
                Client client = null;
                try {
                    client = clientService.findById(clientID);
                } catch (EntityNotFoundException entityNotFoundException) {
                    entityNotFoundException.printStackTrace();
                }
                String sourceID = client.getIdAccount();
                Account sourceAccount = accountService.findByID(sourceID);

                int sourceBalance = Integer.parseInt(sourceAccount.getBalance()) - Integer.parseInt(amount);


                if (sourceBalance >= 0) {
                    accountService.updateAccount(sourceID, sourceAccount.getAccountType(), sourceAccount.getDateOfCreation(), String.valueOf(sourceBalance));
                    String message = "Client:" + clientID + " payed at their desk a(n) " + name + " bill" + " -> " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                    reportService.addEvent(currentEmployeeID, message);
                    JOptionPane.showMessageDialog(paymentPanelView.getContentPane(), "Successfully paid bill: " + name);
                } else {
                    JOptionPane.showMessageDialog(paymentPanelView.getContentPane(), "Payment not succcessful. Not enough money.");
                }
                paymentPanelView.dispose();
            }
            else
                JOptionPane.showMessageDialog(paymentPanelView.getContentPane(), "ID and money amount required");
        }
    }
}
