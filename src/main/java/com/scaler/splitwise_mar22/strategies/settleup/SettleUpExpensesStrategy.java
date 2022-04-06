package com.scaler.splitwise_mar22.strategies.settleup;

import com.scaler.splitwise_mar22.models.Expense;
import com.scaler.splitwise_mar22.models.Transaction;

import java.util.List;

public interface SettleUpExpensesStrategy {
    List<Transaction> settle(List<Expense> expenses);
}
