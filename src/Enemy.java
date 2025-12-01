public class Enemy {


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
