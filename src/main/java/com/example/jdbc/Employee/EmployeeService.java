package com.example.jdbc.Employee;

import com.example.jdbc.Products.Products;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    public void saveAll(Employee employee){
        employeeRepository.save(employee);
    }

    public Employee getById(Long id){
        Optional<Employee> optionalEmployee= employeeRepository.findById(id);
        Employee employee = null;

        if(optionalEmployee.isPresent()){
            employee=optionalEmployee.get();
        }else
            throw new RuntimeException (
            "Employee not found for id : " + id);

        return employee;
    }

    public void deleteId(long id){
        employeeRepository.deleteById(id);
    }

    public void printReport(HttpServletResponse response) throws JRException, IOException {

        List<Employee> employeeList= employeeRepository.findAll();

        //Get file and compile it
        File file = ResourceUtils.getFile("classpath:jrxml/Cherry.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JREmptyDataSource dataSource = new JREmptyDataSource();
        JRBeanCollectionDataSource list = new JRBeanCollectionDataSource(employeeList);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("employeeList", list);
        System.out.println("-----list----- " + employeeList);
//        System.out.println("-----list 2----- " + list);
        //Fill Jasper report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,dataSource);
        //Export report
        JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());
    }

}
