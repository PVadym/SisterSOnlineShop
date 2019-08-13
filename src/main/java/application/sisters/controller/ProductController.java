package application.sisters.controller;

import application.sisters.domain.product.Product;
import application.sisters.rest.GeneralResponse;
import application.sisters.service.ProductServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

import static application.sisters.rest.GeneralResponse.body;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping(value = "products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @Value("${images.upload-path}")
    private String uploadPath;


    @GetMapping
    public ResponseEntity<GeneralResponse<Collection<Product>>> getAllProducts() {
        Collection<Product> allProducts = productService.getAll();

        return new ResponseEntity<>(body(allProducts), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("{id}")
    public ResponseEntity<GeneralResponse<Product>> getProductById(
            @PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(body(productService.findById(id)), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GeneralResponse> deleteProduct(
            @PathVariable(name = "id") Product product) {
        log.info("Product to delete =  {}", product);
        productService.delete(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GeneralResponse<Product>> createProduct(
            @RequestParam (value = "product") String productJson,
            @RequestParam (value = "file", required = false) MultipartFile file ) throws IOException {

        Product product = new ObjectMapper().readValue(productJson, Product.class);
        saveImage(file, product);

        Product savedProduct = productService .createOrUpdate(product);
        log.info("Product saved = {}", savedProduct);
        return new ResponseEntity<>(body(null), HttpStatus.OK);
    }

    private void saveImage(@RequestParam(value = "file", required = false) MultipartFile file, Product product) throws IOException {
        if(Objects.nonNull(file)){
            File uploadDir =new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath +"/" + resultFilename));
            product.setPicture(resultFilename);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<GeneralResponse<Product>> updateProduct(
            @PathVariable("id") Product productToUpdate,
            @RequestParam (value = "product") String productJson,
            @RequestParam (value = "file", required = false) MultipartFile file ) throws IOException {

        Product product = new ObjectMapper().readValue(productJson, Product.class);
        BeanUtils.copyProperties(product, productToUpdate, "id");
        // TODO: remove old img
        saveImage(file,productToUpdate);
        Product updatedProduct = productService.createOrUpdate(productToUpdate);
        log.info("Product updated successfully = {}", updatedProduct);
        return new ResponseEntity<>(body(updatedProduct), HttpStatus.OK);
    }


}

