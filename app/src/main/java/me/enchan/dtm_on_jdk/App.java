package me.enchan.dtm_on_jdk;

public class App {

    private void run() throws Exception {
        System.out.println("hello, java");
    }

    public static void main(String[] args) {
        App app = new App();
        try {
            app.run();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
