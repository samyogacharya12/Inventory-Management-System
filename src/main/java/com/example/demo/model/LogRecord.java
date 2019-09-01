package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class LogRecord {

    private int loginId;
    private long userId;
    private String userName;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date loginTime;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date logoutTime;

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }
}
