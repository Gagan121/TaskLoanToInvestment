package com.home.gs;


import static org.junit.Assert.assertTrue;

import com.home.gs.Engine.ProcessingEngine;
import com.home.gs.Services.Service;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ValidProgramTest {

    ManagerController managerController;

    @Test
    public void testForErrorInFiles(){

        String loansFile = "resources/loansError.csv";
        String investmentsFile = "resources/investmentRequestsError.csv";

        managerController = new ManagerController(loansFile,investmentsFile);

        List[] loanToInvestementItem =  managerController.dataValidationChecks();
        //loan = 0 and investements = 1
        int[] lengthOfItem = new int[] {7,13};

        boolean cond1 = lengthOfItem[0] == loanToInvestementItem[0].size();
        boolean con2 = lengthOfItem[1] == loanToInvestementItem[1].size();

        System.out.println();
        assertEquals(cond1 && con2, true);

    }

    @Test
    public void checkEngine(){
        String loansFile = "resources/loansAddUpEven.csv";
        String investmentsFile = "resources/investmentRequestsAddUpEven.csv";

        managerController = new ManagerController(loansFile,investmentsFile);

        List[] loanToInvestementItem =  managerController.dataValidationChecks();

        loanToInvestementItem = managerController.generateDataType(loanToInvestementItem);

        ProcessingEngine processingEngine = new ProcessingEngine();
        //0 = loans , 1 = investments
        List[] filledLoans = processingEngine.process(loanToInvestementItem[0] , loanToInvestementItem[1]);

        List<String> idsTracker = new ArrayList<>(Arrays.asList("6","5"));
        List<String> idsFixed = new ArrayList<>(Arrays.asList("2","3","4"));

        List[] idsForLoans = new List[] {idsTracker,idsFixed};

        boolean outcome = true;

        for (int i = 0; i < filledLoans.length; i++) {
            outcome = ifTheSame(filledLoans[i],idsForLoans[i]) && outcome;
        }

        assertEquals(outcome, true);



    }

    public boolean ifTheSame(List<Service> listOfServices,List<String> ids){

        for (int i = 0; i< listOfServices.size(); i++) {
            if(! listOfServices.get(i).getUniqueId().equals(ids.get(i)) ){
                return false;
            }
        }
        return true;

    }


}
