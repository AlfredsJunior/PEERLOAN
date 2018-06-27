package com.alfredtechsystems.myapplication2.dataclasses;

/**
 * Created by ashbel on 2/10/2017.
 */

public class dbSchedules {
    private long id;
    private String amount_due;
    private String amount_paid;
    private String due_date;
    private String ln_account;
    private String state;

    public dbSchedules(long id, String amount_due, String amount_paid, String due_date, String ln_account, String state) {
        this.id = id;
        this.amount_due = amount_due;
        this.amount_paid = amount_paid;
        this.due_date = due_date;
        this.ln_account = ln_account;
        this.state = state;
    }

    public dbSchedules() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAmount_due() {
        return amount_due;
    }

    public void setAmount_due(String amount_due) {
        this.amount_due = amount_due;
    }

    public String getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(String amount_paid) {
        this.amount_paid = amount_paid;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getLn_account() {
        return ln_account;
    }

    public void setLn_account(String ln_account) {
        this.ln_account = ln_account;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "dbSchedules{" +
                "id=" + id +
                ", amount_due='" + amount_due + '\'' +
                ", amount_paid='" + amount_paid + '\'' +
                ", due_date='" + due_date + '\'' +
                ", ln_account='" + ln_account + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
