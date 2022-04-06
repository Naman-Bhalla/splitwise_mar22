package com.scaler.splitwise_mar22.repositories;

import com.scaler.splitwise_mar22.models.Group;
import com.scaler.splitwise_mar22.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {

    Optional<Group> findById(Long id);

//    List<Group> findAllByMembersContaining(List<User> users);
}
