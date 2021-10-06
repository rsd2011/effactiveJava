package main.java.io.rsd.effectivejava.homework;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static void printMenu() {
        System.out.println("⎡                    ⎤");
        System.out.println("⎢ 1: paging, 2: quit ⎥");
        System.out.println("⎣                    ⎦");
        System.out.print("⟶ ");
    }

    private static void printPagingQuestion1() {
        System.out.println("현재 페이지");
        System.out.print("⟶ ");
    }

    private static void printPagingQuestion2() {
        System.out.println("페이지당 ROW 수");
        System.out.print("⟶ ");
    }

    private static void printPagingQuestion3() {
        System.out.println("총 ROW 수");
        System.out.print("⟶ ");
    }

    private static int pagingQuestion1(Scanner scanner) {
        printPagingQuestion1();
        int input = scanner.nextInt();
        if (input <= 0)
            throw new IllegalArgumentException("1이상의 정수만 입력할 수 있습니다.");
        return input;
    }

    private static int pagingQuestion2(Scanner scanner) {
        printPagingQuestion2();
        int input = scanner.nextInt();
        if (input <= 0)
            throw new IllegalArgumentException("1이상의 정수만 입력할 수 있습니다.");
        return input;
    }

    private static int pagingQuestion3(Scanner scanner) {
        printPagingQuestion3();
        int input = scanner.nextInt();
        if (input <= 0)
            throw new IllegalArgumentException("1이상의 정수만 입력할 수 있습니다.");
        return input;
    }

    private enum Menu {
        PAGING {
            @Override
            public void run(Scanner scanner) {
               final int currentPage = pagingQuestion1(scanner);
               final int pageRowCount = pagingQuestion2(scanner);
               final int totalRowCount = pagingQuestion3(scanner);
               PagingResult result = new Paging(currentPage, pageRowCount, totalRowCount).paging();
               result.print();
            }
        },
        QUIT {
            @Override
            public void run(Scanner scanner) {
                throw new IllegalArgumentException("잘못된 요청 입니다.");
            }
        };
        private static final Map<Integer, Menu> MENU_MAP = Stream.of(values())
                .collect(Collectors.toMap(m -> m.ordinal() + 1, Function.identity()));

        public static Menu from(int input) {
            return Optional.ofNullable(MENU_MAP.get(input))
                    .orElseThrow(IllegalArgumentException::new);
        }

        public abstract void run(Scanner scanner);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            int input;
            try {
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("메뉴의 숫자만 입력할 수 있습니다.");
                scanner = new Scanner(System.in);
                continue;
            }
            Menu menu;
            try {
                menu = Menu.from(input);
            } catch (IllegalArgumentException e) {
                System.err.println("메뉴의 숫자만 입력할 수 있습니다");
                continue;
            }

            if (menu == Menu.QUIT) break;
            try {
                menu.run(scanner);
            } catch (InputMismatchException e) {
                System.err.println("정수만 입력할 수 있습니다.");
                scanner = new Scanner(System.in);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
