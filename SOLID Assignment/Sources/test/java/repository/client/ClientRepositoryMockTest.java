package repository.client;

import model.Client;
import model.builder.ClientBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.Cache;
import repository.EntityNotFoundException;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ClientRepositoryMockTest {

    private static ClientRepository repository;
    private static final String id = "1";
    private static final String name = "Cristi";
    private static final String cardNumber = "123";
    private static final String CNP = "1234567890000";
    private static final String address = "str Mihail Kogalniceanu";
    @BeforeClass
    public static void setUp() {
        repository = new ClientRepositoryCacheDecorator(
                new ClientRepositoryMock(),
                new Cache<>()
        );
    }


    @Test
    public void findAll() throws Exception {
        List<Client> clients=repository.findAll();
        assertTrue(clients != null);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findById() throws Exception {
        Client client = repository.findById(id);
        assertTrue(client != null);
    }

    @Test
    public void save() throws Exception {
        Client client = new ClientBuilder()
                .setIdClient("Id")
                .setName("name")
                .setCardNumber("cardNumber")
                .setCNP("CNP")
                .setAddress("address")
                .build();

        assertTrue(repository.add(client));
    }

}