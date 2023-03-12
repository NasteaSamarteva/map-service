package com.mapservice.controller;

import com.mapservice.dto.CustomerRegistrationRequest;
import com.mapservice.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public void customerRegistration(@RequestBody @Valid CustomerRegistrationRequest customerRegistrationRequest) {
        customerService.customerRegistration(customerRegistrationRequest);
    }

}
