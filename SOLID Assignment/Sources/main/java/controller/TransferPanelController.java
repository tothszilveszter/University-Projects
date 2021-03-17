package controller;

import model.Account;
import service.ReportService;
import service.account.AccountService;
import view.TransferPanelView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransferPanelController {
    private Long currentEmployeeID;
    private final TransferPanelView transferPanelView;
    private final AccountService accountService;
    private final ReportService reportService;

    public TransferPanelController(Long currentEmployeeID,TransferPanelView transferPanelView, AccountService accountService, ReportService reportService) {
        this.currentEmployeeID=currentEmployeeID;
        this.transferPanelView = transferPanelView;
        this.accountService = accountService;
        this.reportService=reportService;
        transferPanelView.setDoneButtonListener(new TransferButtonListener());
    }

    private class TransferButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String sourceID=transferPanelView.getSourceID();
            Account sourceAccount = accountService.findByID(sourceID);

            String destID=transferPanelView.getDestID();
            Account destAccount = accountService.findByID(destID);
            if (destID.equals("") || sourceID.equals("")){
                JOptionPane.showMessageDialog(transferPanelView.getContentPane(), "ID required");
            }
            else {
                String amount = transferPanelView.getAmount();
                int sourceBalance = Integer.parseInt(sourceAccount.getBalance()) - Integer.parseInt(amount);
                int destBalance = Integer.parseInt(destAccount.getBalance()) + Integer.parseInt(amount);

                if (sourceBalance >= 0) {
                    accountService.updateAccount(sourceID, sourceAccount.getAccountType(), sourceAccount.getDateOfCreation(), String.valueOf(sourceBalance));
                    accountService.updateAccount(destID, destAccount.getAccountType(), destAccount.getDateOfCreation(), String.valueOf(destBalance));

                    String message = "Transferred money from account:" + sourceID + " to account:" + destID + " -> " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                    reportService.addEvent(currentEmployeeID, message);

                    JOptionPane.showMessageDialog(transferPanelView.getContentPane(), "Transfer successful!");
                } else {
                    JOptionPane.showMessageDialog(transferPanelView.getContentPane(), "Transfer not successful.");
                }
                transferPanelView.dispose();
            }
        }
    }
}
