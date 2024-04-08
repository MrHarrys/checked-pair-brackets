package com.company;

import java.util.*;

public class Main {

    static char openBracket = '{';
    static char closeBracket = '}';

    static String bracketsString;
    static String correctString;
    static int numberOfOpenBracket;
    static int numberOfCloseBracket;
    static List<String> positionOfPairedBrackets = new ArrayList<>();
    static String bufferString;

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

        System.out.println("Number of open brackets - " + numberOfOpenBracket + ".\nNumber of close brackets - " + numberOfCloseBracket + ".\nCorrect string is - " + correctString);

        processBrackets(correctString, 1);
    }

    private static String processBrackets(String string, int position) {
        if (position < string.length()) {
            if (string.charAt(position) == openBracket) {
                processBrackets(string, position + 1);
            }
            else if (string.charAt(0) == closeBracket){
                processBrackets(string.substring(1), 1);
            } else {
                System.out.println("Pair bracket founded - positions are " + (position - 1) + " and " + position);
                positionOfPairedBrackets.add(String.valueOf((position - 1) + String.valueOf(position)));
                if (position > 1) {
                    bufferString = string.substring(0, position - 1) + string.substring(position);
                } else {
                    bufferString = string.substring(position);
                }
                System.out.println("String is - " + bufferString);
                processBrackets(string, position + 1);
            }
        }
        return string;
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