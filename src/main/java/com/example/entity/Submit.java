package com.example.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
@Entity
public class Submit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBMIT_ENTITY_SEQ")
    @SequenceGenerator(name = "SUBMIT_ENTITY_SEQ", sequenceName = "SUBMIT_ENTITY_SEQ", allocationSize = 1)
    private Long submit_id;
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName="student_id", nullable = false)
    private Student student;

    @Column(length = 36, nullable = false)
    private Integer student_id;
    @OneToMany(cascade = CascadeType.ALL)
private List<Chat> chatEntities;
    @Column(nullable = false)
    private LocalDateTime timestamp;

    public Long getSubmit_id() {
        return submit_id;
    }

    public void setSubmit_id(Long submit_id) {
        this.submit_id = submit_id;
    }

    public List<Chat> getChatEntities() {
        return chatEntities;
    }

    public void setChatEntities(List<Chat> chatEntities) {
        this.chatEntities = chatEntities;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Student getStudentEntity() {
        return student;
    }

    public void setStudentEntity(Student student) {
        this.student = student;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }
}
