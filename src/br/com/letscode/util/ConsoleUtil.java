/*
 * Huge thanks to Janne Tuukkanen's answer on this
 * <https://stackoverflow.com/questions/48773272/write-print-to-the-bottom-of-terminal>
 */

package br.com.letscode.util;

public class ConsoleUtil {
    static final int DEFAULT_SPEED = 600;

    public static final String ESC = "\033";

    public enum Attribute {
        RESET(0),
        BRIGHT(1),
        USCORE(4),
        BLINK(5),
        REVERSE(7),

        FCOL_BLACK(30),
        FCOL_RED(31),
        FCOL_GREEN(32),
        FCOL_YELLOW(33),
        FCOL_BLUE(34),

        BCOL_BLACK(40),
        BCOL_RED(41),
        BCOL_GREEN(42),
        BCOL_YELLOW(43),
        BCOL_BLUE(44);

        private String escapeCode;

        private Attribute(int code) {
            this.escapeCode = String.format("\033[%dm", code);
        }

        public String getEscapeCode() {
            return this.escapeCode;
        }
    }

    public static String clearScreen() {
        return "\033[2J";
    }

    public static String cursorHome() {
        return "\033[H";
    }

    public static String cursorTo(int row, int column) {
        return String.format("\033[%d;%dH", row, column);
    }

    public static String cursorSave() {
        return "\033[s";
    }

    public static String cursorRestore() {
        return "\033[u";
    }

    public static String scrollScreen() {
        return "\033[r";
    }

    public static String scrollSet(int top, int bottom) {
        return String.format("\033[%d;%dr", top, bottom);
    }

    public static String scrollUp() {
        return "\033D";
    }

    public static String scrollDown() {
        return "\033D";
    }

    public static void slowPrint(String s) {
        slowPrint(s, DEFAULT_SPEED);
    }

    public static void slowPrint(String s, int bps) {
        for (int i = 0; i < s.length(); i++) {
            System.out.print(s.charAt(i));

            if (bps == 0) {
                continue;
            }

            try {
                Thread.sleep((int) (8000.0 / bps));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
