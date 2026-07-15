package com.demo.spring_shift_left_demo.repository;


import com.demo.spring_shift_left_demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
