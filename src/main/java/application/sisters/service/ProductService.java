package application.sisters.service;

import application.sisters.domain.Product;
import application.sisters.domain.Size;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductService{

    private static List<Product> productList;

    @PostConstruct
    public void createProducts(){
        Random random = new Random(100);
        productList = Stream.generate(Product::new)
                .limit(5)
                .peek(createProduct(random))
                .collect(Collectors.toList());
    }

    private static Consumer<Product> createProduct(Random random) {
        return p -> {
            int id = random.nextInt();
            p.setId((long) id);
            p.setName("Product name = " + id);
            p.setSize(Size.M);
            p.setPrice(id*1.5);
        };
    }


    public  List<Product> getAllProducts(){
        return productList;
    }

    public  Product getProductById(){
        return productList.get(0);
    }
}
