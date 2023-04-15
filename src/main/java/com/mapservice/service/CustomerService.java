package com.mapservice.service;

import com.mapservice.dto.CustomerRegistrationRequest;
import com.mapservice.entity.Customer;
import com.mapservice.repository.CustomerRepository;
import com.mapservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void customerRegistration(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = new Customer();
        customer.setUsername(customerRegistrationRequest.getUsername());
        customer.setPassword(passwordEncoder.encode(customerRegistrationRequest.getPassword()));
        customer.setEmail(customerRegistrationRequest.getEmail());
        customer.setId(UUID.randomUUID());
        customer.setRole(roleRepository.findByName("USER"));
        customerRepository.save(customer);

    }
}
