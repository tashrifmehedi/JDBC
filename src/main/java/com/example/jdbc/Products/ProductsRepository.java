package com.example.jdbc.Products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products,Long> {

}
