package com.example.jdbc.Products;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class JReportService {

    @Autowired
    private ProductsRepository repository;

    public void exportJasperReport(HttpServletResponse response,long id) throws JRException, IOException {

        Products products = repository.getReferenceById(id);
        List<Products> productsList= repository.findAll();
        //Get file and compile it
        File file = ResourceUtils.getFile("classpath:jrxml/dummy.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JREmptyDataSource dataSource = new JREmptyDataSource();
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", products.getId());
        parameters.put("name", products.getName());
        parameters.put("brand", products.getBrand());
        parameters.put("price", products.getPrice());
        parameters.put("productsList", productsList);
        System.out.println("-----list----- " + productsList);
        //Fill Jasper report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        //Export report
        JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());
    }
}
