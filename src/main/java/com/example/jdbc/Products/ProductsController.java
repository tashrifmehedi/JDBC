package com.example.jdbc.Products;

import com.example.jdbc.Employee.Employee;
import com.example.jdbc.Employee.EmployeeRepository;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/products")
public class ProductsController {

    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private JReportService service;
    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value = "/showMainPage", method = RequestMethod.GET)
    public String mainPage(Model model){
        List<Products> products = productsRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        model.addAttribute("products", products);
        return "/products/main";
    }
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showList(Model model){
        List<Products> products = productsRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
        model.addAttribute("products", products);
        return "products/productsList";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String addProductsItem(Model model){
        Products products = new Products();
        model.addAttribute("products",products);
        return "products/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String addProductsItem(@ModelAttribute Products products, BindingResult result){

        Products products1 = new Products();
        products1.setName(products.getName());
        products1.setBrand(products.getBrand());
        products1.setCategory(products.getCategory());
        products1.setPrice(products.getPrice());
        products1.setDescription(products.getDescription());
        products1.setCreatedDate(new Date());
        products1.setEmployee(products.getEmployee());
        productsRepository.save(products1);

        return "redirect:/products/list";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editProductItem(Model model, @RequestParam long id){
        try {
            Products products = productsRepository.findById(id).get();

            Products products1 = new Products();
            products1.setName(products.getName());
            products1.setBrand(products.getBrand());
            products1.setCategory(products.getCategory());
            products1.setPrice(products.getPrice());
            products1.setDescription(products.getDescription() );
            model.addAttribute("products",products);

        }catch (Exception e){
            System.out.println("Exception : "+ e.getMessage());
            return "redirect:/products/list";
        }
        return "products/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String updateProduct(Model model,@ModelAttribute Products products,@RequestParam long id,BindingResult result){

        try {
            if(result.hasErrors()){
                return "products/edit";
            }

            Products productss = productsRepository.findById(id).get();
            productss.setName(products.getName());
            productss.setBrand(products.getBrand());
            productss.setCategory(products.getCategory());
            productss.setPrice(products.getPrice());
            productss.setDescription(products.getDescription());
            productss.setCreatedDate(new Date());
            productsRepository.save(productss);
        }catch (Exception e){
            System.out.println("Exception : "+ e.getMessage());
        }
        return "redirect:/products/list";
    }

    @GetMapping(value = "/delete")
    public String deleteProduct(@RequestParam long id){

        try {
            Products products = productsRepository.findById(id).get();
            productsRepository.delete(products);
        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return "redirect:/products/list";
    }


    @GetMapping("/printReport")
    public void createPDF(HttpServletResponse response, long id) throws IOException, JRException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        service.exportJasperReport(response,id);
    }
}
