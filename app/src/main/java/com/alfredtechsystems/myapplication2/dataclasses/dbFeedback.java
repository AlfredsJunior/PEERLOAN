package com.alfredtechsystems.myapplication2.dataclasses;

/**
 * Created by ashbel on 26/9/2017.
 */

public class dbFeedback {
    private long fd_id;
    private String fd_details;
    private String fd_state;
    private String fd_date;
    private String fd_title;

    public dbFeedback(long fd_id, String fd_details, String fd_state, String fd_date,String fd_title) {
        this.fd_id = fd_id;
        this.fd_details = fd_details;
        this.fd_state = fd_state;
        this.fd_date = fd_date;
        this.fd_title = fd_title;
    }

    public dbFeedback(){}

    public long getFd_id() {
        return fd_id;
    }

    public void setFd_id(long fd_id) {
        this.fd_id = fd_id;
    }

    public String getFd_details() {
        return fd_details;
    }

    public void setFd_details(String fd_details) {
        this.fd_details = fd_details;
    }

    public String getFd_state() {
        return fd_state;
    }

    public void setFd_state(String fd_state) {
        this.fd_state = fd_state;
    }

    public String getFd_date() {
        return fd_date;
    }

    public void setFd_date(String fd_date) {
        this.fd_date = fd_date;
    }

    public String getFd_title() {
        return fd_title;
    }

    public void setFd_title(String fd_title) {
        this.fd_title = fd_title;
    }

    @Override
    public String toString() {
        return "dbFeedback{" +
                "fd_id=" + fd_id +
                ", fd_details='" + fd_details + '\'' +
                ", fd_state='" + fd_state + '\'' +
                ", fd_date='" + fd_date + '\'' +
                ", fd_title='" + fd_title + '\'' +
                '}';
    }
}
