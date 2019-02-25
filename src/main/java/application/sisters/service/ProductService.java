package application.sisters.service;

import application.sisters.domain.product.Product;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ProductService extends GeneralService <Product>{

}
