package com.example.personaldetails.activity;

/*
 *@Auther:老刘
 *@Date: 时间
 *@Description:功能
 * */
public class WalletEntity {
    /**
     * result : 4989500
     * message : 查询成功
     * status : 0000
     */

    private int result;
    private String message;
    private String status;

    @Override
    public String toString() {
        return "WalletEntity{" +
                "result=" + result +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

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
}
