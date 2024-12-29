package com.example.jdbc.Products;

import com.example.jdbc.Employee.Employee;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne
    private Employee employee;
    @NotEmpty(message = "The name is required")
    private String name;
    @NotEmpty(message = "The brand is required")
    private String brand;
    @NotEmpty(message = "The name is required")
    private String category;
    private double price;

    @Column(columnDefinition = "TEXT")
    private String description;
    @DateTimeFormat(pattern="dd-MMM-YYYY")
    private Date createdDate;


}
