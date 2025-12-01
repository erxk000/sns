public class Item {

    String type;
    int bonusHp;
    int bonusAttack;
    boolean curse;

    Item(String type, int bonusHp, int bonusAttack, boolean curse) {
        this.type = type;
        this.bonusHp = bonusHp;
        this.bonusAttack = bonusAttack;
        this.curse = curse;
    }
}
