package pl.multishop.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.multishop.model.PersistentLogin;

import java.time.LocalDate;
import java.util.Date;

@Repository("tokenRepositoryDao")
@Transactional
public class HibernateTokenReposImpl extends AbstractDao<String, PersistentLogin> implements PersistentTokenRepository{

    @Override
    public void createNewToken(PersistentRememberMeToken persistentRememberMeToken) {
        PersistentLogin persistentLogin = new PersistentLogin();

        persistentLogin.setUserName(persistentRememberMeToken.getUsername());
        persistentLogin.setSeries(persistentRememberMeToken.getSeries());
        persistentLogin.setToken(persistentRememberMeToken.getTokenValue());
        persistentLogin.setLastUsed(persistentRememberMeToken.getDate());

        persistEntity(persistentLogin);
    }

    @Override
    public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
        PersistentLogin persistentLogin = getByKey(seriesId);
        persistentLogin.setToken(tokenValue);
        persistentLogin.setLastUsed(lastUsed);
        updateEntity(persistentLogin);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        try {
            Criteria criteria = createEntityCriteria();
            criteria.add(Restrictions.eq("series", seriesId));
            PersistentLogin persistentLogin = (PersistentLogin) criteria.uniqueResult();

            return new PersistentRememberMeToken(persistentLogin.getUserName(), persistentLogin.getSeries(),
                    persistentLogin.getToken(), persistentLogin.getLastUsed());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void removeUserTokens(String username) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("username", username));
        PersistentLogin persistentLogin = (PersistentLogin) criteria.uniqueResult();
        if (persistentLogin != null) {
            deleteEntity(persistentLogin);
        }
    }
}
