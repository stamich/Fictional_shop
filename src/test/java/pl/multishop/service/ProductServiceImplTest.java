package pl.multishop.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.multishop.dao.ProductDao;
import pl.multishop.model.Product;
import pl.multishop.service.ProductServiceImpl;

public class ProductServiceImplTest {

    @Mock
    ProductDao productDao;

    @InjectMocks
    ProductServiceImpl productService;

    @Spy
    List<Product> products = new ArrayList<Product>();

    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        products = getProductList();
    }

    @Test
    public void findById(){
        Product product = products.get(0);
        when(productDao.findById(anyInt())).thenReturn(product);
        Assert.assertEquals(productService.findById(product.getProductId()),product);
    }

    @Test
    public void findByName(){
        Product product = products.get(0);
        when(productDao.findByName(anyString())).thenReturn(product);
        Assert.assertEquals(productService.findByName(anyString()),product);
    }

    @Test
    public void saveProduct(){
        doNothing().when(productDao).saveProduct(any(Product.class));
        productService.saveProduct(any(Product.class));
        verify(productDao, atLeastOnce()).saveProduct(any(Product.class));
    }

    @Test
    public void updateProduct(){
        Product product = products.get(0);
        when(productDao.findById(anyInt())).thenReturn(product);
        productService.updateProduct(product);
        verify(productDao, atLeastOnce()).findById(anyInt());
    }

    @Test
    public void delProductById(){
        doNothing().when(productDao).delProductById(anyInt());
        productService.delProductById(anyInt());
        verify(productDao, atLeastOnce()).delProductById(anyInt());
    }

    @Test
    public void findAllProducts(){
        when(productDao.findAllProducts()).thenReturn(products);
        Assert.assertEquals(productService.findAllProducts(), products);
    }

    @Test
    public void findProductsByCategory(){
        Product product = products.get(0);
        when(productDao.findProductsByCategory(anyString())).thenReturn(product);
        Assert.assertEquals(productService.findProductsByCategory(anyString()), product);
    }

    @Test
    public void isProductNumberUnique(){
        Product product = products.get(0);
        when(productDao.findByName(anyString())).thenReturn(product);
        Assert.assertEquals(productService.isProductNumberUnique(product.getProductId(), product.getProductName()), true);
    }

    public List<Product> getProductList(){

        Product p1 = new Product();

        p1.setProductId(1);
        p1.setProductName("Iphone 6");
        p1.setUnitPrice(3998.59);
        p1.setProductDescription("Highendowy smartfon");
        p1.setProductManufacturer("Apple");
        p1.setProductCategory("smartfon");
        p1.setUnitsInStock(7);
        p1.setUnitsInOrder(1);
        p1.setActive(true);

        Product p2 = new Product();

        p2.setProductId(2);
        p2.setProductName("Samung Galaxy J7");
        p2.setUnitPrice(1059.69);
        p2.setProductDescription("Dobry smartfon");
        p2.setProductManufacturer("Samsung");
        p2.setProductCategory("smartfon");
        p2.setUnitsInStock(21);
        p2.setUnitsInOrder(2);
        p2.setActive(true);

        products.add(p1);
        products.add(p2);

        return products;
    }
}
