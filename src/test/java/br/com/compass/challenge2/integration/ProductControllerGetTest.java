package br.com.compass.challenge2.integration;

import br.com.compass.challenge2.integration.util.TestConfig;
import br.com.compass.challenge2.model.Product;
import br.com.compass.challenge2.repositories.ProductRepository;
import br.com.compass.challenge2.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@Import(TestConfig.class)
@Transactional
public class ProductControllerGetTest {
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


    @BeforeEach
    public void up(){
        productRepository.save(product);
    }

    @AfterEach
    public void down(){
        productRepository.deleteAll();
    }

    @Test
    @DisplayName("Get all products")
    public void testGetAllProductsSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Get product by ID")
    public void testGetProductByIdSuccess() throws Exception {
        when(productService.getProductById(anyInt())).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", product.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(product.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(product.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(product.getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value(product.getQuantity()));
    }

    @Test
    @DisplayName("Get product by ID - Not Numeric ID")
    public void testGetProductByIdNotNumericId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", "abc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Get product by ID - Negative ID")
    public void testGetProductByIdNegativeId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", -1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Get product by ID - Not Found")
    public void testGetProductByIdNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", 9999)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}