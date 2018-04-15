package com.home.gs.Services.products;


public abstract class ProductType {
    String name = null;

    @Override
    public String toString() {
        return name;
    }

    public static ProductType getTypeOfProduct (String str){
        AllProducts allProducts = new AllProducts(str);
        return allProducts.getProductType();
    }



}
