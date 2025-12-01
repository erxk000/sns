public class Room {

    String name;
    String description;
    boolean enemyInside;
    boolean itemInside;

    Room(String name, boolean enemyInside, boolean itemInside, String description) {
        this.name = name;
        this.enemyInside = enemyInside;
        this.itemInside = itemInside;
        this.description = description;
    }
}
