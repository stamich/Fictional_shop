package pl.multishop.dao;

import pl.multishop.model.Client;
import pl.multishop.model.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ClientDao {

    public Client findById(int clientId);

    public Client findBySurname(String clientSurname);

    public void saveClient(Client client);

    public void delClient(int clientId);

    public List<Client> findAllClients();

    public Set<Product> findProductsByFilter(Map<String, List<String>> filterParams);
}
