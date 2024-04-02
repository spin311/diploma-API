package com.example.service;

import com.example.dto.PythonLogRequestDTO;
import com.example.entity.Log;
import com.example.repository.LogEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PythonCodeService {

    private final LogEntityRepository logEntityRepository;

    @Autowired
    public PythonCodeService(LogEntityRepository logEntityRepository) {
        this.logEntityRepository = logEntityRepository;
    }


    public String downloadCode(PythonLogRequestDTO pythonLogRequestDTO) {

        String pythonCode = pythonLogRequestDTO.getPythonCode();
        String guid = pythonLogRequestDTO.getId();
        String errorMessage = pythonLogRequestDTO.getErrorMessage();
        Integer taskNumber = pythonLogRequestDTO.getTaskNumber();

        try {
            Log log = new Log();
            log.setCode(pythonCode);
            log.setErrorMessage(errorMessage);
            //set timestamp to current time
            log.setTimestamp(LocalDateTime.now());
            log.setTaskNumber(taskNumber);
            logEntityRepository.save(log);
        } catch (Exception e) {
            e.printStackTrace();
            return String.format("Failed to save task %d with id %s and code %s: %s", taskNumber, guid, pythonCode, e.getMessage());
        }

            return String.format("Task %d with id %s, code %s and error message %s has been downloaded", taskNumber, guid, pythonCode,errorMessage);


    }
}
