package com.example.repository;

import com.example.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogEntityRepository extends JpaRepository<Log, Long>{
}
