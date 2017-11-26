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
    public Client findById(int clientId) {
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
        Client entity = clientDao.findById(client.getClientId());
        if(entity!=null){
            //entity.setClientId(client.getClientId()); //updatable = false
            entity.setClientName(client.getClientName());
            entity.setClientSurname(client.getClientSurname());
            entity.setClientAdress(client.getClientAdress());
            entity.setClientCity(client.getClientCity());
            entity.setClientCountry(client.getClientCountry());
            entity.setClientEmail(client.getClientEmail());
            entity.setClientPhone(client.getClientPhone());
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
