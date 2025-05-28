import Graphics.Frame;

public class Main {
    public static void main(String[] args) {

        try {
            new Frame();
        } catch (Exception e) {
            System.out.println("An error appeared while playing a game.");
        }
    }
}