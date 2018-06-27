package com.alfredtechsystems.myapplication2.dataclasses;

import java.util.Date;

/**
 * Created by ashbel on 10/8/2017.
 */

public class dbSMS {

    private long usrId;
    private String name;
    private Double amount;
    private String trantyp;
    private Date trandate;
    private String tranMonth;

    public long getUsrId() {
        return usrId;
    }

    public void setUsrId(long usrId) {
        this.usrId = usrId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTrantyp() {
        return trantyp;
    }

    public void setTrantyp(String trantyp) {
        this.trantyp = trantyp;
    }

    public Date getTrandate() {
        return trandate;
    }

    public void setTrandate(Date trandate) {
        this.trandate = trandate;
    }

    public String getTranMonth() {
        return tranMonth;
    }

    public void setTranMonth(String tranMonth) {
        this.tranMonth = tranMonth;
    }
}
