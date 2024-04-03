package com.example.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
@Entity
public class Submit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBMIT_SEQ")
    @SequenceGenerator(name = "SUBMIT_SEQ", sequenceName = "SUBMIT_SEQ", allocationSize = 1)
    private Long submit_id;
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    @Column(nullable = false)
    private LocalDateTime timestamp;

    public Long getSubmit_id() {
        return submit_id;
    }

    public void setSubmit_id(Long submit_id) {
        this.submit_id = submit_id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
