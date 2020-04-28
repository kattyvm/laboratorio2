package com.example.lab2.Repository;

import com.example.lab2.Entity.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface departmentsRepository extends JpaRepository<Departments,Integer> {
}
