package com.scaler.splitwise_mar22.repositories;

import com.scaler.splitwise_mar22.models.Group;
import com.scaler.splitwise_mar22.models.GroupExpense;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupExpenseRepository extends JpaRepository<GroupExpense, Long> {

    List<GroupExpense> findAllByGroup(Group group);

//    List<GroupExpense> findAllByGroup
}
