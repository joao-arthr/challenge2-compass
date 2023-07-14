package br.com.compass.challenge2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.compass.challenge2.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}