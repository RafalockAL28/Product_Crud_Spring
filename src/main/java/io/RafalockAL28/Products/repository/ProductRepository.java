package io.RafalockAL28.Products.repository;


import io.RafalockAL28.Products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Respomavel pela implementacçao
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


}
