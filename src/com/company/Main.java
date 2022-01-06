package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Enter { }: ");
        input = scanner.nextLine();
        System.out.println(validate(input));
    }

    public static Set<String> validate(String input) {
        HashSet<String> hashSet = new HashSet<>();

        int countI = 0, countO = 0;
        TreeMap input2 = new TreeMap();
        if (input.charAt(0) == '}') {
            int count = input.indexOf('{');
            for (int i = 0; i < count; i++) {
                input = removeCharAt(input, 0);
            }
        }
        if (input.charAt(input.length() - 1) == '{') {
            for (int i = input.length() - 1; i > input.lastIndexOf('}'); i--) {
                input = removeCharAt(input, input.length() - 1);
            }
        }

        hashSet.add(form4(input));

        StringBuilder input1 = new StringBuilder(input);

        for (int i = 0; i < input.length(); i++) {
            if ((input.charAt(i)) == '{') {
                countI++;
            }
            if ((input.charAt(i)) == '}') {
                countO++;
            }
        }
        if (countI > countO) {
            for (int i = 0; i < (countI - countO); i++)
                input = removeCharAt(input, input.lastIndexOf('{'));
        }
        if (countI < countO) {
            for (int i = 0; i < (countO - countI); i++)
                input = removeCharAt(input, input.indexOf('}'));
        }

        hashSet.add(input);

        TreeMap hashMap = new TreeMap();
        TreeMap hashMap1 = new TreeMap();
        for (int i = 0; i < input1.length(); i++) {
            if (input1.charAt(i) == '{') {
                hashMap.put(i, "{");
            }
            if (input1.charAt(i) != '{' && input1.charAt(i) != '}') {
                hashMap1.put(i, input1.charAt(i));
            }
        }

        int counterCollection = hashMap.size();
        for (int i = 0; i < counterCollection; i++) {
            if (hashMap.isEmpty() == true) {
                break;
            }
            for (int j = (int) hashMap.lastKey() + 1; j < input1.length(); j++) {
                if (hashMap.isEmpty() == true) {
                    break;
                }
                if (input1.indexOf("}") == -1) {
                    break;
                }
                if (input1.charAt(j) == '}') {
                    input2.put(hashMap.lastKey(), '{');
                    input2.put(j, '}');
                    hashMap.remove(hashMap.lastKey());
                    input1.setCharAt(j, '!');
                }
            }
        }
        int size = hashMap1.size();
        for (int i = 0; i < size; i++) {
            input2.put(hashMap1.firstKey(), hashMap1.get(hashMap1.firstKey()));
            hashMap1.remove(hashMap1.firstKey());
        }
        LinkedList<String> linkedList = new LinkedList<>(input2.values());
        String in2 = linkedList.toString();
        in2 = removeCharAt(in2, 0);
        in2 = removeCharAt(in2, in2.length() - 1);
        int counter = 1;
        for (int i = 0; i < in2.length(); i++) {
            if (counter >= in2.length())
                break;
            in2 = removeCharAt(in2, counter);
            counter += 2;
            if (counter >= in2.length())
                break;
        }
        in2 = in2.replaceAll("\\s", "");
        hashSet.add(in2);
        return hashSet;
    }

    public static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }

    public static String form4(String input) {
        TreeMap input1 = new TreeMap();
        HashMap brackets = new HashMap();
        TreeMap symbols = new TreeMap();
        brackets.put(0, "{");
        brackets.put(1, "}");
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '{' || input.charAt(i) == '}')
                input1.put(i, input.charAt(i));
            if (input.charAt(i) != '{' && input.charAt(i) != '}') {
                symbols.put(i, input.charAt(i));
            }
        }
        LinkedList<String> keys = new LinkedList<>(input1.keySet());
        String stringKeys = keys.toString();
        stringKeys = removeCharAt(stringKeys, 0);
        stringKeys = removeCharAt(stringKeys, stringKeys.length() - 1);
        int counter1 = 1;
        for (int i = 0; i < stringKeys.length(); i++) {
            if (1 >= stringKeys.length())
                break;
            stringKeys = removeCharAt(stringKeys, counter1);
            counter1 += 2;
            if (counter1 >= stringKeys.length())
                break;
        }
        stringKeys = stringKeys.replaceAll("\\s", "");
        for (int i = 0; i < input1.size(); i++) {
            if (stringKeys + 1 == input1.lastKey()) {
                break;
            }
            if (input1.get(Character.getNumericValue(stringKeys.charAt(i))).toString().equals(brackets.get(0).toString()) && input1.get(Character.getNumericValue(stringKeys.charAt(i + 1))).toString().equals(brackets.get(0).toString())) {
                input1.remove(Character.getNumericValue(stringKeys.charAt(i)));
            } else if (input1.get(Character.getNumericValue(stringKeys.charAt(i))).toString().equals(brackets.get(1).toString()) && input1.get(Character.getNumericValue(stringKeys.charAt(i + 1))).toString().equals(brackets.get(1).toString())) {
                input1.remove(Character.getNumericValue(stringKeys.charAt(i)));
            } else if (input1.get(Character.getNumericValue(stringKeys.charAt(i))).toString().equals(brackets.get(0).toString()) && input1.get(Character.getNumericValue(stringKeys.charAt(i + 1))).toString().equals(brackets.get(1).toString())) {
                continue;
            }
        }
        int count = symbols.size();
        for (int i = 0; i < count; i++) {
            input1.put(symbols.firstKey(), symbols.get(symbols.firstKey()));
            symbols.remove(symbols.firstKey());
        }

        LinkedList<String> newArray = new LinkedList<>(input1.values());
        String array = newArray.toString();
        array = removeCharAt(array, 0);
        array = removeCharAt(array, array.length() - 1);
        int counter = 1;
        for (int i = 0; i < array.length(); i++) {
            if (counter >= array.length())
                break;
            array = removeCharAt(array, counter);
            counter += 2;
            if (counter >= array.length())
                break;
        }
        array = array.replaceAll("\\s", "");
        return array;
    }
}