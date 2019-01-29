package pl.multishop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.multishop.dao.ClientDao;
import pl.multishop.model.Client;

import java.util.List;

@Service("clientService")
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;

    @Override
    public Client findById(Long clientId) {
        return clientDao.findById(clientId);
    }

    @Override
    public Client findBySurname(String clientSurname) {
        return clientDao.findBySurname(clientSurname);
    }

    @Override
    public void saveClient(Client client) {
        clientDao.saveClient(client);
    }

    @Override
    public void updateClient(Client client) {
        Client entity = clientDao.findById(client.getId());
        if(entity!=null){
            //entity.setClientId(client.getClientId()); //updatable = false
            entity.setFirstName(client.getFirstName());
            entity.setLastName(client.getLastName());
            entity.setHomeAdress(client.getHomeAdress());
            entity.setShippingAdress(client.getShippingAdress());
            entity.setCity(client.getCity());
            entity.setCountry(client.getCountry());
            entity.setEmail(client.getEmail());
            entity.setPhone(client.getPhone());
        }
    }

    @Override
    public void delClient(int clientId) {
        clientDao.delClient(clientId);
    }

    @Override
    public List<Client> findAllClients() {
        return clientDao.findAllClients();
    }
}
