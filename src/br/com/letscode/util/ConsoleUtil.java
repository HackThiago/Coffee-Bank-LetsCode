/*
 * Huge thanks to Janne Tuukkanen's answer on this
 * <https://stackoverflow.com/questions/48773272/write-print-to-the-bottom-of-terminal>
 */

package br.com.letscode.util;

public class ConsoleUtil {
    static final int DEFAULT_SPEED = 600;

    public static final String ESC = "\033";

    public enum AttributeEnum {
        ATTR_RESET(0),
        ATTR_BRIGHT(1),
        ATTR_USCORE(4),
        ATTR_BLINK(5),
        ATTR_REVERSE(7),

        ATTR_FCOL_BLACK(30),
        ATTR_FCOL_RED(31),
        ATTR_FCOL_GREEN(32),
        ATTR_FCOL_YELLOW(33),
        ATTR_FCOL_BLUE(34),

        ATTR_BCOL_BLACK(40),
        ATTR_BCOL_RED(41),
        ATTR_BCOL_GREEN(42),
        ATTR_BCOL_YELLOW(43),
        ATTR_BCOL_BLUE(44);

        private int value;

        private AttributeEnum(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
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

    public static String setAttribute(int attr) {
        return String.format("\033[%dm", attr);
    }

    public static String setAttributes(int[] attr) {
        String attributes = "";
        for (int i = 0; i < attr.length; i++) {
            attributes = attributes.concat(setAttribute(attr[i]));
        }
        return attributes;
    }

    public static void slowPrint(String s) {
        slowPrint(s, DEFAULT_SPEED);
    }

    public static void slowPrint(String s, int bps) {
        for (int i = 0; i < s.length(); i++) {
            System.out.print(s.charAt(i));

            if (bps == 0)
                continue;

            try {
                Thread.sleep((int) (8000.0 / bps));
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
