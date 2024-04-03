package com.example.dto;

import java.util.List;

public class SubmitDTO {
    private String id;

    private List<ChatDTO> chatDTOList;

    private List<PythonLogRequestDTO> pythonLogRequestDTOList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ChatDTO> getChatDTOList() {
        return chatDTOList;
    }

    public void setChatDTOList(List<ChatDTO> chatDTOList) {
        this.chatDTOList = chatDTOList;
    }

    public List<PythonLogRequestDTO> getPythonLogRequestDTOList() {
        return pythonLogRequestDTOList;
    }

    public void setPythonLogRequestDTOList(List<PythonLogRequestDTO> pythonLogRequestDTOList) {
        this.pythonLogRequestDTOList = pythonLogRequestDTOList;
    }


}
