package com.example.secondlesson;

import java.util.ArrayList;
import java.util.Random;

public class PasswordRandomiser {

    private int num=8;
    private String[] letters;
    private String[] symbols;
    private String[] numbers;

    PasswordRandomiser(String[] letters, String[] symbols, String[] numbers) {
        this.letters = letters;
        this.symbols = symbols;
        this.numbers = numbers;
    }

    public String randomize (boolean isUpper, boolean isDigit, boolean isSpecSymbols, int num) {
        ArrayList<String> dict = new ArrayList<>();
        String result = new String();
        for (String i:letters){
            dict.add(i);
        }
        int n = num;
        Random random = new Random();
        if (isUpper){
            Integer letter = random.nextInt(letters.length - 1);
            result+=letters[letter].toUpperCase();
            for (String i:letters){
                dict.add(i.toUpperCase());
            }
            n--;
        }
        if (isDigit){
            Integer digit = random.nextInt(numbers.length-1);
            result+=numbers[digit];
            for (String i:numbers){
                dict.add(i);
            }
            n--;
        }
        if (isSpecSymbols) {
            Integer symbol = random.nextInt(symbols.length - 1);
            result+=symbols[symbol];
            for (String i:symbols){
                dict.add(i);
            }
            n--;
        }
        for (int i=0; i<n; i++){
            Integer newSymbol = random.nextInt(dict.size()-1);
            result+=dict.get(newSymbol);
        }
        return result;
    }

}
