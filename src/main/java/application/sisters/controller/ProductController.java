package application.sisters.controller;

import application.sisters.domain.Product;
import application.sisters.rest.GeneralResponse;
import application.sisters.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static application.sisters.rest.GeneralResponse.*;

;


@CrossOrigin
@RestController
@Slf4j
@RequestMapping(value = "products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public ResponseEntity<GeneralResponse<List<Product>>> getAllProducts(){
        List<Product> allProducts = productService.getAllProducts();

        return new ResponseEntity<>(body(allProducts), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<GeneralResponse<Product>> getProductById(
            @PathVariable(name = "id") Long id
    ) {
        return new ResponseEntity<>(body(productService.getProductById()), HttpStatus.OK);
    }


}
