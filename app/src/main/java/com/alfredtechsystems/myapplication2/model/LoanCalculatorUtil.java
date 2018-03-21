package com.alfredtechsystems.myapplication2.model;

/**
 * Created by alphie on 3/15/2018.
 */

public class LoanCalculatorUtil extends LoanCalculator implements LoanCalculator.loanCalculator {


    LoanCalculator loanCalculator;
    Boolean approve;

    public LoanCalculatorUtil(LoanCalculator loanCalculator){

    }
    public LoanCalculatorUtil(LoanCalculator loanCalculator, Boolean Invest){
        this.loanCalculator = loanCalculator;
        this.approve = Invest;
    }

    @Override
    public double loanEngine() {
        return loanCalculator.getPrinciple() + (loanCalculator.getPrinciple() * loanCalculator.getNoOfWeeks() * loanCalculator.getRate());
    }

    @Override
    public boolean onLoanApproved() {
        //logic
        return approve;
    }


    @Override
    public String loanAmountApproved() {
        if (onLoanApproved()){
            return "Kshs "+loanEngine();
        }
        return "Sorry not credit approved";
    }
}
