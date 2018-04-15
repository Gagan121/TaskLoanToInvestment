package com.home.gs.Services.products;

import com.home.gs.Services.Service;

import java.util.*;

public class AllProducts {

    String product;

    static Set<String> setOfProductType = new HashSet<>(Arrays.asList("FIXED", "TRACKER"));

    ProductType type;

    AllProducts(String product){
        this.product = product;
        type = getTypeOfProduct(product);
    }

    public static boolean checkExistenceOfProduct(String str){
        return setOfProductType.contains(str);
    }


    public ProductType getTypeOfProduct (String str){
        if(str.equals("FIXED")){
            return new Fixed();
        }else if(str.equals("TRACKER")) {
            return new Tracker();
        }
        return null;
    }


    public static List[] divideList(List<Service> list){
        List<Service> tracker = new ArrayList<>();
        List<Service> fixed = new ArrayList<>();

        for (Service item : list) {


            if(AllProducts.isProductFixed(item.getType())){
                fixed.add(item);
            }else if(AllProducts.isProductTracker(item.getType())){
                tracker.add(item);
            }

        }

        return new List[] {tracker,fixed};
    }



    public static boolean isProductTracker (ProductType type){
        if(type instanceof Tracker){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isProductFixed (ProductType type){
        if(type instanceof Fixed){
            return true;
        }else{
            return false;
        }
    }


    public ProductType getProductType(){
        return type;
    }



    @Override
    public String toString() {
        return product;
    }
}


