package com.scaler.splitwise_mar22.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class GroupExpense extends BaseModel {
    @ManyToOne
    private Group group;

    @OneToOne
    private Expense expense;
}

// GroupExpense : Group
//     1           1
//     M           1
// GroupExpense : Expense
//     1           1
//     1           1
//        group_expenses
//   group_id   |   expense_id
//     1        |     2
//     1        |     4