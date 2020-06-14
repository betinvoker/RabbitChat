package com.example.rabbitchat;

public class Message {

    private int ID;
    private String MSG;

    Message() {}

    Message(int ID, String MSG) {
        this.ID = ID;
        this.MSG = MSG;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String messageMSG) {
        this.MSG = messageMSG;
    }
}
