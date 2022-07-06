package br.com.letscode.util;

public class StringUtil {
    public static String BlankSpaces(int quantity) {
        String spaces = "";
        for (int i = 0; i < quantity; i++) {
            spaces = spaces.concat(" ");
        }
        return spaces;
    }

    public static String AddBlankSpacesToAllLines(String s, int quantity) {
        String spaces = BlankSpaces(quantity);
        s = spaces.concat(s);
        s = s.replaceAll("\n", ConsoleUtil.NEW_LINE + spaces);
        return s;
    }
}
