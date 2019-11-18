package com.example.secondlesson;

import java.util.HashMap;
import java.util.Map;

public class Translater {
    private Map<String, String> dictionary;

    private String[] Russians;
    private String[] Latins;

    public Translater (String[] russians, String[] latins) {
        if (russians.length != latins.length) {
            throw new IllegalArgumentException();
        }
        Russians = russians;
        Latins = latins;
        dictionary = new HashMap<>();
        for (int i=0; i<Russians.length; i++){
            dictionary.put(Russians[i], Latins[i]);
        }
    }

    public String convert (CharSequence source) {
        StringBuilder result = new StringBuilder();
        for (int i=0; i< source.length(); i++){
            char c = source.charAt(i);
            String key = String.valueOf(Character.toLowerCase(c));
            if (dictionary.containsKey(key.toLowerCase())){
            result.append(Character.isUpperCase(c)?
                    dictionary.get(key).toUpperCase() : dictionary.get(key));
        }

            if (result.length()<=i) {
                result.append(c);
            }
        }
        return result.toString();
    }

    public int getQuality (CharSequence password){
        return Math.min(password.length(), 10);
    }
}
