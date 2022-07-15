package com.test.assesment.controller;

import com.test.assesment.entity.CustomerTransaction;
import com.test.assesment.services.customer.CustomerServiceImpl;
import com.test.assesment.services.transaction.CustomerTransactionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer-transactions")
public class CustomerTransactionController {
    @Autowired
    CustomerTransactionImpl customerTransactionImpl;

    @Autowired
    CustomerServiceImpl customerService;

    @GetMapping
    public List<CustomerTransaction> getAllTransaction() {
        return customerTransactionImpl.findAllCustomerTransaction();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE )
    public CustomerTransaction saveTransaction(@RequestBody CustomerTransaction transaction) {
        transaction.setCustomer(customerService.getCustomerById(transaction.getCustomer().getId()));
        return customerTransactionImpl.saveCustomerTransaction(transaction);
    }

}
