package com.mapservice.service;

import com.mapservice.entity.Customer;
import com.mapservice.repository.CustomerRepository;
import com.mapservice.security.DefaultUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username);
        if (customer == null){
                throw new UsernameNotFoundException("User was not found");
        }
        DefaultUserDetails userDetails = new DefaultUserDetails(customer);
        return userDetails;
    }
}
