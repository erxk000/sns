import java.util.Random;

public class Room {

    Random random = new Random();

    String name;
    String description;
    boolean enemyInside = random.nextInt(100) < 30;
    boolean itemInside = random.nextInt(100) < 50;

    Room(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
