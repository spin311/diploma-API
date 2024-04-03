package com.example.service;

import com.example.dto.ChatDTO;
import com.example.dto.PythonLogRequestDTO;
import com.example.dto.SubmitDTO;
import com.example.entity.Chat;
import com.example.entity.Log;
import com.example.entity.Student;
import com.example.entity.Submit;
import com.example.repository.ChatRepository;
import com.example.repository.LogRepository;
import com.example.repository.StudentRepository;
import com.example.repository.SubmitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PythonCodeService {

    private final LogRepository logRepository;
    private final StudentRepository studentRepository;
    private final ChatRepository chatRepository;
    private final SubmitRepository submitRepository;

    @Autowired
    public PythonCodeService(LogRepository logRepository, StudentRepository studentRepository, ChatRepository chatRepository, SubmitRepository submitRepository) {
        this.logRepository = logRepository;
        this.studentRepository = studentRepository;
        this.chatRepository = chatRepository;
        this.submitRepository = submitRepository;
    }


    public String downloadCode(PythonLogRequestDTO pythonLogRequestDTO) {

        String pythonCode = pythonLogRequestDTO.getPythonCode();
        String guid = pythonLogRequestDTO.getId();
        String errorMessage = pythonLogRequestDTO.getErrorMessage();
        Integer taskNumber = pythonLogRequestDTO.getTaskNumber();

        try {
            Student student = studentRepository.findByGuid(guid);
            saveLog(pythonLogRequestDTO, student, false);
        } catch (Exception e) {
            e.printStackTrace();
            return String.format("Failed to save task %d with id %s and code %s: %s", taskNumber, guid, pythonCode, e.getMessage());
        }

            return String.format("Task %d with id %s, code %s and error message %s has been downloaded", taskNumber, guid, pythonCode,errorMessage);


    }

    public String submitCode(SubmitDTO submitDTO) {
        String guid = submitDTO.getId();
        List<ChatDTO> chatList = submitDTO.getChatDTOList();
        List<PythonLogRequestDTO> pythonLogRequestList = submitDTO.getPythonLogRequestDTOList();
        try {
            Student student = studentRepository.findByGuid(guid);
            saveSubmit(student, pythonLogRequestList, chatList);
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to submit code: " + e.getMessage();
        }
        return "Code submitted";
    }

    private void saveSubmit(Student student, List<PythonLogRequestDTO> pythonLogRequestList, List<ChatDTO> chatList) {
        Submit submit = new Submit();
        submit.setStudent(student);
        for (PythonLogRequestDTO pythonLogRequestDTO : pythonLogRequestList) {
            saveLog(pythonLogRequestDTO, student, true);
        }

        for (ChatDTO chatDTO : chatList) {
            saveChat(chatDTO, submit);
        }
        submit.setTimestamp(LocalDateTime.now());
        submitRepository.save(submit);
    }

    private void saveChat(ChatDTO chatDTO, Submit submit) {
        Chat chat = new Chat();
        chat.setSubmit(submit);
        chat.setChatQuestion(chatDTO.getChatQuestion());
        chat.setChatAnswer(chatDTO.getChatAnswer());
        chat.setChatNumber(chatDTO.getChatNumber());
        chat.setCodeNumber(chatDTO.getCodeNumber());
        chat.setTimestamp(chatDTO.getTimestamp());
        chatRepository.save(chat);
    }

    private void saveLog(PythonLogRequestDTO pythonLogRequestDTO, Student student, Boolean submitted) {
        Log log = new Log();
        log.setCode(pythonLogRequestDTO.getPythonCode());
        log.setErrorMessage(pythonLogRequestDTO.getErrorMessage());
        log.setTaskNumber(pythonLogRequestDTO.getTaskNumber());
        log.setSubmitted(submitted);
        log.setTimestamp(LocalDateTime.now());
        log.setStudent(student);
        logRepository.save(log);
    }
}
