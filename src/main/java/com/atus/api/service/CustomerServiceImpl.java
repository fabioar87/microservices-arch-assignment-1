package com.atus.api.service;

import com.atus.api.entity.CustomerEntity;
import com.atus.api.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void deleteCustomerById(String customerId) {
        customerRepository.deleteById(UUID.fromString(customerId));
    }

    @Override
    public Iterable<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<CustomerEntity> getCustomerById(String customerId) {
        return customerRepository.findById(UUID.fromString(customerId));
    }
}
