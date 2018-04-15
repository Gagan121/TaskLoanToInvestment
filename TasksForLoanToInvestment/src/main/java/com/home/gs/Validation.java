package com.home.gs;

import com.home.gs.Services.products.AllProducts;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public Validation() {
        //look for duplicate values

    }

    public void validateServices(List<String[]> list, String service){
        //checks for Loans that may b the same/ have the same id
        Set<String> uniqueIdString = new HashSet<>();

        validateForErrors(list);

        boolean flag = false;

        Iterator<String[]> iterator = list.iterator();
        while (iterator.hasNext()) {
            String[] item = iterator.next();
            if(service.equals("Loans")){

                flag = validateDataNumber(item[0]) &&
                        validateMoney(item[1]) &&
                        validateDataForWords(item[2]) &&
                        //checks the product exists e.g. TRACKER/FIXED
                        AllProducts.checkExistenceOfProduct(item[2]) &&
                        validateDataNumber(item[3])&&
                        validateDate(item[4]) &&
                        //checking for duplicate loans IDs
                        checkForDuplicates(uniqueIdString,item[0]);
                //then adds the loans to the set
                uniqueIdString.add(item[0]);

            //id
            //loan amount
            //product
            //term
            //date

            }else if(service.equals("Investments")){
                flag = validateDataForWords(item[0]) &&
                        validateMoney(item[1]) &&
                        validateDataForWords(item[2]) &&
                        //checks the product exists e.g. TRACKER/FIXED
                        AllProducts.checkExistenceOfProduct(item[2]) &&
                        validateDataNumber(item[3]);
            }
            //name
            //loan amount
            //product
            //term


            //if an error is found
            if(!flag){

                iterator.remove();
            }
            flag = false;

        }

    }

    public boolean checkForDuplicates(Set<String> setID,String item){
        //if the set has the value it means its a duplicate and return true
        //so its flipped to make the flag false;
        return !setID.contains(item);
    }


    //no need to return - its parsed by reference
    public void validateForErrors(List<String[]> list) {
        //loops through the String array in the list
        Iterator<String[]> iterator = list.iterator();
        while (iterator.hasNext()) {

            String[] item = iterator.next();

            for (String subItem : item) {
                //then checks if the String has missing data
                //valid data check are done in different method as validity check are always changing
                if(subItem.equals("")){
                    //and removes the items from the data
                    iterator.remove();
                    break;
                }
            }
        }
    }



    //not setting the words FIXED or TRACKER as products could change over time
    public boolean validateDataForWords(String subItem){
        Pattern pattern = Pattern.compile("[^a-zA-Z]");
        Matcher match = pattern.matcher(subItem);
        //if found it means data is not valid and should be removed
        //returning true - means data is valid
        return !match.find();
    }

    public boolean validateDataNumber(String subItem){
        Pattern pattern = Pattern.compile("\\D");
        Matcher match = pattern.matcher(subItem);
        //if found it means data is not valid and should be removed
        //returning true - means data is valid
        return !match.find();
    }

    public boolean validateMoney(String subItem){
        //works with 100, 100.00
        Pattern pattern = Pattern.compile("^\\d*\\.?\\d{2}$");
        Matcher match = pattern.matcher(subItem);
        //if found it means data is accurate
        //returning true - means data is valid
        return match.find();
    }

    public boolean validateDate(String subItem){
        Pattern pattern = Pattern.compile("^\\d{1,2}\\/\\d{1,2}\\/(\\d{4}|\\d{2})$");
        Matcher match = pattern.matcher(subItem);
        //if found it means data is accurate
        //returning true - means data is valid
        return match.find();
    }



    public int StringToInt(String str){
        try{
            return Integer.parseInt(str);
        }catch (NumberFormatException e){
            System.err.println(e);
        }
        return -1;
    }

    public double StringToDouble(String str){
        try{
            return Double.parseDouble(str);
        }catch (NumberFormatException e){
            System.err.println(e);
        }
        return -1;
    }



    //check if this works
    public Date StringToDate(String str){
        try{
            String[] dateString = str.split("/");
            Calendar c = new GregorianCalendar(
                    //year
                    StringToInt(dateString[2]),
                    //month as 0 = January
                    StringToInt(dateString[1]) -1,
                    //day
                    StringToInt(dateString[0]));
            return c.getTime();
        }catch (Exception e){
            System.err.println(e);
        }
        return null;
    }








}

