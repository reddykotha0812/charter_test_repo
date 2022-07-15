package com.test.assesment.controller;


import com.test.assesment.entity.Customer;
import com.test.assesment.services.customer.CustomerService;
import com.test.assesment.services.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    TransactionService transactionService;

    @GetMapping
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        System.out.print(customer.toString());
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/totalrewards/{id}")
    public Map<String, Long> totalRewardsForCustomer(@PathVariable("id") Long id) {
        return transactionService.getTotalRewardPointForCustomer(id);
    }

    @GetMapping("/monthly-reward/{id}")
    public List<Object> getMonthlyUserReward(@PathVariable("id") Long id) throws SQLException {
        return transactionService.getRewardPointPerMonth(id);
    }
}
