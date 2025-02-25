package com.atus.api.service;

import com.atus.api.entity.CustomerEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public interface CustomerService {
    void deleteCustomerById(String customerId);
    Iterable<CustomerEntity> getAllCustomers();
    Optional<CustomerEntity> getCustomerById(String customerId);
}
