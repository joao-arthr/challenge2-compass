package br.com.compass.challenge2.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compass.challenge2.DTO.ProductDTO;
import br.com.compass.challenge2.model.Product;
import br.com.compass.challenge2.service.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<List<ProductDTO>> getAllProducts(){
		List<Product> products = productService.getAllProducts();
		List<ProductDTO> productDTOs = products.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
		return ResponseEntity.ok(productDTOs);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> updateProduct(
			@PathVariable Long id,
			@Valid @RequestBody ProductDTO productDTO) {
		Product product = convertToEntity(productDTO);
		Product updatedProduct = productService.updateProduct(id, product);
		return ResponseEntity.ok(convertToDTO(updatedProduct));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}

	private ProductDTO convertToDTO(Product product) {
		return new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getQuantity());
	}

	private Product convertToEntity(ProductDTO productDTO) {
		return new Product(productDTO.getId(), productDTO.getName(), productDTO.getPrice(), productDTO.getQuantity());
	}
}
