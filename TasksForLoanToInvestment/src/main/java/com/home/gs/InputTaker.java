package com.home.gs;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class InputTaker {
    public InputTaker(){

    }



    public List<String[]> input(String fileAddress) {
        List<String[]> list = new ArrayList<>();
        try (
                CSVReader reader = new CSVReader(new FileReader(fileAddress));
        ) {
            String[] newLine;
            while((newLine = reader.readNext()) != null){
                list.add(newLine);
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
        //as the title /heading gets taken in as well
        if(!list.isEmpty()){
            list.remove(0);
        }
        return list;
    }


}
