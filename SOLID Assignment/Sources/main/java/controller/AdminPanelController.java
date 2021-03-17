package controller;

import model.Employee;
import model.validation.Notification;
import service.ReportService;
import service.employee.AuthenticationService;
import view.AdminView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AdminPanelController {
    private Long currentID;
    private final AdminView adminView;
    private final AuthenticationService authenticationService;
    private final ReportService reportService;

    public AdminPanelController(Long currentID, AdminView adminView, AuthenticationService authenticationService, ReportService reportService) {
        this.currentID=currentID;
        this.adminView = adminView;
        this.authenticationService = authenticationService;
        this.reportService=reportService;
        adminView.setAddButtonListener(new AddButtonListener());
        adminView.setUpdateButtonListener(new UpdateButtonListener());
        adminView.setDeleteButtonListener(new DeleteButtonListener());
        adminView.setViewButtonListener(new ViewButtonListener());
        adminView.setReportButtonListener(new ReportButtonListener());
    }


    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String user=adminView.getUserName();
            String password=adminView.getPassword();

            Notification<Boolean> registerNotification = authenticationService.register(user, password);
            if (registerNotification.hasErrors()) {
                JOptionPane.showMessageDialog(adminView.getContentPane(), registerNotification.getFormattedErrors());
            } else {
                if (!registerNotification.getResult()) {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "Registration not successful, please try again later.");
                } else {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "Registration successful!");
                }
            }
        }
    }


    private class UpdateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id=adminView.getId();
            String user=adminView.getUserName();
            String password=adminView.getPassword();
            Employee wantedEmployee=authenticationService.findById(Long.valueOf(id));
            if (wantedEmployee!=null) {

                Notification<Boolean> registerNotification = authenticationService.update(id, user, password);
                if (registerNotification.hasErrors()) {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), registerNotification.getFormattedErrors());
                } else {
                    if (!registerNotification.getResult()) {
                        JOptionPane.showMessageDialog(adminView.getContentPane(), "Update not successful.");
                    } else {
                        JOptionPane.showMessageDialog(adminView.getContentPane(), "Update successful!");
                    }
                }
            }
            else
                JOptionPane.showMessageDialog(adminView.getContentPane(), "Entered ID not in database.");
        }
    }

    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id=adminView.getId();
            Employee wantedEmployee=authenticationService.findById(Long.valueOf(id));
            if (!id.equals(""))
            {
                if (wantedEmployee!=null) {
                    if (id.equals(String.valueOf(currentID))) {
                        JOptionPane.showMessageDialog(adminView.getContentPane(), "You can't delete yourself");
                    } else if (!authenticationService.delete(id)) {
                        JOptionPane.showMessageDialog(adminView.getContentPane(), "Delete not successful.");
                    } else {
                        JOptionPane.showMessageDialog(adminView.getContentPane(), "Deleted successfully!");
                    }
                }
                else
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "Entered ID not is database!");
            }
            else
                JOptionPane.showMessageDialog(adminView.getContentPane(), "Please give an id.");
        }
    }

    private class ViewButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            authenticationService.view();
        }
    }

    private class ReportButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (adminView.getId().equals(""))
                JOptionPane.showMessageDialog(adminView.getContentPane(), "Please give an id.");
            else
                {
                Long id=Long.valueOf(adminView.getId());
                boolean done = false;
                try {
                    done = reportService.generateReport(id);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                Employee wantedEmployee = authenticationService.findById(id);
                if (done && wantedEmployee!=null) {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "Report generated for " + wantedEmployee.getUsername());
                } else {
                    JOptionPane.showMessageDialog(adminView.getContentPane(), "Report was not generated. Possibly wrong ID entered");
                }
            }
        }
    }
}
