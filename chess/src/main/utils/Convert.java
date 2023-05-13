package main.utils;

import main.exceptions.InvalidMovementException;

public class Convert {
    public static int coordinateToInt(String coordinate) {

        if(coordinate.length() > 2) {
            throw new InvalidMovementException("A coordenada deve ter 2 caracteres ex : a1, d4, h6 ... ");
        }

        char x = coordinate.charAt(0);
        char y =  coordinate.charAt(1);
        int yConverted = Integer.parseInt(y + "");

        switch (x) {
            case 'a' -> {
                return  10 + yConverted;
            }
            case 'b' -> {
                return  2 * 10 + yConverted;
            }
            case 'c' -> {
                return 3 * 10 + yConverted;
            }
            case 'd' -> {
                return 4 * 10 + yConverted;
            }
            case 'e' -> {
                return 5 * 10 + yConverted;
            }
            case 'f' -> {
                return 6 * 10 + yConverted;
            }
            case 'g' -> {
                return 7 * 10 + yConverted;
            }
            case 'h' -> {
                return 8 * 10 + yConverted;
            }
            default -> {
                return 1;
            }
        }

    }

    public static char convertIntToChar(int num) {
        switch (num) {
            case 1 -> {
                return  'a';
            }
            case 2 -> {
                return  'b';
            }
            case 3 -> {
                return 'c';
            }
            case 4 -> {
                return 'd';
            }
            case 5 -> {
                return 'e';
            }
            case 6 -> {
                return 'f';
            }
            case 7 -> {
                return 'g';
            }
            case 8 -> {
                return 'h';
            }

        }
        return 9;
    }

}
