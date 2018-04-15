package com.home.gs.Services;

import com.home.gs.Services.products.AllProducts;
import com.home.gs.Services.products.ProductType;

import java.util.List;

public class Service implements Comparable<Service>{

    String uniqueId;
    double amountOfMoney;
    ProductType type;
    int term;

    public void setIntialValues(String uniqueId,
                                double amountOfMoney,
                                ProductType type, int term){
        this.uniqueId = uniqueId;
        setAmountOfMoney(amountOfMoney);
        this.type = type;
        this.term = term;

    }

    public static List[] segregateTF(List<Service> list){
        return AllProducts.divideList(list);
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public double getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public ProductType getType() {
        return type;
    }

    public int getTerm() {
        return term;
    }


    @Override
    public int compareTo(Service o) {
        return 0;
    }
}
