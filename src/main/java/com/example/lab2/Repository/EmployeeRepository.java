package com.example.lab2.Repository;

import com.example.lab2.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {

    List<EmployeeEntity> findByFirstnameOrLastname(String nombre);

}
