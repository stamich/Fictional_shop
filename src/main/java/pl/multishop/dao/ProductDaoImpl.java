package pl.multishop.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pl.multishop.model.Product;

import java.util.List;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDao<Integer, Product> implements ProductDao {

    @Override
    public Product findById(int productId) {
        return getById(productId);
    }

    @Override
    public Product findByName(String productName) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("productName", productName));
        return (Product) criteria.uniqueResult();
    }

    @Override
    public void saveProduct(Product product) {
        persistEntity(product);
    }

    @Override
    public void delProductById(int productId) {
        Query query = getSession().createQuery("delete from Product where productId = :productId");
        //query.setString("productId", productId);
        query.setInteger("productId", productId);
        query.executeUpdate();
    }

    @Override
    public List<Product> findAllProducts() {
        Criteria criteria = createEntityCriteria();
        return (List<Product>) criteria.list() ;
    }

    @Override
    public Product findProductsByCategory(String productCategory) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("productCategory", productCategory));
        return (Product) criteria.uniqueResult();
    }
}
