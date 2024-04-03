package com.example.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDENT_SEQ")
    @SequenceGenerator(name = "STUDENT_SEQ", sequenceName = "STUDENT_SEQ", allocationSize = 1)
    private Long student_id;

    @Column(length = 36, nullable = false, unique = true)
    private String guid;

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
}
