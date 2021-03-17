package repository.client;

import model.Client;
import repository.Cache;
import repository.EntityNotFoundException;

import java.util.List;

public class ClientRepositoryCacheDecorator extends ClientRepositoryDecorator {

    private Cache<Client> cache;

    public ClientRepositoryCacheDecorator(ClientRepository clientRepository, Cache<Client> cache) {
        super(clientRepository);
        this.cache = cache;
    }

    @Override
    public List<Client> findAll() {
        if (cache.hasResult()){
            return cache.load();
        }
        List<Client> clients = decoratedRepository.findAll();
        cache.save(clients);
        return clients;
    }

    @Override
    public Client findById(String id) throws EntityNotFoundException {
        return decoratedRepository.findById(id);
    }

    @Override
    public boolean add(Client client) {
        cache.invalidateCache();
        return decoratedRepository.add(client);
    }

    @Override
    public boolean update(String oldClientID, Client newClient) {
        cache.invalidateCache();
        return decoratedRepository.update(oldClientID, newClient);
    }

    @Override
    public void view() {
        decoratedRepository.view();
    }

    @Override
    public void removeAll() {
        decoratedRepository.removeAll();
    }
}