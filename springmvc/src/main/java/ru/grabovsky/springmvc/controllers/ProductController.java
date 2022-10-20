package ru.grabovsky.springmvc.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.grabovsky.springmvc.entity.Product;
import ru.grabovsky.springmvc.services.ProductService;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String getAllProducts(ModelMap model) {
        model.addAttribute("products", productService.getAllProducts());
        return "allProducts";
    }

    @GetMapping("/{id}")
    public String getProductById(ModelMap model, @PathVariable final Long id) {
        model.addAttribute("product", productService.getProductById(id));
        return "product";
    }

    @GetMapping("/{id}/delete")
    public String deleteProductById(@PathVariable final Long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String updateProductById(ModelMap model, @PathVariable final Long id) {
        model.addAttribute("product", productService.getProductById(id));
        return "addEditProduct";
    }

    @PostMapping("/add_edit")
    public String addProduct(Product product) {
        productService.newProduct(product);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String addProductForm(ModelMap model) {
        model.addAttribute("product", new Product());
        return "addEditProduct";
    }
}
