package pl.multishop.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pl.multishop.model.Client;

import java.util.List;

@Repository("clientDao")
public class ClientDaoImpl extends AbstractDao<Integer, Client> implements ClientDao{

    @Override
    public Client findById(int clientId) {
        return getById(clientId);
    }

    @Override
    public Client findBySurname(String clientSurname) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("clientSurname", clientSurname));
        return (Client) criteria.uniqueResult();
    }

    @Override
    public void saveClient(Client client) {
        persistEntity(client);
    }

    @Override
    public void delClient(int clientId) {
        Query query = getSession().createQuery("delete from Client where clientId = :clientId");
        query.setInteger("clientId", clientId);
        query.executeUpdate();
    }

    @Override
    public List<Client> findAllClients() {
        Criteria criteria = createEntityCriteria();
        return (List<Client>) criteria.list();
    }
}
