package com.alfredtechsystems.myapplication2.dataclasses;

/**
 * Created by ashbel on 3/8/2017.
 */

public class dbLoans {
    private long ln_id;
    private String ln_balance;
    private String ln_tenure;
    private String ln_int_rate;
    private String ln_pr_paid;
    private String ln_amount;
    private String ln_fee_paid;
    private String ln_int_paid;
    private String ln_acc_num;
    private String ln_set_num;
    private String ln_acc_sate;
    private String ln_amt_due;

    public dbLoans(long ln_id, String ln_balance, String ln_tenure, String ln_int_rate, String ln_pr_paid, String ln_amount, String ln_fee_paid, String ln_int_paid, String ln_acc_num, String ln_set_num, String ln_acc_sate, String ln_amt_due) {
        this.ln_id = ln_id;
        this.ln_balance = ln_balance;
        this.ln_tenure = ln_tenure;
        this.ln_int_rate = ln_int_rate;
        this.ln_pr_paid = ln_pr_paid;
        this.ln_amount = ln_amount;
        this.ln_fee_paid = ln_fee_paid;
        this.ln_int_paid = ln_int_paid;
        this.ln_acc_num = ln_acc_num;
        this.ln_set_num = ln_set_num;
        this.ln_acc_sate = ln_acc_sate;
        this.ln_amt_due = ln_amt_due;
    }

    public dbLoans() {
    }

    public long getLn_id() {
        return ln_id;
    }

    public void setLn_id(long ln_id) {
        this.ln_id = ln_id;
    }

    public String getLn_balance() {
        return ln_balance;
    }

    public void setLn_balance(String ln_balance) {
        this.ln_balance = ln_balance;
    }

    public String getLn_tenure() {
        return ln_tenure;
    }

    public void setLn_tenure(String ln_tenure) {
        this.ln_tenure = ln_tenure;
    }

    public String getLn_int_rate() {
        return ln_int_rate;
    }

    public void setLn_int_rate(String ln_int_rate) {
        this.ln_int_rate = ln_int_rate;
    }

    public String getLn_pr_paid() {
        return ln_pr_paid;
    }

    public void setLn_pr_paid(String ln_pr_paid) {
        this.ln_pr_paid = ln_pr_paid;
    }

    public String getLn_amount() {
        return ln_amount;
    }

    public void setLn_amount(String ln_amount) {
        this.ln_amount = ln_amount;
    }

    public String getLn_fee_paid() {
        return ln_fee_paid;
    }

    public void setLn_fee_paid(String ln_fee_paid) {
        this.ln_fee_paid = ln_fee_paid;
    }

    public String getLn_int_paid() {
        return ln_int_paid;
    }

    public void setLn_int_paid(String ln_int_paid) {
        this.ln_int_paid = ln_int_paid;
    }

    public String getLn_acc_num() {
        return ln_acc_num;
    }

    public void setLn_acc_num(String ln_acc_num) {
        this.ln_acc_num = ln_acc_num;
    }

    public String getLn_set_num() {
        return ln_set_num;
    }

    public void setLn_set_num(String ln_set_num) {
        this.ln_set_num = ln_set_num;
    }

    public String getLn_acc_sate() {
        return ln_acc_sate;
    }

    public void setLn_acc_sate(String ln_acc_sate) {
        this.ln_acc_sate = ln_acc_sate;
    }

    public String getLn_amt_due() {
        return ln_amt_due;
    }

    public void setLn_amt_due(String ln_amt_due) {
        this.ln_amt_due = ln_amt_due;
    }

    @Override
    public String toString() {
        return "dbLoans{" +
                "ln_id=" + ln_id +
                ", ln_balance='" + ln_balance + '\'' +
                ", ln_tenure='" + ln_tenure + '\'' +
                ", ln_int_rate='" + ln_int_rate + '\'' +
                ", ln_pr_paid='" + ln_pr_paid + '\'' +
                ", ln_amount='" + ln_amount + '\'' +
                ", ln_fee_paid='" + ln_fee_paid + '\'' +
                ", ln_int_paid='" + ln_int_paid + '\'' +
                ", ln_acc_num='" + ln_acc_num + '\'' +
                ", ln_set_num='" + ln_set_num + '\'' +
                ", ln_acc_sate='" + ln_acc_sate + '\'' +
                ", ln_amt_due='" + ln_amt_due + '\'' +
                '}';
    }
}
