package com.home.gs.Services;

public class Investor {
    private String name;
    private double moneyInvested;

    public Investor(String name, double moneyInvested){
        this.name = name;
        this.moneyInvested = moneyInvested;
    }

    public String getName(){
        return name;
    }
    public double getMoneyInvested(){
        return moneyInvested;
    }



}
