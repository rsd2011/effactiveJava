package io.rsd.effectivejava.item42;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

public class LambdaExpression {
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


    public static void main(String[] args) {
        List<String> words = Arrays.asList("a","aq3","qqrb","avcxc","vvvvvd","sqrasfe");
        //Collections.sort(words, (String s1,String s2) -> Integer.compare(s1.length(), s2.length()));
        //Collections.sort(words, Comparator.comparingInt(String::length));
        words.sort(Comparator.comparingInt(String::length));
        System.out.println(words);
    }
}
