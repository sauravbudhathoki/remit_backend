package com.remit.remittance.utils;


import static ch.qos.logback.core.util.StringUtil.isNullOrEmpty;

public class FieldValidationsUtils {

    private FieldValidationsUtils() {
        //prevent instantiation
    }

    //check if String is null or empty

    public static boolean isNullorEmpty(String str){
        return str ==null || str.isBlank();
    }

    //check if number is null or <=0

    public static boolean containsNumbers(String str){
        return str !=null && str.matches(".*\\d.*");
    }

    //check telephone number
    public static boolean isValidPhone(String telephone){
        return telephone !=null && telephone.matches("^[+\\d][\\d\\s-]+$");
    }

    // Check mobile number length (example: 10 digits for Nepal)
    public static boolean isInvalidMobile(String mobile) {
        return isNullOrEmpty(mobile) || !mobile.matches("\\d{10}");
    }

    //  Check if currency code is valid (3-letter ISO like USD, NPR, EUR)
    public static boolean isInvalidCurrency(String currency) {
        return isNullOrEmpty(currency) || !currency.matches("^[A-Z]{3}$");
    }

}
