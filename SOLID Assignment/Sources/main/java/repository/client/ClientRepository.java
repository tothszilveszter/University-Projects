package repository.client;

import model.Client;
import repository.EntityNotFoundException;

import java.util.List;


public interface ClientRepository {

    List<Client> findAll();

    Client findById(String id) throws EntityNotFoundException;

    boolean add(Client client);

    boolean update(String oldClientID, Client newClient);

    void view();

    void removeAll();

}