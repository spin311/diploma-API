package com.example.repository;

import com.example.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class LogEntityRepository implements JpaRepository<LogEntity, Long>{
}
