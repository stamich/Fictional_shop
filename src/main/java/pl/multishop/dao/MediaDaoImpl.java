package pl.multishop.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pl.multishop.model.Media;

import java.util.List;

@Repository("mediaDao")
public class MediaDaoImpl extends AbstractDao<String, Media> implements MediaDao {

    @Override
    public Media findById(String id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id", id));
        return (Media) criteria.uniqueResult();
    }

    @Override
    public Media findByOriginalName(String originalName) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("originalName", originalName));
        return (Media) criteria.uniqueResult();
    }

    @Override
    public void saveMedia(Media media) {
        persistEntity(media);
    }

    @Override
    public void deleteById(String id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id", id));
        Media media = (Media) criteria.uniqueResult();
        deleteEntity(media);
    }

    @Override
    public List<Media> findAll() {
        Criteria criteria = createEntityCriteria();
        criteria.addOrder(Order.asc("originalName"));
        return (List<Media>)criteria.list();
    }
}
