package com.test.assesment.services.transaction;

import com.test.assesment.entity.CustomerTransaction;
import com.test.assesment.util.MonthlyUserRewardReport;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface TransactionService {
    List<CustomerTransaction> findAllCustomerTransaction();

    CustomerTransaction saveCustomerTransaction(CustomerTransaction transaction);


    Map<String, Long> getTotalRewardPointForCustomer(Long id);
    List<Object>getRewardPointPerMonth(Long id) throws SQLException;
}
