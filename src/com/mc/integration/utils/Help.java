package com.mc.integration.utils;

public class Help {

    public static void show() {
        System.out.println("MC Integration");
        System.out.println("mc integration documentation v1.0");
        System.out.println("use: mc-integration.jar -service -action data");
        System.out.println("-service: -bp: BP service");
        System.out.println("          -cs: CS service");
        System.out.println("          -nv: Novedades service");
        System.out.println("-action: -c1 create/update BP");
        System.out.println("         -c2 create/update Contacto");
        System.out.println("data for BP service: referencia");
        System.out.println("                     referencia id-contacto");
    }
}
