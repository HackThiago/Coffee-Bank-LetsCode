package br.com.letscode.util;

public class SystemInterfaceUtil {
    public static String getHeader(String screenName, ConsolePosition pos) {
        final String HEADER_START_TEXT = "Coffee Bank";
        final String time = TimeUtil.now();
        final int consoleMiddleRow = pos.getColumn() / 2;
        final int screenNameStartPadding = consoleMiddleRow - HEADER_START_TEXT.length() - (screenName.length() / 2);
        final int dateStartPadding = pos.getColumn() - screenNameStartPadding - time.length() - screenName.length()
                - HEADER_START_TEXT.length();

        return ConsoleUtil.Attribute.FCOL_BLUE.getEscapeCode()
                + ConsoleUtil.Attribute.REVERSE.getEscapeCode()
                + HEADER_START_TEXT
                + StringUtil.blankSpaces(screenNameStartPadding)
                + screenName
                + StringUtil.blankSpaces(dateStartPadding)
                + time
                + ConsoleUtil.Attribute.RESET.getEscapeCode()
                + ConsoleUtil.NEW_LINE;
    }
}
