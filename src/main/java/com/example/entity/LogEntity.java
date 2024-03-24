package com.example.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class LogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOG_ENTITY_SEQ")
    @SequenceGenerator(name = "LOG_ENTITY_SEQ", sequenceName = "LOG_ENTITY_SEQ", allocationSize = 1)
    private Long id;
@Column(length = 36, nullable = false)
    private String guid;
@Column(length = 2, nullable = false)
    private Integer taskNumber;
@Column(length = 4096, nullable = false)
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

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Integer getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }
}
