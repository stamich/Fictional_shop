package pl.multishop.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import pl.multishop.model.Product;

import java.util.List;

/**
 * This class extends AbstractDao class and implements ProductDao interface
 * for creating methods used with product model.
 * @author Michał Stawarski
 */
@Repository("productDao")
public class ProductDaoImpl extends AbstractDao<Integer, Product> implements ProductDao {

    /**
     * This method finds product by id.
     * @param productId
     * @return getById(productId)
     */
    @Override
    public Product findById(int productId) {
        return getById(productId);
    }

    /**
     * This method finds product by name.
     * @param productName
     * @return (Product) criteria.uniqueResult()
     */
    @Override
    public Product findByName(String productName) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("productName", productName));
        return (Product) criteria.uniqueResult();
    }

    /**
     * This method saves product.
     * @param product
     */
    @Override
    public void saveProduct(Product product) {
        persistEntity(product);
    }

    /**
     * This method deletes product.
     * @param productId
     */
    @Override
    public void delProductById(int productId) {
        Query query = getSession().createQuery("delete from Product where productId = :productId");
        //query.setString("productId", productId);
        query.setInteger("productId", productId);
        query.executeUpdate();
    }

    /**
     * This method makes the list of all products.
     * @return (List<Product>) criteria.list()
     */
    @Override
    public List<Product> findAllProducts() {
        Criteria criteria = createEntityCriteria();
        return (List<Product>) criteria.list() ;
    }

    /**
     * This method finds products by category.
     * @param productCategory
     * @return (Product) criteria.uniqueResult()
     */
    @Override
    public Product findProductsByCategory(String productCategory) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("productCategory", productCategory));
        return (Product) criteria.uniqueResult();
    }
}
