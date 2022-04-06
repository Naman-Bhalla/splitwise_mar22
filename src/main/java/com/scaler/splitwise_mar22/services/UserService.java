package com.scaler.splitwise_mar22.services;

import com.scaler.splitwise_mar22.models.Expense;
import com.scaler.splitwise_mar22.models.Transaction;
import com.scaler.splitwise_mar22.models.User;
import com.scaler.splitwise_mar22.repositories.ExpenseRepository;
import com.scaler.splitwise_mar22.repositories.UserRepository;
import com.scaler.splitwise_mar22.services.passwordhashing.PasswordHashingStrategy;
import com.scaler.splitwise_mar22.strategies.settleup.SettleUpExpensesStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// if multiple classes implenting an interface:
// via configuration: Which data type to use
@Service
public class UserService {

    @Autowired
    private PasswordHashingStrategy passwordHashingStrategy;

//    @Qualifier("userRepoHelo")
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private SettleUpExpensesStrategy settleUpExpensesStrategy;

    public User registerUser(String name,
                             String password,
                             String phoneNumber) {

        // hash the password
        String hashedPassword = passwordHashingStrategy.hash(password);

        // create the user object -> no ID
        User user = new User();
        user.setName(name);
        user.setHashedPassword(hashedPassword);
        user.setPhoneNumber(phoneNumber);

        // save the user object ->  ID exists
        User savedUser = userRepository.save(user);

        // return the saved object
        return savedUser;
    }

    public List<Transaction> settleUp(Long userId) {
        // 1. Get all the expenses that the user is a part of
        // 2. Run the algo
        // 3. Filter the transactions where the from or to
        //    is the given user.

        User user = userRepository.getUserById(userId).get();

        List<Expense> expenses = expenseRepository
                .findAllByPaidByContainingOrOwedByContaining(userId, userId);
        List<Transaction> transactions = settleUpExpensesStrategy
                .settle(expenses);

        List<Transaction> filteredTransactions = new ArrayList<>();

        for (Transaction transaction: transactions) {
            if (transaction.getFrom().getPhoneNumber().equals(user.getPhoneNumber()) ||
            transaction.getTo().getPhoneNumber().equals(user.getPhoneNumber())) {
                filteredTransactions.add(transaction);
            }
        }

        return filteredTransactions;
    }
}

// don't tightly couple a service to a request
// same service can be used by multiple requests
// Back at 10:12 PM
// - How to run the code: How to run a command line application in Spring Boot
// - Run and test the code
// - Code the greedy solution
