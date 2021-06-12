package com.practicaltask.customer;

import com.practicaltask.config.CommonResponse;
import com.practicaltask.config.ResponseUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public CommonResponse getAllCustomers() throws URISyntaxException, IOException, InterruptedException {
        CommonResponse response = customerService.saveAllCustomers();
        return ResponseUtilities.createSuccessResponse(response);
    }

}