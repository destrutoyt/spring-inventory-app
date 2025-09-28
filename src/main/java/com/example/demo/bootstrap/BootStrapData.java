package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {

       /*
        OutsourcedPart o= new OutsourcedPart();
        o.setCompanyName("Western Governors University");
        o.setName("out test");
        o.setInv(5);
        o.setPrice(20.0);
        o.setId(100L);
        outsourcedPartRepository.save(o);
        OutsourcedPart thePart=null;
        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            if(part.getName().equals("out test"))thePart=part;
        }

        System.out.println(thePart.getCompanyName());
        */
        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            System.out.println(part.getName()+" "+part.getCompanyName());
        }

        /*
        Product bicycle= new Product("bicycle",100.0,15);
        Product unicycle= new Product("unicycle",100.0,15);
        productRepository.save(bicycle);
        productRepository.save(unicycle);
        */

        System.out.println("Application Started in Bootstrap");
        System.out.println("Number of Products: "+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts: "+partRepository.count());
        System.out.println(partRepository.findAll());

        if (partRepository.count()==0) {
            addParts();
        }
        if (productRepository.count()==0) {
            addProducts();
        }

        System.out.println("Data initialization complete.");
        System.out.println("\nProject by Miguel Garces Lenis\n");
    }
    public void addParts() {
        InhousePart part1 = new InhousePart();
        InhousePart part2 = new InhousePart();
        InhousePart part3 = new InhousePart();
        InhousePart part4 = new InhousePart();
        InhousePart part5 = new InhousePart();

        part1.setName("Frame");
        part1.setPrice(15.0);
        part1.setInv(10);
        part1.setMIN_INV(2);
        part1.setMAX_INV(20);

        part2.setName("Wheels");
        part2.setPrice(10.0);
        part2.setInv(15);
        part2.setMIN_INV(5);
        part2.setMAX_INV(25);

        part3.setName("Saddle");
        part3.setPrice(5.0);
        part3.setInv(20);
        part3.setMIN_INV(5);
        part3.setMAX_INV(30);

        part4.setName("Chain");
        part4.setPrice(8.0);
        part4.setInv(25);
        part4.setMIN_INV(15);
        part4.setMAX_INV(65);

        part5.setName("Handlebars");
        part5.setPrice(12.0);
        part5.setInv(30);
        part5.setMIN_INV(10);
        part5.setMAX_INV(50);

        partRepository.save(part1);
        partRepository.save(part2);
        partRepository.save(part3);
        partRepository.save(part4);
        partRepository.save(part5);

        System.out.println("Parts added successfully.");
    }

    private void addProducts() {
        Product product1 = new Product("Complete Road Bike", 250.0, 5);
        Product product2 = new Product("High Performing Wheels", 125.0, 5);
        Product product3 = new Product("Gel-padded Comfort Saddle", 100.0, 7);
        Product product4 = new Product("11-Speed Performance Chain", 80.0, 6);
        Product product5 = new Product("Ergonomic Handlebar with Integrated Grips", 150.0, 4);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);

        System.out.println("Products added successfully.");
    }
}
