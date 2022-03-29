package com.scaler.splitwise_mar22.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "groups")
public class Group extends BaseModel {
    // GROUP : USER
    //   1       M
    //   M       1
    //    M to M
    @ManyToMany
    private List<User> admins;

    @ManyToMany
    private List<User> members;

    private String name;
}
