package repository.client;

import model.Client;
import repository.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ClientRepositoryMock implements ClientRepository {

    private List<Client> clients;

    public ClientRepositoryMock() {
        clients = new ArrayList<>();
    }

    public List<Client> findAll() {
        return clients;
    }

    public Client findById(String id) throws EntityNotFoundException {
        List<Client> filteredClients = clients.parallelStream()
                .filter(it -> it.getId().equals(id))
                .collect(Collectors.toList());
        if (filteredClients.size() > 0) {
            return filteredClients.get(0);
        }
        throw new EntityNotFoundException(Long.valueOf(id), Client.class.getSimpleName());

    }

    public boolean add(Client client) {
        return clients.add(client);
    }

    public boolean update(String oldClientID, Client newClient) {
        for(Client x : clients){
            if (x.getId().equals(oldClientID)){
                x.setId(newClient.getId());
                x.setName(newClient.getName());
                x.setIdAccount(newClient.getIdAccount());
                x.setAddress(newClient.getAddress());
                x.setCardNumber(newClient.getCardNumber());
                x.setCNP(newClient.getCNP());
                return true;
            }
        }
        return false;
    }

    public void view(){
        for(Client x : clients){
            System.out.println(x.getId() + " : " + x.getName() + " : " + x.getAddress() + " : " + x.getCardNumber() + " : " + x.getCNP() + " with account's id:" + x.getIdAccount());
        }
    }
    @Override
    public void removeAll() {
        clients.clear();
    }
}