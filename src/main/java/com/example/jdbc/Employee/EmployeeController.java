package com.example.jdbc.Employee;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import org.springframework.boot.Banner;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/list")
    public String viewHomePage(Model model) {
        model.addAttribute("allemplist",employeeService.getAllEmployee());
        return "employee/index";
    }
    @GetMapping(value = "/addnew")
    public String saveEmployee(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "employee/addEmployee";
    }
    @PostMapping(value = "/saveEmployee")
    public String saveEmployee(@ModelAttribute ("employee") Employee employee, HttpSession session){
        employeeService.saveAll(employee);
        session.setAttribute("message","da");
        return "redirect:/employees/list";
    }
    @GetMapping(value = "/update/{id}")
    public String update(@PathVariable(value = "id") long id, Model model){
        Employee employee = employeeService.getById(id);
        model.addAttribute("employee", employee);
        return "employee/updateEmployee";
    }
    @GetMapping("/deleteEmployee/{id}")
    public String deleteId(@PathVariable(value = "id") long id) {
        employeeService.deleteId(id);
        return "redirect:/list";

    }
    @GetMapping(value = "/view/{id}")
    public String view(@PathVariable(value = "id") long id, Model model){
        Employee employee = employeeService.getById(id);
        model.addAttribute("employee", employee);
        return "employee/view";
    }
    @GetMapping("/printReport")
    public void printReport(HttpServletResponse response) throws IOException, JRException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        employeeService.printReport(response);
    }
}