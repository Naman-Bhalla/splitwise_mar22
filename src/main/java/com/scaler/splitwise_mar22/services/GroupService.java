package com.scaler.splitwise_mar22.services;

import com.scaler.splitwise_mar22.models.Expense;
import com.scaler.splitwise_mar22.models.Group;
import com.scaler.splitwise_mar22.models.GroupExpense;
import com.scaler.splitwise_mar22.models.Transaction;
import com.scaler.splitwise_mar22.repositories.GroupExpenseRepository;
import com.scaler.splitwise_mar22.repositories.GroupRepository;
import com.scaler.splitwise_mar22.strategies.settleup.SettleUpExpensesStrategy;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupExpenseRepository groupExpenseRepository;

    @Autowired
    private SettleUpExpensesStrategy settleUpExpensesStrategy;

    @Autowired
    private GroupRepository groupRepository;

    public List<Transaction> settleUp(Long groupId) {
        // 1. Get all the expenses of the group
        // 2. Call the algo that takes a list of
        //    expenses and returns a list of
        //    transactions to settle up those expenses.

        Group group = groupRepository.findById(groupId).get();

        List<GroupExpense> groupExpenses = groupExpenseRepository
                .findAllByGroup(group);

        List<Expense> expenses = new ArrayList<>();

        for (GroupExpense groupExpense: groupExpenses) {
            expenses.add(groupExpense.getExpense());
        }

        return settleUpExpensesStrategy.settle(expenses);
    }
}
