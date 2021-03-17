package service.client;

import model.Client;
import model.builder.ClientBuilder;
import model.validation.ClientValidator;
import model.validation.Notification;
import repository.EntityNotFoundException;
import repository.client.ClientRepository;


public class ClientServiceMySQL implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceMySQL(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    @Override
    public Notification<Boolean> addClient(String name, String cardNumber, String CNP, String address, String account) {

        Client client = new ClientBuilder()
                .setName(name)
                .setCardNumber(cardNumber)
                .setCNP(CNP)
                .setAddress(address)
                .setIdAccount(account)
                .build();

        ClientValidator clientValidator = new ClientValidator(client);
        boolean clientValid = clientValidator.validateClient();
        Notification<Boolean> clientNotification = new Notification<>();
        if (!clientValid)
        {
            clientValidator.getErrors().forEach(clientNotification::addError);
            clientNotification.setResult(Boolean.FALSE);

        }
        else{
            clientNotification.setResult(clientRepository.add(client));
        }
        return clientNotification;
    }

    @Override
    public boolean updateClient(String oldClientID, String name, String cardNumber, String CNP, String address) {

        Client client = new ClientBuilder()
                .setName(name)
                .setCardNumber(cardNumber)
                .setCNP(CNP)
                .setAddress(address)
                .build();

        ClientValidator clientValidator = new ClientValidator(client);
        boolean clientValid = clientValidator.validateClient();
        if (!clientValid)
            return false;

        if (clientRepository.update(oldClientID,client)){
            return true;
        }
        else{
            return false;
        }

    }


    @Override
    public void viewClients() {
        clientRepository.view();
    }

    @Override
    public Client findById(String id) throws EntityNotFoundException {
        return clientRepository.findById(id);
    }
}
