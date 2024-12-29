package com.example.jdbc.Employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;
    private String employeeName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    private String address;
    private String phoneNo;
    private String email;

}
