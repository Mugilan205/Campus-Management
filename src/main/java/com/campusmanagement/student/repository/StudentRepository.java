package com.campusmanagement.student.repository;

import com.campusmanagement.student.entity.Student;
import com.campusmanagement.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository
        extends JpaRepository<Student, Long> {

    Optional<Student> findByUser(User user);

    Optional<Student> findByRollNumber(String rollNumber);

    boolean existsByRollNumber(String rollNumber);
}