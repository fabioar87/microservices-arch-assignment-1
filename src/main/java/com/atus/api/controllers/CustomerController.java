package com.atus.api.controllers;

import com.atus.api.CustomerApi;
import com.atus.api.hateoas.CustomerRepresentationModelAssembler;
import com.atus.api.model.Customer;
import com.atus.api.service.CustomerService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CustomerController implements CustomerApi {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;
    private final CustomerRepresentationModelAssembler assembler;

    public CustomerController(CustomerService customerService, CustomerRepresentationModelAssembler assembler) {
        this.customerService = customerService;
        this.assembler = assembler;
    }

    @Override
    public ResponseEntity<Customer> getCustomerById(String customerId) throws Exception {
        log.info("Request for customer id {}", customerId);
        return customerService.getCustomerById(customerId).map(assembler::toModel)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<Customer>> getAllCustomers() throws Exception {
        log.info("Request for all customers");
        try{
            return ResponseEntity.ok(assembler.toListModel(customerService.getAllCustomers()));
        } catch (RuntimeException e){
            throw new RuntimeException("Exception while getting all customers", e);
        }
    }

    @Override
    public ResponseEntity<Void> deleteCustomerById(String customerId) throws Exception {
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.accepted().build();
    }
}
