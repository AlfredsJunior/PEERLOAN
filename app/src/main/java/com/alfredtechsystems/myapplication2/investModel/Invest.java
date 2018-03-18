package com.alfredtechsystems.myapplication2.investModel;

/**
 * Created by alphie on 3/15/2018.
 */

public class Invest {

    private double Amount;
    private double Weeks;
    private double Rate;


    public Invest(){

    }


    public Invest( double Amount,  double Weeks, double Rate){
        this.Amount=Amount;
       this.Weeks=Weeks;
       this.Rate=Rate;
    }


    public interface  InvestCalculator{
        double InvestEngine();
        boolean onInvestApproved();
        String InvestAmountApproved();
    }

    public double getAmount() {return Amount;}
    public double getWeeks() {return Weeks;}
    public double getRate() {return Rate;}


    public void setAmount(double amount) {this.Amount = amount;}
    public void setWeeks(double weeks) {this.Weeks = weeks;}
    public void setRate(double rate) {this.Rate = rate;}
}



