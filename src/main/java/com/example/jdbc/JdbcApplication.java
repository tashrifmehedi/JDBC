package com.example.jdbc;

import com.example.jdbc.Products.Products;
import net.sf.jasperreports.engine.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class JdbcApplication {

    public static void main(String[] args) throws JRException {

        SpringApplication.run(JdbcApplication.class, args);

    }
}
