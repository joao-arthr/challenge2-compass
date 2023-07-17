package br.com.compass.challenge2.integration.util;

import br.com.compass.challenge2.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public Product getProduct() {
        Product product = new Product();
        product.setId(1);
        product.setName("Product 1");
        product.setPrice(10.0);
        product.setQuantity(5);

        return product;
    }
}