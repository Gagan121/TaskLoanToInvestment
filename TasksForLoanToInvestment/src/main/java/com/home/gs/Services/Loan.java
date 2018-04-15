package com.home.gs.Services;

import com.home.gs.Services.products.ProductType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Loan extends Service {

    boolean loanFilled = false;

    List<Investor> investorList = new ArrayList<>();

    Date date;

    public Loan(String uniqueId, double amountOfMoney, ProductType type, int term, Date date){

        setIntialValues(uniqueId,amountOfMoney,type,term);
        this.date = date;

    }

    @Override
    public int compareTo(Service o) {
        //bigger 1 oldest
        return this.date.compareTo(((Loan)o).date);
    }

    public void addInvestor(Investor investor){
        investorList.add(investor);
    }

    public List<Investor> getInvestorList(){
        return investorList;
    }

    //if filled the loan has enough money
    @Override
    public void setAmountOfMoney(double amountOfMoney) {
        if(amountOfMoney == 0){
            loanFilled = true;
        }
        super.setAmountOfMoney(amountOfMoney);
    }

    public boolean isLoanFilled() {
        return loanFilled;
    }
}
