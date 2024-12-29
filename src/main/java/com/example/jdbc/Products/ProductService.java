package com.example.jdbc.Products;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService{

    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private HttpServletResponse response;



}
