package pl.multishop.domain.repository.impl;

import org.springframework.stereotype.Repository;
import pl.multishop.domain.Product;
import pl.multishop.domain.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by michal on 06.05.17.
 */

@Repository
public class InMemoryProductRepository implements ProductRepository{

    private List<Product> listOfProducts = new ArrayList<Product>();

    public InMemoryProductRepository() {


        /*Product VW_Polo = new Product("P0021", "VW Polo", new BigDecimal(45000));
        VW_Polo.setDescription("VW Polo - samochód osobowy klasy aut miejskich produkowany przez niemiecki koncern motoryzacyjny Volkswagen AG od 1978 roku.");
        VW_Polo.setCategory("car");
        VW_Polo.setManufacturer("Volkswagen");
        VW_Polo.setUnitsInStock(10);

        Product VW_Golf = new Product("P0022", "VW Golf", new BigDecimal(67000));
        VW_Golf.setDescription("VW Golf - samochód osobowy klasy kompaktowej produkowany przez niemiecki koncern motoryzacyjny Volkswagen AG od 1974 roku.");
        VW_Golf.setCategory("Car");
        VW_Golf.setManufacturer("Volkswagen");
        VW_Golf.setUnitsInStock(7);

        Product VW_Touran = new Product("P0023", "VW Touran", new BigDecimal(88000));
        VW_Touran.setDescription("VW Touran - samochód osobowy typu minivan produkowany przez niemiecki koncern motoryzacyjny Volkswagen AG od 2003 roku.");
        VW_Touran.setCategory("Car");
        VW_Touran.setManufacturer("Volkswagen");
        VW_Touran.setUnitsInStock(8);*/

        Product iphone = new Product("P1001","iPhone 5s", new BigDecimal(2500));
        iphone.setDescription("Apple iPhone 5s, smartfon z 4-calowym ekranem o rozdzielczości 640×1136 i 8-megapikselowym aparatem");
        iphone.setCategory("smartfon");
        iphone.setManufacturer("Apple");
        iphone.setUnitsInStock(1000);

        Product laptop_dell = new Product("P1002","Dell Inspiron", new BigDecimal(2700));
        laptop_dell.setDescription("Dell Inspiron, 14-calowy laptop (czarny) z procesorami Intel Core 3. generacji");
        laptop_dell.setCategory("laptop");
        laptop_dell.setManufacturer("Dell");
        laptop_dell.setUnitsInStock(1000);

        Product tablet_Nexus = new Product("P1003","Nexus 7", new BigDecimal(1300));
        tablet_Nexus.setDescription("Google Nexus 7 jest najlżejszym 7-calowym tabletem z 4-rdzeniowym procesorem Qualcomm Snapdragon S4 Pro");
        tablet_Nexus.setCategory("tablet");
        tablet_Nexus.setManufacturer("Google");
        tablet_Nexus.setUnitsInStock(1000);

        //listOfProducts.add(VW_Polo);
        //listOfProducts.add(VW_Golf);
        //listOfProducts.add(VW_Touran);
        listOfProducts.add(iphone);
        listOfProducts.add(laptop_dell);
        listOfProducts.add(tablet_Nexus);
    }

    public List<Product> getAllProducts() {
        return listOfProducts;
    }

    public Product getProductById(String productId) {
        Product productById = null;

        for(Product product : listOfProducts) {
            if(product!=null && product.getProductId()!=null && product.getProductId().equals(productId)){
                productById = product;
                break;
            }
        }

        if(productById == null){
            throw new IllegalArgumentException("Brak produktu o wskazanym id: "+ productId);
        }

        return productById;
    }
}
