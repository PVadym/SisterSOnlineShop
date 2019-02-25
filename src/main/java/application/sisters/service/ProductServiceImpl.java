package application.sisters.service;

import application.sisters.domain.product.Product;
import application.sisters.domain.product.Size;
import application.sisters.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;


    @PostConstruct
    public void createProducts(){
        Random random = new Random(100);
        List<Product> collect = Stream.generate(Product::new)
                .limit(5)
                .peek(createProduct(random))
                .collect(Collectors.toList());
        productRepository.saveAll(collect);
    }

    private static Consumer<Product> createProduct(Random random) {
        return p -> {
            int id = random.nextInt();
            p.setName("Product name = " + id);
            p.setSize(Size.M);
            p.setPrice(id*1.5);
        };
    }


    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Product> findByName(String name) {
        return productRepository.findAllByName(name);
    }

    @Override
    public Collection<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }


    @Override
    public Product createOrUpdate(Product product) {
        return productRepository.save(product);
    }
}
