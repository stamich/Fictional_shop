package pl.multishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import pl.multishop.service.ProductService;

import java.util.List;
import java.util.Map;


/**
 * Created by michal on 05.05.17.
 */

@Controller
@RequestMapping("/products")
public class ProductController // Klasa kontrolera repozytorium produktów - warstwa danych.
{
    @Autowired
    //private ProductDao productRepository;
    private ProductService productService;

    @RequestMapping
    public String list(Model model)
    {
        /*Product VW_Polo = new Product("P0021", "VW Polo", new BigDecimal(45000));
        VW_Polo.setDescription("VW Polo - samochód osobowy klasy aut miejskich produkowany przez niemiecki koncern motoryzacyjny Volkswagen AG od 1978 roku.");
        VW_Polo.setCategory("car");
        VW_Polo.setManufacturer("Volkswagen");
        VW_Polo.setUnitsInStock(10);
        model.addAttribute("product", VW_Polo);*/

        //model.addAttribute("products", productRepository.getAllProducts());
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @RequestMapping("/all")
    public String allProducts(Model model)
    {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @RequestMapping("/{category}")
    public String getProductsByCategory(@PathVariable("category") String productCategory, Model model)
    {
        model.addAttribute("products", productService.getProductsByCategory(productCategory));
        return "products";
    }

    @RequestMapping("/filter/{ByCriteria}")
    public String getProductsByFilter(@MatrixVariable(pathVar="ByCriteria") Map<String,List<String>> filterParams, Model model)
    {
        model.addAttribute("products", productService.getProductsByFilter(filterParams));
        return "products";
    }

    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") String productId, Model model)
    {
        model.addAttribute("product", productService.getProductById(productId));
        return "product";
    }
}
