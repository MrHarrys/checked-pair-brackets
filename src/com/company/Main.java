package com.company;

import java.util.*;

public class Main {

    static char openBracket = '{';
    static char closeBracket = '}';

    static String bracketsString;
    static String correctString;
    static int numberOfOpenBracket;
    static int numberOfCloseBracket;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.print("Enter { }: ");
        input = scanner.nextLine();
        bracketsString = removedUnusedChars(input);
        correctString = removedIncorrectStartAndEndBrackets(bracketsString);

        for (int i = 0; i < correctString.length(); i++) {
            if (correctString.charAt(i) == openBracket)
                numberOfOpenBracket++;
            if (correctString.charAt(i) == closeBracket)
                numberOfCloseBracket++;
        }

        System.out.println("Number of open brackets - " + numberOfOpenBracket + ".\nNumber of close brackets - " + numberOfCloseBracket);



    }

    private static String removedUnusedChars(String string) {
        return string.replaceAll("[a-zA-Z0-9\\\\s+]", "");
    }

    private static String removedIncorrectStartAndEndBrackets(String string) {
        if (string != null) {
            if (string.startsWith("}")) {
                string = string.substring(1);
                removedIncorrectStartAndEndBrackets(string);
            }
            if (string.endsWith("{")) {
                string = string.substring(0, string.length() - 1);
                removedIncorrectStartAndEndBrackets(string);
            }
        }
        return string;
    }
}