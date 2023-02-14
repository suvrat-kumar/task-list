package com.codurance.training.tasks;

public class Utils {
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
