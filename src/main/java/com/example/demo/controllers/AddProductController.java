package com.example.demo.controllers;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.PartService;
import com.example.demo.service.PartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 *
 *
 */
@Controller
public class AddProductController {
    @Autowired
    private ApplicationContext context;
    private PartService partService;
    private List<Part> theParts;
    private static Product product1;
    private Product product;

    @GetMapping("/showFormAddProduct")
    public String showFormAddPart(Model theModel) {
        theModel.addAttribute("parts", partService.findAll());
        product = new Product();
        product1=product;
        theModel.addAttribute("product", product);

        List<Part>availParts=new ArrayList<>();
        for(Part p: partService.findAll()){
            if(!product.getParts().contains(p))availParts.add(p);
        }
        theModel.addAttribute("availparts",availParts);
        theModel.addAttribute("assparts",product.getParts());
        return "productForm";
    }

    @PostMapping("/showFormAddProduct")
    public String submitForm(@Valid @ModelAttribute("product") Product product,
                            BindingResult bindingResult, Model theModel) {

        theModel.addAttribute("product", product);

        ProductService productService = context.getBean(ProductServiceImpl.class);
        PartService partService1 = context.getBean(PartServiceImpl.class);

        // Check if creating/updating would drop any part below MIN_INV
        boolean hasInventoryError = false;
        if(product.getId() != 0) {
            Product existing = productService.findById((int) product.getId());
            int invDiff = product.getInv() - existing.getInv();

            for (Part p : existing.getParts()) {
                int newInv = p.getInv() - invDiff;
                if (newInv < p.getMIN_INV()) {
                    bindingResult.reject("globalError",
                        "Cannot create/update product: part '" + p.getName() +
                        "' would fall below minimum inventory (" + p.getMIN_INV() + ")");
                    hasInventoryError = true;
                }
            }
        }

        if(bindingResult.hasErrors() || hasInventoryError){
            theModel.addAttribute("parts", partService.findAll());

            Product existing = new Product();
            try { existing = productService.findById((int) product.getId()); } catch (Exception ignored) {}

            List<Part> availParts = new ArrayList<>();
            for(Part p : partService.findAll()){
                if(!existing.getParts().contains(p)) availParts.add(p);
            }
            theModel.addAttribute("availparts", availParts);
            theModel.addAttribute("assparts", existing.getParts());

            return "productForm";
        }

        // Safe: update inventory and save
        if(product.getId() != 0){
            Product existing = productService.findById((int) product.getId());
            int invDiff = product.getInv() - existing.getInv();
            for(Part p : existing.getParts()){
                p.setInv(p.getInv() - invDiff);
                partService1.save(p);
            }
        } else {
            product.setInv(0);
        }

        productService.save(product);
        return "confirmationaddproduct";
    }

    @GetMapping("/showProductFormForUpdate")
    public String showProductFormForUpdate(@RequestParam("productID") int theId, Model theModel) {
        theModel.addAttribute("parts", partService.findAll());
        ProductService repo = context.getBean(ProductServiceImpl.class);
        Product theProduct = repo.findById(theId);
        product1=theProduct;
    //    this.product=product;
        //set the employ as a model attibute to prepopulate the form
        theModel.addAttribute("product", theProduct);
        theModel.addAttribute("assparts",theProduct.getParts());
        List<Part>availParts=new ArrayList<>();
        for(Part p: partService.findAll()){
            if(!theProduct.getParts().contains(p))availParts.add(p);
        }
        theModel.addAttribute("availparts",availParts);
        //send over to our form
        return "productForm";
    }

    @GetMapping("/deleteproduct")
    public String deleteProduct(@RequestParam("productID") int theId, Model theModel) {
        ProductService productService = context.getBean(ProductServiceImpl.class);
        Product product2=productService.findById(theId);
        for(Part part:product2.getParts()){
            part.getProducts().remove(product2);
            partService.save(part);
        }
        product2.getParts().removeAll(product2.getParts());
        productService.save(product2);
        productService.deleteById(theId);

        return "confirmationdeleteproduct";
    }

    @GetMapping("/buyproduct")
    public String buyProduct(@RequestParam("productID") int theId, Model theModel) {
        ProductService productService = context.getBean(ProductServiceImpl.class);
        Product product2 = productService.findById(theId);

        if(product2.getInv()>0) {
            product2.setInv(product2.getInv() - 1);
            productService.save(product2);
            return "confirmationbuyproduct";
        }
        else {
            return "failedproductpurchase";
        }
    }

    public AddProductController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping("/associatepart")
    public String associatePart(@Valid @RequestParam("partID") int theID, Model theModel){
    //    theModel.addAttribute("product", product);
    //    Product product1=new Product();
        if (product1.getName()==null) {
            return "saveproductscreen";
        }
        else{
        product1.getParts().add(partService.findById(theID));
        partService.findById(theID).getProducts().add(product1);
        ProductService productService = context.getBean(ProductServiceImpl.class);
        productService.save(product1);
        partService.save(partService.findById(theID));
        theModel.addAttribute("product", product1);
        theModel.addAttribute("assparts",product1.getParts());
        List<Part>availParts=new ArrayList<>();
        for(Part p: partService.findAll()){
            if(!product1.getParts().contains(p))availParts.add(p);
        }
        theModel.addAttribute("availparts",availParts);
        return "confirmationassocpart";
        }
    }
    @GetMapping("/removepart")
    public String removePart(@RequestParam("partID") int theID, Model theModel){
        theModel.addAttribute("product", product);
      //  Product product1=new Product();
        product1.getParts().remove(partService.findById(theID));
        partService.findById(theID).getProducts().remove(product1);
        ProductService productService = context.getBean(ProductServiceImpl.class);
        productService.save(product1);
        partService.save(partService.findById(theID));
        theModel.addAttribute("product", product1);
        theModel.addAttribute("assparts",product1.getParts());
        List<Part>availParts=new ArrayList<>();
        for(Part p: partService.findAll()){
            if(!product1.getParts().contains(p))availParts.add(p);
        }
        theModel.addAttribute("availparts",availParts);
        return "productForm";
    }
}
