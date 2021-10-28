package io.rsd.effectivejava.item46;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.DoubleBinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Item46 {
    public enum Operation {
        PLUS("+", (x,y) -> x+y),
        MINUS("-", (x,y) -> x-y),
        TIMES("*", (x,y) -> x*y),
        DIVIDE("/", (x,y) -> x/y);

        private final String symbol;
        private final DoubleBinaryOperator op;

        Operation(String symbol, DoubleBinaryOperator op) {
            this.symbol = symbol;
            this.op = op;
        }

        @Override
        public String toString() {
            return symbol;
        }

        public double apply(double x, double y) {
            return op.applyAsDouble(x, y);
        };
    }

    public static void badExample46_1() {
        Stream<String> words = Stream.of("aaaa","bbbb","cccc","aaaa");
        Map<String, Long> freq = new HashMap<>();
        words.forEach(word -> freq.merge(word.toLowerCase(), 1L, Long::sum));
        System.out.println(freq);
    }

    public static void goodExample46_2() {
        Stream<String> words = Stream.of("aaaa","bbbb","cccc","aaaa");
        Map<String, Long> freq = words.collect(groupingBy(String::toLowerCase, counting()));
        System.out.println(freq);
    }

    public static void example46_3() {
        Stream<String> words = Stream.of("a","b","c","d","e","f","g","h","i","j","k","a","b","c","d","e","a","b","d","f","i","aaaa","bbbb","cccc","aaaa");
        Map<String, Long> freq = words.collect(groupingBy(String::toLowerCase, counting()));
//        List<String> topTen =
                freq.entrySet().stream()
//                .map(Map.Entry::getValue).forEach(System.out::println);
//                .sorted(Comparator.comparing(freq::get).reversed())
                .sorted(Comparator.comparing(Map.Entry::getValue))
                .limit(10)
                .forEach(System.out::println);
//                .map(o -> o.getKey())
//                .collect(toList());
//        System.out.println(topTen);
    }

    private static final Map<String, Operation> stringToEnum = Stream.of(Operation.values())
            .collect(toMap(Operation::name, Function.identity()));

    public static void main(String[] args) {
        System.out.println(
                stringToEnum.keySet().stream()
                .collect(joining("|",">","<"))
        );
    }
}
