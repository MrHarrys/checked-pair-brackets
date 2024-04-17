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

//        System.out.println("TEST " + positionOfPairedBrackets + "\nSTRING " + bufferString);

        bufferString = replacedIncorrectStartAndEndBrackets(bufferString);

        if (bufferString.contains(stringOpenBracket) || bufferString.contains(stringCloseBracket))
            processFinalString(bufferString, 0, 0);

//        System.out.println("AFTER ALL " + bufferString + "\n" + positionOfPairedBrackets);

        StringBuilder correctBracketsString = new StringBuilder();
        correctBracketsString.setLength(positionOfPairedBrackets.size());

        for (String element : positionOfPairedBrackets){
            int openBracketPos = Integer.parseInt(element.substring(0, (element.indexOf("_"))));
            int closeBracketPos = Integer.parseInt(element.substring((element.indexOf("_") + 1)));

            correctBracketsString.insert(openBracketPos, stringOpenBracket);
            correctBracketsString.insert(closeBracketPos, stringCloseBracket);
        }

        System.out.println("FINALLY - " + correctBracketsString.toString().replaceAll("[^{}]",""));
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
//                System.out.println("String is - " + bufferString);
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
            if (string.indexOf(stringOpenBracket) > 0 && string.indexOf(stringCloseBracket) > 0) {
                if (string.indexOf(stringOpenBracket) > string.indexOf(stringCloseBracket)) {
                    string = string.substring(0, string.indexOf(stringCloseBracket)) + "a" + string.substring(string.indexOf(stringCloseBracket) + 1);
                } else if (string.lastIndexOf(stringOpenBracket) > string.lastIndexOf(stringCloseBracket)) {
                    string = string.substring(0, string.lastIndexOf(stringOpenBracket)) + "a" + string.substring(string.lastIndexOf(stringOpenBracket) + 1);
                } else isAllBracketsDone = true;
            } else {
                if (string.indexOf(stringOpenBracket) > 0) {
                    string = string.substring(0, string.indexOf(openBracket)) + string.substring(string.lastIndexOf(openBracket));
                } else if (string.indexOf(stringCloseBracket) > 0) {
                    string = string.substring(0, string.indexOf(closeBracket)) + string.substring(string.lastIndexOf(closeBracket));
                }
                isAllBracketsDone = true;
            }
        } while (!isAllBracketsDone);

        return string;
    }

    private static String processFinalString(String string, int position, int posOfOpenBracket){
         if (position < string.length()){
            if (string.charAt(position) == 'a') {
                processFinalString(string, position + 1,  posOfOpenBracket);
            }
            else if (string.charAt(position) == openBracket){
                processFinalString(string, position + 1, position);
            }
            else if (string.charAt(position) == closeBracket){
                int lengthOfBufChar = position - posOfOpenBracket;
                System.out.println("Pair bracket founded - positions are " + (position - lengthOfBufChar) + " and " + position);
                positionOfPairedBrackets.add((position - lengthOfBufChar) + "_" + position);
                bufferString = string.substring(0, position - lengthOfBufChar);
                for (int i = 0; i <= lengthOfBufChar; i++) {
                    bufferString += "a";
                }
                bufferString += string.substring(position + 1);
//                System.out.println("String is - " + bufferString);
                processFinalString(bufferString, 0, 0);
            }
        }
        return string;
    }
}