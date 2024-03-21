package com.example.service;

import com.example.dto.PythonLogRequestDTO;
import com.example.entity.LogEntity;
import com.example.repository.LogEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        try {
            LogEntity logEntity = new LogEntity();
            logEntity.setCode(pythonCode);
            logEntity.setGuid(guid);
            logEntity.setErrorMessage(errorMessage);
            //set timestamp to current time
            logEntity.setTimestamp(LocalDateTime.now());
            logEntityRepository.save(logEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return String.format("Failed to save code with id %s and code %s: %s", guid, pythonCode, e.getMessage());
        }

            return String.format("Code with id %s, code %s and error message %s has been downloaded", guid, pythonCode,errorMessage);


    }
}
