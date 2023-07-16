package br.com.compass.challenge2.integration;

import br.com.compass.challenge2.model.Product;
import br.com.compass.challenge2.repositories.ProductRepository;
import br.com.compass.challenge2.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class ProductControllerPostTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Product product;

    @MockBean
    private ProductService productService;

    @AfterEach
    public void down(){
        productRepository.deleteAll();
    }

    @Test
    @DisplayName("Post product")
    public void testCreateProductSuccess() throws Exception {
        Product product1 = new Product();
        product1.setId(2);
        product1.setName("New Product");
        product1.setPrice(20.0);
        product1.setQuantity(10);

        var json = objectMapper.writeValueAsString(product1);

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
