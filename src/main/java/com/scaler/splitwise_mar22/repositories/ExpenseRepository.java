package com.scaler.splitwise_mar22.repositories;

import com.scaler.splitwise_mar22.models.Expense;
import com.scaler.splitwise_mar22.models.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// HIbernate ORM
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findAllByPaidByContainingOrOwedByContaining(Long userId, Long userId2);
    // select * from expenses
    // where
    // paidBy contains userID
    // OR
    // owedBy contains userId
}
