package com.alfredtechsystems.myapplication2.dataclasses;

/**
 * Created by ashbel on 26/9/2017.
 */

public class dbSecurities {
    private long sec_id;
    private String sec_details;
    private String sec_state;
    private String sec_date;
    private String sec_type;
    private String sec_create_date;

    public dbSecurities(long sec_id, String sec_details, String sec_state, String sec_date, String sec_create_date, String sec_type) {
        this.sec_id = sec_id;
        this.sec_details = sec_details;
        this.sec_state = sec_state;
        this.sec_date = sec_date;
        this.sec_create_date = sec_create_date;
        this.sec_type = sec_type;
    }

    public dbSecurities(){}

    public long getSec_id() {
        return sec_id;
    }

    public void setSec_id(long sec_id) {
        this.sec_id = sec_id;
    }

    public String getSec_details() {
        return sec_details;
    }

    public void setSec_details(String sec_details) {
        this.sec_details = sec_details;
    }

    public String getSec_state() {
        return sec_state;
    }

    public void setSec_state(String sec_state) {
        this.sec_state = sec_state;
    }

    public String getSec_date() {
        return sec_date;
    }

    public void setSec_date(String sec_date) {
        this.sec_date = sec_date;
    }

    public String getSec_create_date() {
        return sec_create_date;
    }

    public void setSec_create_date(String sec_create_date) {
        this.sec_create_date = sec_create_date;
    }

    public String getSec_type() {
        return sec_type;
    }

    public void setSec_type(String sec_type) {
        this.sec_type = sec_type;
    }

    @Override
    public String toString() {
        return "dbSecurities{" +
                "sec_id=" + sec_id +
                ", sec_details='" + sec_details + '\'' +
                ", sec_state='" + sec_state + '\'' +
                ", sec_date='" + sec_date + '\'' +
                ", sec_type='" + sec_type + '\'' +
                ", sec_create_date='" + sec_create_date + '\'' +
                '}';
    }
}
