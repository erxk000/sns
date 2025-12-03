import java.util.Random;

public class Enemy {

    Random random = new Random();

    boolean itemDrop = random.nextInt(100) < 50;
    int attack;
    int hp;

    Enemy(int attack, int hp) {
        this.attack = attack;
        this.hp = hp;
    }

    String enemyInfo() {
        return "Enemy\nHP: " + this.hp;
    }
}
