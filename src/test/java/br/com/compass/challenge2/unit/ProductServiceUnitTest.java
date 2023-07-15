package br.com.compass.challenge2.unit;

import br.com.compass.challenge2.model.Product;
import br.com.compass.challenge2.repositories.ProductRepository;
import br.com.compass.challenge2.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceUnitTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    @DisplayName("Get all products")
    void getAllProducts_ReturnsListOfProducts() {

        List<Product> expectedProducts = Arrays.asList(
                new Product(1, "Product 1", 10.0, 5),
                new Product(2, "Product 2", 20.0, 10)
        );
        when(productRepository.findAll()).thenReturn(expectedProducts);


        List<Product> actualProducts = productService.getAllProducts();


        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    @DisplayName("Get product by ID")
    void getProductById_ValidId_ReturnsProduct() {

        int productId = 1;
        Product expectedProduct = new Product(productId, "Product 1", 10.0, 5);
        when(productRepository.findById(productId)).thenReturn(Optional.of(expectedProduct));


        Product actualProduct = productService.getProductById(productId);


        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void getProductById_InvalidId_ThrowsRuntimeException() {
        int productId = 1;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> productService.getProductById(productId));
    }

    @Test
    @DisplayName("Create product")
    void createProduct_ValidProduct_ReturnsCreatedProduct() {
        Product productToCreate = new Product(1, "Product 1", 10.0, 5);
        Product expectedCreatedProduct = new Product(1, "Product 1", 10.0, 5);
        when(productRepository.save(productToCreate)).thenReturn(expectedCreatedProduct);

        Product actualCreatedProduct = productService.createProduct(productToCreate);

        assertEquals(expectedCreatedProduct, actualCreatedProduct);
    }

    @Test
    @DisplayName("Update product")
    void updateProduct_ValidProduct_ReturnsUpdatedProduct() {
        int productId = 1;
        Product productToUpdate = new Product(productId, "Product 1", 10.0, 5);
        Product expectedUpdatedProduct = new Product(productId, "Updated Product", 15.0, 7);
        when(productRepository.findById(productId)).thenReturn(Optional.of(productToUpdate));
        when(productRepository.save(productToUpdate)).thenReturn(expectedUpdatedProduct);

        Product actualUpdatedProduct = productService.updateProduct(productId, productToUpdate);

        assertEquals(expectedUpdatedProduct, actualUpdatedProduct);
    }

    @Test
    void updateProduct_InvalidId_ThrowsRuntimeException() {
        int productId = 1;
        Product productToUpdate = new Product(productId, "Product 1", 10.0, 5);
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> productService.updateProduct(productId, productToUpdate));
    }

    @Test
    @DisplayName("Delete product by ID")
    void deleteProduct_ValidId_DeletesProduct() {
        int productId = 1;
        Product product = new Product(productId, "Product 1", 10.0, 5);
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        productService.deleteProduct(productId);

        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, times(1)).delete(product);
    }
}
