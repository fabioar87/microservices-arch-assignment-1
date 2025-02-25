package com.atus.api.controllers;

import com.atus.api.CustomerApi;
import com.atus.api.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class CustomerController implements CustomerApi {
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Override
    public ResponseEntity<Customer> getCustomerById(String id) throws Exception {
        log.info("Request for customer id {}", id);
        throw new RuntimeException("Customer not found");
    }
}
