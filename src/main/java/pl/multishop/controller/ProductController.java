package pl.multishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.multishop.domain.repository.ProductRepository;


/**
 * Created by michal on 05.05.17.
 */

@Controller
public class ProductController // Klasa kontrolera repozytorium produktów - warstwa danych.
{
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("/products")
    public String list(Model model)
    {
        /*Product VW_Polo = new Product("P0021", "VW Polo", new BigDecimal(45000));
        VW_Polo.setDescription("VW Polo - samochód osobowy klasy aut miejskich produkowany przez niemiecki koncern motoryzacyjny Volkswagen AG od 1978 roku.");
        VW_Polo.setCategory("car");
        VW_Polo.setManufacturer("Volkswagen");
        VW_Polo.setUnitsInStock(10);
        model.addAttribute("product", VW_Polo);*/

        model.addAttribute("products", productRepository.getAllProducts());
        return "products";
    }
}
