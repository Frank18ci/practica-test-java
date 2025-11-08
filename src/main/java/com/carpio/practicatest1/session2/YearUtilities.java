package com.carpio.practicatest1.session2;

public class YearUtilities {
    public static boolean isLeap(int year){
        if(year%4 == 0){
            if(year%100 ==0){
                return year % 400 == 0;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }
}
