package pl.multishop.service;

import pl.multishop.model.Client;

import java.util.List;

public interface ClientService {

    public Client findById(int clientId);

    public Client findBySurname(String clientSurname);

    public void saveClient(Client client);

    public void updateClient(Client client);

    public void delClient(int clientId);

    public List<Client> findAllClients();
}
