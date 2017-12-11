package pl.multishop.controller;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.multishop.controller.ProductController;
import pl.multishop.model.Product;
import pl.multishop.service.ProductService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @Mock
    ProductService productService;

    @Mock
    MessageSource messageSource;

    @InjectMocks
    ProductController productController;

    @Spy
    List<Product> products = new ArrayList<Product>();

    @Spy
    ModelMap modelMap;

    @Mock
    BindingResult bindingResult;

    @BeforeClass
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        products = getProductList();
    }

    @Test
    public void listProducts(){
        when(productService.findAllProducts()).thenReturn(products);
        Assert.assertEquals(productController.listProducts(modelMap), "products");
        Assert.assertEquals(modelMap.get("products"), products);
        verify(productService, atLeastOnce()).findAllProducts();
    }

    @Test
    public void newProduct(){
        Assert.assertEquals(productController.newProduct(modelMap), "addProduct");
        Assert.assertNotNull(modelMap.get("product"));
        Assert.assertFalse((Boolean)modelMap.get("edit"));
        Assert.assertEquals(((Product)modelMap.get("product")).getProductId(), 0);
    }

    @Test
    public void saveProductWithValidationError(){
        when(bindingResult.hasErrors()).thenReturn(true);
        doNothing().when(productService).saveProduct(any(Product.class));
        Assert.assertEquals(productController.saveProduct(products.get(0), bindingResult, modelMap), "addProduct");
    }

    @Test
    public void saveProductWithValidationErrorNonUniqueSSN(){
        when(bindingResult.hasErrors()).thenReturn(false);
        when(productService.isProductNumberUnique(anyInt(), anyString())).thenReturn(false);
        Assert.assertEquals(productController.saveProduct(products.get(0), bindingResult, modelMap), "addProduct");
    }

    @Test
    public void saveProductWithSuccess(){
        when(bindingResult.hasErrors()).thenReturn(false);
        when(productService.isProductNumberUnique(anyInt(), anyString())).thenReturn(true);
        doNothing().when(productService).saveProduct(any(Product.class));
        Assert.assertEquals(productController.saveProduct(products.get(0), bindingResult, modelMap), "success");
        Assert.assertEquals(modelMap.get("success"), "Produkt numer 1 zapisany pomyślnie!");
    }

    @Test
    public void editProduct(){
        Product product = products.get(1);
        when(productService.findById(anyInt())).thenReturn(product);
        Assert.assertEquals(productController.editProduct(anyInt(), modelMap), "addProduct");
        Assert.assertNotNull(modelMap.get("product"));
        Assert.assertTrue((Boolean)modelMap.get("edit"));
        Assert.assertEquals(((Product)modelMap.get("product")).getProductId(), 2);
    }

    @Test
    public void updateProductWithValidationError(){
        when(bindingResult.hasErrors()).thenReturn(true);
        doNothing().when(productService).updateProduct(any(Product.class));
        Assert.assertEquals(productController.updateProduct(products.get(0), bindingResult, modelMap,1), "addProduct");
    }

    @Test
    public void updateProductWithValidationErrorNonUniqueNumber(){
        when(bindingResult.hasErrors()).thenReturn(false);
        when(productService.isProductNumberUnique(anyInt(), anyString())).thenReturn(false);
        Assert.assertEquals(productController.updateProduct(products.get(0), bindingResult, modelMap,1), "addProduct");
    }

    @Test
    public void updateProductWithSuccess(){
        when(bindingResult.hasErrors()).thenReturn(false);
        when(productService.isProductNumberUnique(anyInt(), anyString())).thenReturn(true);
        doNothing().when(productService).updateProduct(any(Product.class));
        Assert.assertEquals(productController.updateProduct(products.get(0), bindingResult, modelMap, 1), "success");
        Assert.assertEquals(modelMap.get("success"), "Produkt numer 1 zmieniony pomyślnie!");
    }

    @Test
    public void deleteProduct(){
        doNothing().when(productService).delProductById(anyInt());
        Assert.assertEquals(productController.deleteProduct(1), "redirect:/list");
    }

    public List<Product> getProductList(){

        Product p1 = new Product();

        p1.setProductId(1);
        p1.setProductName("Iphone 6");
        p1.setUnitPrice(3999.59);
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
