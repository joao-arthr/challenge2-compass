package br.com.compass.challenge2.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public class ProductDTO {

	private int id;
	
	@NotEmpty(message = "Name is required")
	private String name;
	
	@Positive(message = "Price must be a positive number")
	private double price;

	@NotEmpty(message = "Quantity is required")
	private int quantity;

	public ProductDTO(int id, String name, double price, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
