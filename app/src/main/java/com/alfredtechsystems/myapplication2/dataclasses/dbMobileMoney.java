package com.alfredtechsystems.myapplication2.dataclasses;

import java.util.Date;

/**
 * Created by ashbel on 3/8/2017.
 */

public class dbMobileMoney {
    private long mobId;
    private Double mobAmt;
    private String mobName;
    private Date appDate;
    private String mobType;

    public dbMobileMoney(long mobId, Double mobAmt, String mobName, Date appDate, String mobType) {
        this.mobId = mobId;
        this.mobAmt = mobAmt;
        this.mobName = mobName;
        this.appDate = appDate;
        this.mobType = mobType;
    }

    public dbMobileMoney(){}

    public long getMobId() {
        return mobId;
    }

    public void setMobId(long mobId) {
        this.mobId = mobId;
    }

    public Double getMobAmt() {
        return mobAmt;
    }

    public void setMobAmt(Double mobAmt) {
        this.mobAmt = mobAmt;
    }

    public String getMobName() {
        return mobName;
    }

    public void setMobName(String mobName) {
        this.mobName = mobName;
    }

    public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }

    public String getMobType() {
        return mobType;
    }

    public void setMobType(String mobType) {
        this.mobType = mobType;
    }
}
