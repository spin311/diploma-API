package com.example.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOG_SEQ")
    @SequenceGenerator(name = "LOG_SEQ", sequenceName = "LOG_SEQ", allocationSize = 1)
    private Long log_id;
    @Column(length = 36, nullable = false)
    private Integer student_id;
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName="student_id", nullable = false)
    private Student student;

@Column(length = 2, nullable = false)
    private Integer taskNumber;
@Column(length = 8192, nullable = false)
    private String code;
@Column
    private String errorMessage;
@Column(nullable = false)
    private LocalDateTime timestamp;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Long getLog_id() {
        return log_id;
    }

    public void setLog_id(Long log_id) {
        this.log_id = log_id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public Student getStudentEntity() {
        return student;
    }

    public void setStudentEntity(Student student) {
        this.student = student;
    }

    public Integer getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }


}
