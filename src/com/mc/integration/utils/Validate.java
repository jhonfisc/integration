package com.mc.integration.utils;

public class Validate {
    public static boolean isValidReference(String reference) {
        if(reference.isEmpty()) return false;
        if(!isValidNumber(reference)) return false;
        return true;
    }

    public static boolean isValidNumber(String reference) {
        try{
            int number = Integer.parseInt(reference);
            if (number < 0) {
                System.out.println("MB-002: La Referencia "+reference+" esta mal formada, numero negativo");
                return false;
            }
            return true;
        }
        catch (NumberFormatException ex){
            System.out.println("MB-001: La Referencia "+reference+" esta mal formada");
            return false;
        }
    }
}
