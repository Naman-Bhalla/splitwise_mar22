package com.scaler.splitwise_mar22.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseModel {
  private String name;
  private String phoneNumber;
  private String hashedPassword;
}

// User -> user
// Group -> group
// Expense -> expense
