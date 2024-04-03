package com.example.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHAT_SEQ")
    @SequenceGenerator(name = "CHAT_SEQ", sequenceName = "CHAT_SEQ", allocationSize = 1)
    private Long chat_id;

    @ManyToOne
    @JoinColumn(name = "submit_id", nullable = false)
    private Submit submit;
    @Column(length = 4, nullable = false)
    private Integer chatNumber;
    @Column(length = 2, nullable = false)
    private Integer codeNumber;
    @Column(length = 8192, nullable = false)
    private String chatQuestion;
    @Column(length = 8192, nullable = false)
    private String chatAnswer;
    @Column(nullable = false)
    private LocalDateTime timestamp;

    public Integer getChatNumber() {
        return chatNumber;
    }

    public void setChatNumber(Integer chatNumber) {
        this.chatNumber = chatNumber;
    }

    public Integer getCodeNumber() {
        return codeNumber;
    }

    public void setCodeNumber(Integer codeNumber) {
        this.codeNumber = codeNumber;
    }

    public String getChatQuestion() {
        return chatQuestion;
    }

    public void setChatQuestion(String chatQuestion) {
        this.chatQuestion = chatQuestion;
    }

    public String getChatAnswer() {
        return chatAnswer;
    }

    public void setChatAnswer(String chatAnswer) {
        this.chatAnswer = chatAnswer;
    }

    public Long getChat_id() {
        return chat_id;
    }

    public void setChat_id(Long chat_id) {
        this.chat_id = chat_id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Submit getSubmit() {
        return submit;
    }

    public void setSubmit(Submit submit) {
        this.submit = submit;
    }
}
