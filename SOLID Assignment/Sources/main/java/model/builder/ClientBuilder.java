package model.builder;

import model.Account;
import model.Client;
import model.Role;

import java.util.Date;
import java.util.List;

public class ClientBuilder {

    private Client client;

    public ClientBuilder() {
        client = new Client();
    }

    public ClientBuilder setIdClient(String id) {
        client.setId(id);
        return this;
    }


    public ClientBuilder setName(String name) {
        client.setName(name);
        return this;
    }

    public ClientBuilder setCardNumber(String cardNumber) {
        client.setCardNumber(cardNumber);
        return this;
    }

    public ClientBuilder setCNP(String CNP) {
        client.setCNP(CNP);
        return this;
    }


    public ClientBuilder setAddress(String address) {
        client.setAddress(address);
        return this;
    }

    public ClientBuilder setAccount(String idAccount) {
        client.setIdAccount(idAccount);
        return this;
    }

    public ClientBuilder setIdAccount(String id) {
        client.setIdAccount(id);
        return this;
    }

    public Client build() {
        return client;
    }


}