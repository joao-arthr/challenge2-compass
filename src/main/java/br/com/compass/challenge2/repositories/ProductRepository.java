package br.com.compass.challenge2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compass.challenge2.model.Product;

public interface ProductRepository extends JpaRepository<Product, int> {

}
