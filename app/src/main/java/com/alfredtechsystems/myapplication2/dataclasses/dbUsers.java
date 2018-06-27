package com.alfredtechsystems.myapplication2.dataclasses;

/**
 * Created by ashbel on 3/8/2017.
 */

public class dbUsers {

    private long usrId;
    private String firstname;
    private String lastname;
    private String gender;
    private String idNum;
    private String phNum;
    private String pswd;
    private String app_id;
    private String sent;
    private String mambu_id;
    private String create_date;

    public dbUsers(long usrId, String firstname, String lastname, String gender, String phNum, String idNum, String pswd, String app_id, String sent,String mambu_id ,String create_date){
        this.usrId = usrId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.idNum = idNum;
        this.phNum = phNum;
        this.pswd = pswd;
        this.app_id = app_id;
        this.sent = sent;
        this.mambu_id = mambu_id;
        this.create_date = create_date;
    }

    public dbUsers(){

    }

    public long getUsrId() {
        return usrId;
    }

    public void setUsrId(long usrId) {
        this.usrId = usrId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getPhNum() {
        return phNum;
    }

    public void setPhNum(String phNum) {
        this.phNum = phNum;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getCreate_date() {
        return create_date;
    }

    public String getSent() {
        return sent;
    }

    public void setSent(String sent) {
        this.sent = sent;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getMambu_id() {
        return mambu_id;
    }

    public void setMambu_id(String mambu_id) {
        this.mambu_id = mambu_id;
    }

    @Override
    public String toString() {
        return "dbUsers{" +
                "usrId=" + usrId +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", gender='" + gender + '\'' +
                ", idNum='" + idNum + '\'' +
                ", phNum='" + phNum + '\'' +
                ", pswd='" + pswd + '\'' +
                ", app_id='" + app_id + '\'' +
                ", sent='" + sent + '\'' +
                ", mambu_id='" + mambu_id + '\'' +
                ", create_date='" + create_date + '\'' +
                '}';
    }
}
