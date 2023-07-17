package br.com.compass.challenge2.integration;

import br.com.compass.challenge2.integration.util.JsonUtils;
import br.com.compass.challenge2.DTO.ProductDTO;
import br.com.compass.challenge2.integration.util.TestConfig;
import br.com.compass.challenge2.model.Product;
import br.com.compass.challenge2.repositories.ProductRepository;
import br.com.compass.challenge2.service.ProductService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class ProductIntegrationPutTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Product product;

    @BeforeAll
    public void up(){
        productRepository.save(product);
    }

    @AfterAll
    public void down(){
        productRepository.deleteAll();
    }

    @Test
    @DisplayName("Update product")
    public void testUpdateProductSuccess() throws Exception {
        Product updatedProduct = new Product(1, "Updated Product", 19.99, 20);
        ProductDTO updatedProductDTO = new ProductDTO(updatedProduct.getId(), updatedProduct.getName(), updatedProduct.getPrice(), updatedProduct.getQuantity());
        when(productService.updateProduct(anyInt(), Mockito.any(Product.class))).thenReturn(updatedProduct);

        mockMvc.perform(MockMvcRequestBuilders.put("/products/{id}", product.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.asJsonString(updatedProductDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(updatedProduct.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(updatedProduct.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(updatedProduct.getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value(updatedProduct.getQuantity()));
    }

    @Test
    @DisplayName("Update non existing product")
    public void testUpdateNonExistingProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/products/{id}", 9999)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Updated Product\", \"price\": 9.99, \"quantity\": 10}"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testUpdateProductWithNonNumericId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/products/{id}",  "a")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Updated Product\", \"price\": 9.99, \"quantity\": 10}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testUpdateProductWithNegativeId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/products/{id}",  -9999)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Updated Product\", \"price\": 9.99, \"quantity\": 10}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }



}
