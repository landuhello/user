package com.example.personaldetails.activity.bean;

/*
 *@Auther:
 *@Date:
 *@Description:
 * */
public class MessageEntity {
    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
