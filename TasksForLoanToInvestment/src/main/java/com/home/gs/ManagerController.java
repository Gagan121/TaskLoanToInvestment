package com.home.gs;

import com.home.gs.Engine.ProcessingEngine;
import com.home.gs.Services.Investment;
import com.home.gs.Services.Loan;
import com.home.gs.Services.Service;
import com.home.gs.Services.products.ProductType;

import java.util.ArrayList;
import java.util.List;

public class ManagerController {

    InputTaker inputTaker = new InputTaker();
    Validation validation= new Validation();
    OutputProducer outputProducer = new OutputProducer();

    String loansFile;
    String investmentsFile;

    public ManagerController(){
        loansFile = "resources/loans.csv";
        investmentsFile = "resources/investmentRequests.csv";
    }
    public ManagerController(String loansFile,String investmentsFile){
        this.loansFile = loansFile;
        this.investmentsFile = investmentsFile;
    }


    public List[] dataValidationChecks(){
        //get data from files

        List<String[]> loanItems =  inputTaker.input(loansFile);
        List<String[]> investmentItems =  inputTaker.input(investmentsFile);


        exitLoops(loanItems,investmentItems);

        validation.validateServices(loanItems, "Loans");
        validation.validateServices(investmentItems, "Investments");

        exitLoops(loanItems,investmentItems);


        return new List[] {loanItems,investmentItems};

    }

    private void exitLoops(List<String[]> loanItems,List<String[]> investmentItems){
        //if no files are given or the data is incorrect
        if(loanItems.size() == 0){
            outputProducer.outputSting("No valid loans are present!");
            //ending the program
            System.exit(0);
        }else if(investmentItems.size() == 0){
            outputProducer.outputSting("No valid investments are present!");
            //ending the program
            System.exit(0);
        }
    }


    private List<Service> storeIntoDataTypes(List<String[]> list, String typeOfServices){

        List<Service> serviceList = new ArrayList<>();

        for (String[] item : list) {
            Service service = null;
            if(typeOfServices.equals("Loans")){
                service = new Loan(item[0],
                        validation.StringToDouble(item[1]),
                        //static object getting ProductType from ProductType
                        ProductType.getTypeOfProduct(item[2]),
                        validation.StringToInt(item[3]),
                        validation.StringToDate(item[4]));

            }else if(typeOfServices.equals("Investments")){
                service = new Investment(item[0],
                        validation.StringToDouble(item[1]),
                        //static object getting ProductType from ProductType
                        ProductType.getTypeOfProduct(item[2]),
                        validation.StringToInt(item[3]));
            }

            serviceList.add(service);

        }
        return serviceList;
    }

    public List[] generateDataType(List[] itemsOfLists){
        List<Service> serviceListLoans  = storeIntoDataTypes(itemsOfLists[0], "Loans");
        List<Service> serviceListInvestments =  storeIntoDataTypes(itemsOfLists[1], "Investments");

        return new List[] {serviceListLoans,serviceListInvestments};
    }


    public void run(){
        List[] itemsOfLists = dataValidationChecks();


        itemsOfLists = generateDataType(itemsOfLists);

        ProcessingEngine processingEngine = new ProcessingEngine();
        List[] filledLoans = processingEngine.process(itemsOfLists[0] , itemsOfLists[1]);

        List<List> listOfLists = new ArrayList<>();
        listOfLists.add(filledLoans[0]);
        listOfLists.add(filledLoans[1]);


        outputProducer.printList(listOfLists);


        outputProducer.produceListFormatForJson(listOfLists);


    }

}
