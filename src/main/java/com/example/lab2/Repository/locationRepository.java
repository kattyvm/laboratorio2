package com.example.lab2.Repository;

import com.example.lab2.Entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface locationRepository extends JpaRepository<Location,Integer> {
}
