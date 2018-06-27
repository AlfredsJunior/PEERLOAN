package com.alfredtechsystems.myapplication2.dataclasses;

/**
 * Created by ashbel on 5/10/2017.
 */

public class dbTransactions {
    private long id;
    private String tran_date;
    private String tran_type;
    private String tran_amount;
    private String tran_balance;
    private String tran_account;
    private String trans_id;

    public dbTransactions() {
    }

    public dbTransactions(long id, String tran_date, String tran_type, String tran_amount, String tran_balance, String tran_account, String trans_id) {
        this.id = id;
        this.tran_date = tran_date;
        this.tran_type = tran_type;
        this.tran_amount = tran_amount;
        this.tran_balance = tran_balance;
        this.tran_account = tran_account;
        this.trans_id = trans_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTran_date() {
        return tran_date;
    }

    public void setTran_date(String tran_date) {
        this.tran_date = tran_date;
    }

    public String getTran_type() {
        return tran_type;
    }

    public void setTran_type(String tran_type) {
        this.tran_type = tran_type;
    }

    public String getTran_amount() {
        return tran_amount;
    }

    public void setTran_amount(String tran_amount) {
        this.tran_amount = tran_amount;
    }

    public String getTran_balance() {
        return tran_balance;
    }

    public void setTran_balance(String tran_balance) {
        this.tran_balance = tran_balance;
    }

    public String getTran_account() {
        return tran_account;
    }

    public void setTran_account(String tran_account) {
        this.tran_account = tran_account;
    }

    public String getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(String trans_id) {
        this.trans_id = trans_id;
    }


    @Override
    public String toString() {
        return "dbTransactions{" +
                "id=" + id +
                ", tran_date='" + tran_date + '\'' +
                ", tran_type='" + tran_type + '\'' +
                ", tran_amount='" + tran_amount + '\'' +
                ", tran_balance='" + tran_balance + '\'' +
                ", tran_account='" + tran_account + '\'' +
                ", trans_id='" + trans_id + '\'' +
                '}';
    }
}
