package com.example.service;

import com.example.dto.PythonLogRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class PythonCodeService {


    public String downloadCode(PythonLogRequestDTO pythonLogRequestDTO) {

        String pythonCode = pythonLogRequestDTO.getPythonCode();
        String id = pythonLogRequestDTO.getId();
        String errorMessage = pythonLogRequestDTO.getErrorMessage();


        if (errorMessage != null) {
            return String.format("Code with id %s and code %s has not been downloaded because of error: %s", id, pythonCode,errorMessage);
        }


        return String.format("Code with id %s and code %s has been downloaded", id, pythonCode);

    }
}
