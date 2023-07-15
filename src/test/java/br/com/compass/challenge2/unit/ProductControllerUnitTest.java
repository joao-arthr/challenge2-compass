package br.com.compass.challenge2.unit;

import br.com.compass.challenge2.DTO.ProductDTO;
import br.com.compass.challenge2.controller.ProductController;
import br.com.compass.challenge2.model.Product;
import br.com.compass.challenge2.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;



@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class ProductControllerUnitTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    void getAllProducts_ReturnsListOfProductDTOs() {
        List<Product> products = Arrays.asList(
                new Product(1, "Product 1", 10.0, 5),
                new Product(2, "Product 2", 20.0, 10)
        );
        when(productService.getAllProducts()).thenReturn(products);

        ResponseEntity<List<ProductDTO>> responseEntity = productController.getAllProducts();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
    }

    @Test
    void getProductById_ValidId_ReturnsProductDTO() {
        int productId = 1;
        Product product = new Product(productId, "Product 1", 10.0, 5);
        when(productService.getProductById(productId)).thenReturn(product);

        ResponseEntity<ProductDTO> responseEntity = productController.getProductById(productId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productId, responseEntity.getBody().getId());
    }

    @Test
    void createProduct_ValidProductDTO_ReturnsCreatedProductDTO() {
        ProductDTO productDTO = new ProductDTO(1, "Product 1", 10.0, 5);
        Product product = new Product(1, "Product 1", 10.0, 5);
        when(productService.createProduct(any(Product.class))).thenReturn(product);

        ResponseEntity<ProductDTO> responseEntity = productController.createProduct(productDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productDTO.getId(), responseEntity.getBody().getId());
    }

    @Test
    void updateProduct_ValidIdAndProductDTO_ReturnsUpdatedProductDTO() {
        int productId = 1;
        ProductDTO productDTO = new ProductDTO(productId, "Product 1", 15.0, 7);
        Product product = new Product(productId, "Product 1", 15.0, 7);
        when(productService.updateProduct(eq(productId), any(Product.class))).thenReturn(product);

        ResponseEntity<ProductDTO> responseEntity = productController.updateProduct(productId, productDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(productId, responseEntity.getBody().getId());
        assertEquals(productDTO.getPrice(), responseEntity.getBody().getPrice());
        assertEquals(productDTO.getQuantity(), responseEntity.getBody().getQuantity());
    }

    @Test
    void deleteProduct_ValidId_ReturnsNoContent() {
        int productId = 1;

        ResponseEntity<Void> responseEntity = productController.deleteProduct(productId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(productService, times(1)).deleteProduct(eq(productId));
    }
}
