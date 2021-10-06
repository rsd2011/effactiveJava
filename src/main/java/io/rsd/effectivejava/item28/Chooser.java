package main.java.io.rsd.effectivejava.item28;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Chooser<T> {
    private final List<T> choiceArray;

    public Chooser(Collection<T> choices) {
        choiceArray = new ArrayList<>(choices);
    }

    public Object choose() {
        Random random = ThreadLocalRandom.current();
        return choiceArray.get(random.nextInt(choiceArray.size()));
    }

//    public static void main(String[] args) {
//        List<String> list = Arrays.asList("1","2","3","4","5","6");
//        int result = new Chooser<Integer>(list).choose();
//        System.out.println(result);
//    }
}
