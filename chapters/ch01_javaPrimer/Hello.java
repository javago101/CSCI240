package ch01_javaPrimer;

public class Hello {
    private Hello() {
    }

    public static void main(String[] args) {
        System.out.println("Environment is ready!");
    }

    public static Hello createHello() {
        return new Hello();
    }
}