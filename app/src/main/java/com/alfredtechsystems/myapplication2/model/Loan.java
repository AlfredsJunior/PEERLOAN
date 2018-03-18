package com.alfredtechsystems.myapplication2.model;

/**
 * Created by alphie on 3/15/2018.
 */

public class Loan {

    private double principle;
    private double noOfWeeks;
    private double rate;

    public Loan(){

    }
    public Loan(double principle, double noOfWeeks, double rate){
        this.noOfWeeks = noOfWeeks;
        this.principle = principle;
        this.rate = rate;
    }

    public interface loanCalculator{

        double loanEngine();
        boolean onLoanApproved();
        String loanAmountApproved();
    }

    public double getNoOfWeeks() {
        return noOfWeeks;
    }

    public double getPrinciple() {
        return principle;
    }

    public double getRate() {
        return rate;
    }

    public void setNoOfWeeks(double noOfWeeks) {
        this.noOfWeeks = noOfWeeks;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setPrinciple(double principle) {
        this.principle = principle;
    }
}
