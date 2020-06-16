package com.example.rabbitchat;

public class Message {
    private String  NAME;
    private String MSG;
    private String TIME;

    Message() {}

    Message(String  NAME, String MSG, String TIME) {
        this.NAME = NAME;
        this.MSG = MSG;
        this.TIME = TIME;
    }

    public String  getNAME() {
        return NAME;
    }

    public void setID(String  messageNAME) {
        this.NAME = messageNAME;
    }

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String messageMSG) {
        this.MSG = messageMSG;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String messageTIME) {
        this.TIME = messageTIME;
    }
}