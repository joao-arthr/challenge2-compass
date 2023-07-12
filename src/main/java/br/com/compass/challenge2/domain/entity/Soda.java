package br.com.compass.challenge2.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Soda")
public class Soda {
    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sodaId")
    private int id;

    @Column(name="soda_name")
    private String sodaName;

    @Column(name="price")
    private double price;

    @Column(name="quantity")
    private int quantity;

    //Constructors
    public Soda() {

    }

    public Soda(String sodaName, double price, int quantity) {
        this.sodaName = sodaName;
        this.price = price;
        this.quantity = quantity;
    }

    //Getters/Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSodaName() {
        return sodaName;
    }

    public void setSodaName(String sodaName) {
        this.sodaName = sodaName;
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

    //toString() method

    @Override
    public String toString() {
        return "Soda{" +
                "id=" + id +
                ", sodaName='" + sodaName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
