package com.example.controller;

import com.example.service.PythonCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/python")
public class PythonCodeController {

    private PythonCodeService pythonCodeService;

    @Autowired
    public PythonCodeController(PythonCodeService pythonCodeService) {
        this.pythonCodeService = pythonCodeService;
    }

    @GetMapping("/log")
    public ResponseEntity<String> log(@RequestBody String id, String pythonCode) {
        try {
            String result = pythonCodeService.downloadCode(id, pythonCode);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to log Python code: " + e.getMessage());

        }
        //call PythonCodeService with code


    }
}
