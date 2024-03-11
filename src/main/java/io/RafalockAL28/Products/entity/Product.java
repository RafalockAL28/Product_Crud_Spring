package io.RafalockAL28.Products.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name = "produtos")
@Data //Adicionando getters e setters
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "tipo")
    private  String type;
    @Column(name = "preco")
    private Double price;
    @Column(name = "quantidade")
    private  int qtde;

    public Product() {
    }

    @Builder
    public Product(String name, String type, Double price, int qtde) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.qtde = qtde;
    }
}
