package com.example.rabbitchat;

public class Message {
    private String  NAME;
    private String MSG;

    Message() {}

    Message(String  NAME, String MSG) {
        this.NAME = NAME;
        this.MSG = MSG;
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
}