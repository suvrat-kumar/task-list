package com.codurance.training.tasks.utils;

import java.text.SimpleDateFormat;

public class Utils {

    public static final SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

    public static Long lastId = 1l;

    public static boolean isAlphaNumeric(String s){
        String alphanumeric= "^[a-zA-Z0-9]*$";
        String alpha= "^[a-zA-Z]*$";
        String numeric= "^[0-9]*$";
        return s.matches(alphanumeric) || s.matches(alpha) || s.matches(numeric);
    }

    public static Long nextId(Long lastId) {
        return ++lastId;
    }

}
