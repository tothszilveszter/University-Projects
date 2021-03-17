package service.client;

import model.Client;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.io.IOException;

public interface ClientService {
    Notification<Boolean> addClient(String name, String cardNumber, String CNP, String address, String account);

    boolean updateClient(String id,String name, String cardNumber, String CNP, String address);

    void viewClients();

    Client findById(String id) throws EntityNotFoundException;

}
