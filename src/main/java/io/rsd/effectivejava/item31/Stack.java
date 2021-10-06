package main.java.io.rsd.effectivejava.item31;

import java.util.*;

public class Stack<E> {
    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public Stack() {
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop() {
        if (size == 0)
            throw new EmptyStackException();
        E result = elements[--size];
        elements[size] = null;
        return result;
    }

    public void pushAll(Iterable<? extends E> src) {
        for (E e : src) {
            push(e);
        }
    }

    public void popAll(Collection<? super E> dst) {
        while (!isEmpty()) {
            dst.add(pop());
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    public static <E extends Comparable<? super E>> E max(Collection<? extends E> c) {
        if (c.isEmpty())
            throw new IllegalArgumentException("컬렉션이 비어 있습니다.");

        E result = null;
        for (E e : c) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }
        return result;
    }

    public static void swap(List<?> list, int i, int j) {
        swapHelper(list, i, j);
    }

    private static <E> void swapHelper(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }

    public static void stackTest() {
        Stack<Number> numberStack = new Stack<>();
        Iterable<Integer> integers = Arrays.asList(1,2,3,4,5);
        Iterable<Double> doubles = Arrays.asList(1.1,2.3,3.5,4.6,5.1);
        numberStack.pushAll(integers);
        numberStack.pushAll(doubles);

        Collection<Object> objects = new ArrayList<>();
        numberStack.popAll(objects);
        objects.forEach(System.out::println);
    }

    public static void main(String[] args) {
        List<Number> numbers = Arrays.asList(1,2,3,4,5);
        numbers.forEach(System.out::println);
        swapHelper(numbers, 1, 4);
        swap(numbers, 2, 3);
        numbers.forEach(System.out::println);
    }
}
