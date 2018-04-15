package com.home.gs.Services;

import com.home.gs.Services.products.ProductType;

public class Investment extends Service {

    public Investment(String uniqueId, double amountOfMoney, ProductType type, int term){

        setIntialValues(uniqueId,amountOfMoney,type,term);

    }

    @Override
    public int compareTo(Service o) {
        //bigger 1 oldest
        int other = (((Investment)o).term);

        if(term > other){
            return 1;
        }else if(term < other){
            return -1;
        }
        return 0;
    }


}
