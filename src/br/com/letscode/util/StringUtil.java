package br.com.letscode.util;

public class StringUtil {
    public static String multiply(String str, int times) {
        String newStr = "";
        for (int i = 0; i < times; i++) {
            newStr = newStr.concat(str);
        }
        return newStr;
    }

    public static String blankSpaces(int quantity) {
        String spaces = "";
        for (int i = 0; i < quantity; i++) {
            spaces = spaces.concat(" ");
        }
        return spaces;
    }

    public static String addBlankSpacesToAllLines(String s, int quantity) {
        String spaces = blankSpaces(quantity);
        s = spaces.concat(s);
        s = s.replaceAll("\n", ConsoleUtil.NEW_LINE + spaces);
        return s;
    }

    public static String centralize(String s, int lineWidth) {
        return blankSpaces((lineWidth - s.length()) / 2).concat(s);
    }

    public static String centralizeBlock(String s, int lineWidth) {
        String[] lines = s.split("\n");
        int maxLineLength = 0;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].length() > maxLineLength) {
                maxLineLength = lines[i].length();
            }
        }
        int spacesQuantity = (lineWidth - maxLineLength) / 2;
        s = addBlankSpacesToAllLines(s, spacesQuantity);
        return s;
    }
}
