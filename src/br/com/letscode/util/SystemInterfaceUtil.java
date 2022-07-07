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

    public static String getMessage(String message, MessageType type, int consoleWidth) {
        String format = ConsoleUtil.Attribute.BRIGHT.getEscapeCode();

        String formatBGColor;
        switch (type) {
            case SUCCESS:
                formatBGColor = ConsoleUtil.Attribute.FCOL_GREEN.getEscapeCode();
                break;
            case ERROR:
                formatBGColor = ConsoleUtil.Attribute.FCOL_RED.getEscapeCode();
                break;
            case WARNING:
                formatBGColor = ConsoleUtil.Attribute.FCOL_YELLOW.getEscapeCode();
                break;
            case INFO:
                formatBGColor = ConsoleUtil.Attribute.FCOL_BLUE.getEscapeCode();
                break;
            default:
                formatBGColor = ConsoleUtil.Attribute.REVERSE.getEscapeCode();
                break;
        }
        format = format.concat(formatBGColor);

        return format
                + ConsoleUtil.Attribute.REVERSE.getEscapeCode()
                + StringUtil.centralize(message, consoleWidth)
                + StringUtil.blankSpaces((consoleWidth / 2) - (message.length() / 2))
                + ConsoleUtil.Attribute.RESET.getEscapeCode()
                + ConsoleUtil.NEW_LINE;
    }
}
