package main.java.io.rsd.effectivejava.item8;

public class SampleResource implements AutoCloseable {
    @Override
    public void close() throws RuntimeException {
        System.out.println("close");
    }

    public void hello() {
        System.out.println("hello");
    }

    public static void main(String[] args) {
        try (SampleResource resource = new SampleResource()) {
            resource.hello(); // 리소스 사용
        }
    }
}