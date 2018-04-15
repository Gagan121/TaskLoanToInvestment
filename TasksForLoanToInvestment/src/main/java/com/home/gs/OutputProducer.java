package com.home.gs;

import com.home.gs.Services.Investor;
import com.home.gs.Services.Loan;
import com.home.gs.Services.Service;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;

public class OutputProducer {


    private StringBuilder sb;

    public OutputProducer(){
        sb = new StringBuilder();
    }



    public void printList(List<List> listOfLists){

        for (List<Service> list : listOfLists) {

            NumberFormat format = NumberFormat.getCurrencyInstance();
            sb.append(list.get(0).getType() +"\n");
            for (Service item : list) {
                sb.append("Loan ID :"+item.getUniqueId()+ " The investors are ");
                String space="";
                for (Investor investor:((Loan) item).getInvestorList()) {
                    sb.append( space + investor.getName() +
                            " and they have invested: " +
                            format.format(investor.getMoneyInvested()) + "\n");
                    //made for indenting output
                    space = "\t\t\t\t\t\t\t ";

                }
            }

            printString();

        }



    }

    public void outputSting(String str){
        sb.append(str);
        printString();
    }

    public void printString(){
        String printing = sb.toString();
        sb = new StringBuilder();
        System.out.println(printing);
    }


    public void produceListFormatForJson(List<List> listOfLists){

        JSONObject finalObject = new JSONObject();
        for (List<Service> list:listOfLists) {

            NumberFormat format = NumberFormat.getCurrencyInstance();
            JSONObject jsonObject = new JSONObject();
            for (Service item : list) {

                JSONArray jsonArray = new JSONArray();

                for (Investor investor:((Loan) item).getInvestorList()) {
                    //Array <- Object
                    jsonArray.put(new JSONObject().put(investor.getName(), format.format(investor.getMoneyInvested()) ) );

                }
                //Object <- Array
                jsonObject.put("Loan ID :"+item.getUniqueId(),jsonArray);

            }
            JSONArray productArray = new JSONArray();
            //Array <- Object
            productArray.put(jsonObject);

            //Object <- Array
            finalObject.put(list.get(0).getType().toString() ,productArray);

        }

        writeJsonFile(finalObject);




    }

    public void writeJsonFile(JSONObject jsonObject){
        try(FileWriter fileWriter =  new FileWriter("resources/jsonFile.json")){
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();
        }catch(IOException e){
            System.err.println(e);
        }

        //System.out.println(jsonObject);
    }


}
