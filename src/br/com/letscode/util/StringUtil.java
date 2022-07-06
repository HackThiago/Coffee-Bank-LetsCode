package br.com.letscode.util;

public class StringUtil {
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
        s = s.replaceAll("\n", "\n" + spaces);
        return s;
    }
}
