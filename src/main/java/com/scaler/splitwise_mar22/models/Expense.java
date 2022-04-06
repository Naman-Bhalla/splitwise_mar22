package com.scaler.splitwise_mar22.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

// created_by
// Expense : User
//    1    :  1
//    M     :   1
@Getter
@Setter
@Entity
public class Expense extends BaseModel {
    private int amount;
    // curent_class TO attribute_class
    // expense TO user
    //   M     TO  1
    @ManyToOne // Hibernate ORM
    private User createdBy;

    private String description;

    @ElementCollection
    private Map<User, Integer> paidBy;

    @ElementCollection
    private Map<User, Integer> owedBy;
}


// A -> as
// User -> users
// Game -> games

// Project Building - 1 class on ORM