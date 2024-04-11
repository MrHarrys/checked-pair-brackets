package com.company;

import java.util.*;

public class Main {

    static char openBracket = '{';
    static char closeBracket = '}';

    static String stringOpenBracket = String.valueOf(openBracket);
    static String stringCloseBracket = String.valueOf(closeBracket);

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

        processInnerBrackets(correctString, 0, 1, true);

        System.out.println("TEST " + positionOfPairedBrackets + " STRING " + bufferString);

        bufferString = replacedIncorrectStartAndEndBrackets(bufferString);

        System.out.println("AFTER ALL " + bufferString);
    }

    private static String processInnerBrackets(String string, int position, int lengthOfBufChar, boolean boolMarker) {
        if (position < string.length()) {
            if (string.charAt(position) == openBracket) {
                processInnerBrackets(string, position + lengthOfBufChar, lengthOfBufChar, true);
            } else if (string.charAt(position) == closeBracket && !boolMarker) {
                processInnerBrackets(string, position + lengthOfBufChar, lengthOfBufChar, false);
            } else {
                if (string.substring(position).lastIndexOf('a') + string.substring(position).indexOf("a") < -1) {
                    lengthOfBufChar = 1;
                } else {
                    lengthOfBufChar = string.lastIndexOf('a') + string.indexOf("a") - 1;
                }
                System.out.println("Pair bracket founded - positions are " + (position - lengthOfBufChar) + " and " + position);
                positionOfPairedBrackets.add(String.valueOf((position - lengthOfBufChar) + "_" + String.valueOf(position)));
                bufferString = string.substring(0, position - lengthOfBufChar);
                for (int i = 0; i <= lengthOfBufChar; i++) {
                    bufferString += "a";
                }
                bufferString += string.substring(position + 1);
                System.out.println("String is - " + bufferString);
                processInnerBrackets(bufferString, position + 1, lengthOfBufChar, false);
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
                string = removedIncorrectStartAndEndBrackets(string.substring(1));
            }
            if (string.endsWith("{")) {
                string = removedIncorrectStartAndEndBrackets(string.substring(0, string.length() - 1));
            }
            return string;
        }
        return null;
    }

    private static String replacedIncorrectStartAndEndBrackets(String string) {
        boolean isAllBracketsDone = false;
        do {
            if (string.indexOf(stringOpenBracket) > string.indexOf(stringCloseBracket)) {
                string = string.substring(0, string.indexOf(stringCloseBracket)) + "a" + string.substring(string.indexOf(stringCloseBracket) + 1);
            } else if (string.lastIndexOf(stringOpenBracket) > string.lastIndexOf(stringCloseBracket)) {
                string = string.substring(0, string.lastIndexOf(stringOpenBracket)) + "a" + string.substring(string.lastIndexOf(stringOpenBracket) + 1);
            } else isAllBracketsDone = true;
        } while (!isAllBracketsDone);

        return string;
    }
}