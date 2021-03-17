package controller;

import model.Employee;
import model.validation.Notification;
import repository.employee.AuthenticationException;
import service.ReportService;
import service.account.AccountService;
import service.employee.AuthenticationService;
import service.client.ClientService;
import view.AdminView;
import view.EmployeeView;
import view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LoginController {
    private final LoginView loginView;
    private final AuthenticationService authenticationService;
    private final ClientService clientService;
    private final AccountService accountService;
    private final ReportService reportService;

    public LoginController(LoginView loginView, AuthenticationService authenticationService, ClientService clientService, AccountService accountService, ReportService reportService) {
        this.loginView = loginView;
        this.authenticationService = authenticationService;
        this.clientService = clientService;
        this.accountService=accountService;
        this.reportService=reportService;
        loginView.setLoginButtonListener(new LoginButtonListener());
        loginView.setRegisterButtonListener(new RegisterButtonListener());

    }

    private class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            List<String> userRole = new ArrayList<>();
            Notification<Employee> loginNotification = null;
            Long currentEmployeeID=null;
            try {
                loginNotification = authenticationService.login(username, password);
            } catch (AuthenticationException e1) {
                e1.printStackTrace();
            }

            if (loginNotification != null) {
                if (loginNotification.hasErrors()) {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), loginNotification.getFormattedErrors());
                } else {
                    loginNotification.getResult().getRoles().forEach(role -> userRole.add(role.getRole()));
                    currentEmployeeID=loginNotification.getResult().getId();
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Login successful!");
                    loginView.dispose();

                    for (String x : userRole) {
                        if (x.equals("employee")){

                            new EmployeePanelController(currentEmployeeID,new EmployeeView(), clientService, accountService, reportService);

                        }
                        else if (x.equals("administrator")){
                            new AdminPanelController(currentEmployeeID,new AdminView(),authenticationService,reportService);
                        }
                    }

                }
            }
        }
    }

    private class RegisterButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();
            Notification<Boolean> registerNotification = authenticationService.register(username, password);
            if (registerNotification.hasErrors()) {
                JOptionPane.showMessageDialog(loginView.getContentPane(), registerNotification.getFormattedErrors());
            } else {
                if (!registerNotification.getResult()) {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration not successful, please try again later.");
                } else {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration successful!");
                }
            }
        }
    }


}