package com.example.messager.model;

public class Message {
    private String message;
    public boolean isFromMe;

    public Message(String message, boolean isFromMe) {
        this.message = message;
        this.isFromMe = isFromMe;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isFromMe() {
        return isFromMe;
    }

    public void setFromMe(boolean fromMe) {
        isFromMe = fromMe;
    }
}
