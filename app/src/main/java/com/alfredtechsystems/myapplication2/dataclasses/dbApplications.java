package com.alfredtechsystems.myapplication2.dataclasses;

/**
 * Created by ashbel on 3/8/2017.
 */

public class dbApplications {
    private long appId;
    private Double appAmt;
    private String appTenure;
    private String appDate;
    private String appStatus;
    private String appSector;
    private String appInfo;
    private String applcnId;
    private String applcnType;
    private String applcn_address;
    private String applcn_income;
    private String applcn_expense;
    private String applcn_assets;
    private String applcn_collateral;
    private String ops_length;

    public dbApplications(long appId, Double appAmt, String appTenure, String appDate, String appStatus, String appSector, String appInfo, String applcnId, String applcnType, String applcn_address, String applcn_income, String applcn_expense, String applcn_assets, String applcn_collateral,String ops_length) {
        this.appId = appId;
        this.appAmt = appAmt;
        this.appTenure = appTenure;
        this.appDate = appDate;
        this.appStatus = appStatus;
        this.appSector = appSector;
        this.appInfo = appInfo;
        this.applcnId = applcnId;
        this.applcnType = applcnType;
        this.applcn_address = applcn_address;
        this.applcn_income = applcn_income;
        this.applcn_expense = applcn_expense;
        this.applcn_assets = applcn_assets;
        this.applcn_collateral = applcn_collateral;
        this.ops_length = ops_length;
    }

    public dbApplications(){}

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public Double getAppAmt() {
        return appAmt;
    }

    public void setAppAmt(Double appAmt) {
        this.appAmt = appAmt;
    }

    public String getAppTenure() {
        return appTenure;
    }

    public void setAppTenure(String appTenure) {
        this.appTenure = appTenure;
    }

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    public String getAppSector() {
        return appSector;
    }

    public void setAppSector(String appSector) {
        this.appSector = appSector;
    }

    public String getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(String appInfo) {
        this.appInfo = appInfo;
    }

    public String getApplcnId() {
        return applcnId;
    }

    public void setApplcnId(String applcnId) {
        this.applcnId = applcnId;
    }

    public String getApplcnType() {
        return applcnType;
    }

    public void setApplcnType(String applcnType) {
        this.applcnType = applcnType;
    }

    public String getApplcn_address() {
        return applcn_address;
    }

    public void setApplcn_address(String applcn_address) {
        this.applcn_address = applcn_address;
    }

    public String getApplcn_income() {
        return applcn_income;
    }

    public void setApplcn_income(String applcn_income) {
        this.applcn_income = applcn_income;
    }

    public String getApplcn_expense() {
        return applcn_expense;
    }

    public void setApplcn_expense(String applcn_expense) {
        this.applcn_expense = applcn_expense;
    }

    public String getApplcn_assets() {
        return applcn_assets;
    }

    public void setApplcn_assets(String applcn_assets) {
        this.applcn_assets = applcn_assets;
    }

    public String getApplcn_collateral() {
        return applcn_collateral;
    }

    public void setApplcn_collateral(String applcn_collateral) {
        this.applcn_collateral = applcn_collateral;
    }

    public String getOps_length() {
        return ops_length;
    }

    public void setOps_length(String ops_length) {
        this.ops_length = ops_length;
    }

    @Override
    public String toString() {
        return "dbApplications{" +
                "appId=" + appId +
                ", appAmt=" + appAmt +
                ", appTenure='" + appTenure + '\'' +
                ", appDate='" + appDate + '\'' +
                ", appStatus='" + appStatus + '\'' +
                ", appSector='" + appSector + '\'' +
                ", appInfo='" + appInfo + '\'' +
                ", applcnId='" + applcnId + '\'' +
                ", applcnType='" + applcnType + '\'' +
                ", applcn_address='" + applcn_address + '\'' +
                ", applcn_income='" + applcn_income + '\'' +
                ", applcn_expense='" + applcn_expense + '\'' +
                ", applcn_assets='" + applcn_assets + '\'' +
                ", applcn_collateral='" + applcn_collateral + '\'' +
                ", ops_length='" + ops_length + '\'' +
                '}';
    }
}
