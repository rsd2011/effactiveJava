package main.java.io.rsd.effectivejava.item36;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class Text {
    public static final int STYLE_BOLD = 1 << 0;            // 1
    public static final int STYLE_ITALIC = 1 << 1;          // 2
    public static final int STYLE_UNDERLINE = 1 << 2;       // 4
    public static final int STYLE_STRIKETHROUGH = 1 << 3;   // 8

    // 매개변수 styles는 0개 이상의 STYLE_ 상수를 비트별 OR한 값이다.
    public void applyStyles(int styles) {}

    public enum Style { BOLD, ITALIC, UNDERLINE, STRIKETHROUGH }

    public void applyStyles(Set<Style> styles) {
        styles.forEach(System.out::println);
    }

    public static void main(String[] args) {
        EnumSet<Style> styles = EnumSet.of(Style.BOLD, Style.ITALIC);
        Text text = new Text();
        text.applyStyles(styles);
        EnumMap<Style, List<String>> a = new EnumMap<>(Style.class);
        a.entrySet().forEach(System.out::println);
        System.out.println(a.values());
    }
}
