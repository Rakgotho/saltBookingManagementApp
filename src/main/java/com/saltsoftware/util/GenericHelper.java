package com.saltsoftware.util;

/*
 * @co-author name: Linton Appollis
 * student num: 216182484
 * Desc: PatientPaymentRecord class Create utility package that generates Receipt Number Randomly.
 * date: 3 July 2020
 **/
import java.util.UUID;

/*
 Author: Sakeena Francis
 Description: Create utility package that generates ID's
 Date: 11-Jul-2020
*/

public class GenericHelper {

    public static String generateID(){
        return UUID.randomUUID().toString();
    }

    //Create utility package that generates Receipt Number Randomly
    public static String generateReceiptNumber(){
        return UUID.randomUUID().toString();
    }

    //create utility package that generates Cost ID Randomly
    public static String generateCostID(){ return UUID.randomUUID().toString(); }
}

@interface Override {
}

