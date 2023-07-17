package br.com.compass.challenge2.integration;

import br.com.compass.challenge2.integration.util.TestConfig;
import br.com.compass.challenge2.model.Product;
import br.com.compass.challenge2.repositories.ProductRepository;
import br.com.compass.challenge2.service.ProductService;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class ProductIntegrationDeleteTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Product product;

    @Mock
    private ProductService productService;

    @BeforeAll
    public void up(){
        productRepository.save(product);
    }

    @AfterAll
    public void down(){
        productRepository.deleteAll();
    }

    @Test
    @DisplayName("Delete products")
    public void testDeleteProductSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/{id}", product.getId()))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @DisplayName("Delete non existing product")
    public void testUpdateNonExistingProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/{id}", 9999))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("Delete with non numeric ID")
    public void testUpdateProductWithNonNumericId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/{id}", "a"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Delete with negative ID")
    public void testUpdateProductWithNegativeId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/{id}", -1))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


}
