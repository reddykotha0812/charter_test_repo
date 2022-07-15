package com.test.assesment.services.transaction;

import com.test.assesment.entity.CustomerTransaction;
import com.test.assesment.repository.CustomerTransactionRepository;
import com.test.assesment.util.MonthlyUserRewardReport;
import com.test.assesment.util.RewardUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerTransactionImpl implements TransactionService {

    @Autowired
    CustomerTransactionRepository customerTransactionRepository;

    @Override
    public List<CustomerTransaction> findAllCustomerTransaction() {
        return customerTransactionRepository.findAll();
    }



    @Override
    public CustomerTransaction saveCustomerTransaction(CustomerTransaction transaction) {
        if (transaction.getTransactionDate() == null) {
            transaction.setTransactionDate(LocalDateTime.now());
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            LocalDateTime transactionDate = LocalDateTime.parse(String.valueOf(transaction.getTransactionDate()).replace("T", " "), formatter);
            transaction.setTransactionDate(transactionDate);
        }
        transaction.setRewardPerTransaction(RewardUtils.calculateRewardPoint(transaction.getAmount()));
        return customerTransactionRepository.save(transaction);
    }

    @Override
    public Map<String, Long> getTotalRewardPointForCustomer(Long id) {
        Map<String, Long> totalReward = new HashMap<>();
        totalReward.put("customerId", id);
        totalReward.put("totalReward", customerTransactionRepository.getTotalRewardPointForCustomer(id));

        return totalReward;
    }


    public List<Object> getRewardPointPerMonth(Long id) throws SQLException {
        return customerTransactionRepository.getMonthlyUserRewards(id);

    }
}
