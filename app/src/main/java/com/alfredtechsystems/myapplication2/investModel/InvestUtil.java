package com.alfredtechsystems.myapplication2.investModel;

/**
 * Created by alphie on 3/15/2018.
 */

public class InvestUtil extends Invest implements Invest.InvestCalculator{

    Invest amount;
    Boolean invest;

    public InvestUtil(Invest amount){

    }
    public InvestUtil(Invest Amount, Boolean Invest){
        this.amount=amount;
        this.invest=invest;
    }

    @Override
    public double InvestEngine() {
        return amount.getAmount()+ (amount.getAmount()*amount.getWeeks()*amount.getRate());
    }

    @Override
    public boolean onInvestApproved() {
        return true;
    }

    @Override
    public String InvestAmountApproved() {
        if (onInvestApproved()){
            return "Kshs"+ InvestEngine();
        }
        return "Debit not Invested";
    }
}
