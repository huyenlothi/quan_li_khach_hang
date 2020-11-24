package com.quanli.controller;

import com.quanli.model.Customer;
import com.quanli.model.CustomerForm;
import com.quanli.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private Environment environment;

    @GetMapping("/create-customer")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView= new ModelAndView("create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }
    @PostMapping("/create-customer")
    public ModelAndView saveCustomer(@ModelAttribute CustomerForm customerForm){
        Customer customer = new Customer(customerForm.getFirstName(), customerForm.getLastName(), customerForm.getAddress());
        MultipartFile file = customerForm.getImage();
        String image = file.getOriginalFilename();
        customer.setImage(image);
        String fileUpload = environment.getProperty("file_upload").toString();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + image));
        } catch (IOException e) {
            e.printStackTrace();
        }

        customerService.save(customer);
        return new ModelAndView("create", "customer", new Customer());
    }
    @GetMapping("/customer")
    public ModelAndView listCustomer(){
        List<Customer> customers = customerService.findAll();
        ModelAndView modelAndView= new ModelAndView("list");
        modelAndView.addObject("listCustomer", customers);
        return modelAndView;
    }
    @GetMapping("/customer/edit/{id}")
    public ModelAndView editForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("edit");
        Customer customer = customerService.findById(id);
//        CustomerForm customerForm = new CustomerForm(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getAddress(), null);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }
    @PostMapping("/customer/edit")
    public ModelAndView edit(@ModelAttribute CustomerForm customerForm){
        Customer customer = customerService.findById(customerForm.getId());
        customer.setFirstName(customerForm.getFirstName());
        customer.setLastName(customerForm.getLastName());
        MultipartFile file = customerForm.getImage();
        String image;
        if (file.isEmpty()){
            image = customer.getImage();
        }else {
            image = file.getOriginalFilename();
        }
        customer.setImage(image);
        String fileUpload = environment.getProperty("file_upload").toString();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + image));
        } catch (IOException e) {
            e.printStackTrace();
        }
        customerService.save(customer);
        ModelAndView modelAndView= new ModelAndView("edit");
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }
    @GetMapping("/customer/delete/{id}")
    public String delete(@PathVariable Long id,RedirectAttributes redirect) {
        redirect.addFlashAttribute("message", "Removed customer successfully!");
        customerService.remove(id);
        return "redirect:/customer";
    }




}
