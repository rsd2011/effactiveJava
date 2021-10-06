package main.java.io.rsd.effectivejava.item28;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Covariant {
    public static void covariant() {
        Object[] objectArray = new Long[1];
        objectArray[0] = 1L;
    }

    public static void main(String[] args) {
        covariant();
    }
}
