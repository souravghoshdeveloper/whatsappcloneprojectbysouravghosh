package com.souravghosh.whatsapp.Modeles;

public class MassagesModel {
    String uId, message, messageId;
    Long timetamp;

    public MassagesModel(String uId, String message, Long timetamp) {
        this.uId = uId;
        this.message = message;
        this.timetamp = timetamp;
    }

    public MassagesModel(String uId, String message) {
        this.uId = uId;
        this.message = message;
    }
    public MassagesModel(){}

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimetamp() {
        return timetamp;
    }

    public void setTimetamp(Long timetamp) {
        this.timetamp = timetamp;
    }
}
