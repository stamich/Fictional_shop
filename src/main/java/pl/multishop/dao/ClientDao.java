package pl.multishop.dao;

import pl.multishop.model.Client;

import java.util.List;

public interface ClientDao {

    public Client findById(Long clientId);

    public Client findBySurname(String clientSurname);

    public void saveClient(Client client);

    public void delClient(int clientId);

    public List<Client> findAllClients();

}
