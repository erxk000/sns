import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();

        // Welcoming message to the game
        System.out.println("=================");
        System.out.println("Sekiro No Shadows");
        System.out.println("=================");
        System.out.println();

        game.start();
        // test

        exit(1);
    }
}
