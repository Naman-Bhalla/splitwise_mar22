package com.scaler.splitwise_mar22.strategies.settleup;

import com.scaler.splitwise_mar22.models.Expense;
import com.scaler.splitwise_mar22.models.Transaction;
import com.scaler.splitwise_mar22.models.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GreedySettleUpExpenseStrategy implements SettleUpExpensesStrategy {

    class Record {
        User user;
        int pendingAmount;

        Record(User user, int pendingAmount) {
            this.user = user;
            this.pendingAmount = pendingAmount;
        }
    }

    @Override
    public List<Transaction> settle(List<Expense> expenses) {
        Map<User, Integer> extraMoney = new HashMap<>();

        for (Expense expense: expenses) {
            for (User user: expense.getPaidBy().keySet()) {
                if (!extraMoney.containsKey(user)) {
                    extraMoney.put(user, 0);
                }

                extraMoney.put(user, extraMoney.get(user) + expense.getPaidBy().get(user));
            }

            for (User user: expense.getPaidBy().keySet()) {
                if (!extraMoney.containsKey(user)) {
                    extraMoney.put(user, 0);
                }

                extraMoney.put(user, extraMoney.get(user) - expense.getPaidBy().get(user));
            }
        }


        Queue<Record> negativeQueue = new ArrayDeque<>();
        Queue<Record> positiveQueue = new ArrayDeque<>();

        for (User user: extraMoney.keySet()) {
            if (extraMoney.get(user) < 0) {
                negativeQueue.add(new Record(user, extraMoney.get(user)));
            } else if (extraMoney.get(user) > 0) {
                positiveQueue.add(new Record(user, extraMoney.get(user)));
            }
        }

        List<Transaction> transactions = new ArrayList<>();

        // A: 20
        // B: -80
        while (!positiveQueue.isEmpty() && !negativeQueue.isEmpty()) {
            Record firstNegative = negativeQueue.remove();
            Record firstPostive = positiveQueue.remove();

            if (firstPostive.pendingAmount > Math.abs(firstNegative.pendingAmount)) {
                transactions.add(
                        new Transaction(firstNegative.user.toDto(), firstPostive.user.toDto(), Math.abs(firstNegative.pendingAmount))
                );
                positiveQueue.add(new Record(firstPostive.user, firstPostive.pendingAmount - Math.abs(firstNegative.pendingAmount)));
            } else {
                transactions.add(
                        new Transaction(firstNegative.user.toDto(), firstPostive.user.toDto(), firstPostive.pendingAmount)
                );
                negativeQueue.add(new Record(firstNegative.user, firstNegative.pendingAmount + firstPostive.pendingAmount));
            }
        }

        return transactions;
    }
}

// 1. Code

// expense 1
// paidBy: 1 - 50   2 - 100
// owedBy: 1 - 25   2 - 25  3 - 25 4 - 75

// expense 2
// paidBy: 2 - 50   2 - 10
// owedBy: 1 - 15   2 - 15  3 - 15 4 - 15
