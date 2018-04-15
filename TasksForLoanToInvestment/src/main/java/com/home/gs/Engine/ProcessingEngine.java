package com.home.gs.Engine;

import com.home.gs.Services.Investor;
import com.home.gs.Services.Loan;
import com.home.gs.Services.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcessingEngine {
    public ProcessingEngine(){


    }

    public List[] process(List<Service> loanList, List<Service> investmentList){
        Collections.sort(loanList);

        //0 = tracker and 1 = fixed
        List[] typeOfProductLoans = Service.segregateTF(loanList);
        List[] typeOfProductInvestments = Service.segregateTF(investmentList);

        //get the longest one to invest sort to be at the top
        for (List<Service> items:typeOfProductInvestments) {
            Collections.sort(items);
        }

        for (int i = 0; i < typeOfProductInvestments.length; i++) {
            //pairs loans with investments
            pairUp(typeOfProductLoans[i],typeOfProductInvestments[i]);
        }



        //made dynamic so other products if entered can be be added without changing much of the code
        List[] filledLoans = new List[typeOfProductLoans.length];

        for (int i = 0; i < filledLoans.length; i++) {
            filledLoans[i] = getFilledLoans(typeOfProductLoans[i]);
        }


        return filledLoans;

    }


    public void pairUp(List<Service> loans,List<Service> investments){
        for (Service loan : loans) {
            for (Service investment : investments) {
                if(loan.getTerm() < investment.getTerm()){

                    //being true the loan has been filled by investments
                    if(reduce(loan, investment)){break;}
                }
            }
        }
    }

    public boolean reduce(Service loan , Service investment){
        //if the investment has not money left
        if(investment.getAmountOfMoney() == 0){
            return false;
        }
        double total = loan.getAmountOfMoney() - investment.getAmountOfMoney();

        boolean outcome;

        Loan objLoan = (Loan) loan;

        Investor investor;
        //loan is filled then a negative is produces
        if(total < 0) {
            //turn the negative to a positive
            total = Math.abs(total);
            investor = new Investor(investment.getUniqueId(), loan.getAmountOfMoney());
            objLoan.setAmountOfMoney(0);
            outcome =  true;
        }else if(total == 0){
            //in case the Investment matches the loan exactly
            investor = new Investor(investment.getUniqueId(), investment.getAmountOfMoney());
            objLoan.setAmountOfMoney(0);
            outcome =  true;
        }else {

            objLoan.setAmountOfMoney(total);

            //in the final case the loan still needs to be filled
            /*
            total made to equal 0 so you set the new value into loan
            but it was a positive number before meaning the loan still needs more money
             */
            investor = new Investor(investment.getUniqueId(), investment.getAmountOfMoney());
            total = 0;
            outcome =  false;
        }
        //                    the name of the investment maker

        investment.setAmountOfMoney(total);

        objLoan.addInvestor(investor);

        return outcome;

    }

    public List<Service> getFilledLoans(List<Service> loanList){
        List<Service> filledLoans = new ArrayList<>();
        for (Service items :loanList) {
            boolean outcome = ((Loan)items).isLoanFilled();
            if(outcome){
                filledLoans.add(items);
            }
        }
        return filledLoans;
    }


}
