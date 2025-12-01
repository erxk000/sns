public class Player {

    String name;
    int attack;
    int defense;
    int hp;
    int level;
    int exp;

    Player(String name, int attack, int defense, int hp, int level, int exp) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.hp = hp;
        this.level = level;
        this.exp = exp;
    }

    String playerInfo() {
        return "Player \n" + "Attack: " + this.attack + "\nDefense: " + this.defense + "\nHP: " + this.hp;
    }

}
