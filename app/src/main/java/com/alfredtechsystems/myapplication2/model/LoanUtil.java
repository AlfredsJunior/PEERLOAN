package com.alfredtechsystems.myapplication2.model;

/**
 * Created by alphie on 3/15/2018.
 */

public class LoanUtil extends Loan implements Loan.loanCalculator {


    Loan loan;
    Boolean approve;

    public LoanUtil(Loan loan){

    }
    public LoanUtil(Loan loan, Boolean Invest){
        this.loan = loan;
        this.approve = Invest;
    }

    @Override
    public double loanEngine() {
        return loan.getPrinciple() + (loan.getPrinciple() * loan.getNoOfWeeks() * loan.getRate());
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
