package com.example.repository;

import com.example.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogEntityRepository extends JpaRepository<LogEntity, Long>{
}
