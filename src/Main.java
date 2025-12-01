import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {

    // Random object
    static Random random = new Random();

    static Scanner scanner = new Scanner(System.in);
    static String pickItem;

    // Creation of enemy and player
    static Enemy enemy1 = new Enemy(36, 80);
    static Player player = new Player("lone-wolf", 40, 50, 65, 0, 0);

    // Creation of rooms
    static Room room1 = new Room("Dungeon", true, false, "");
    static Room room2 = new Room("Throne Room", false, true, " welcomes you, a strange aura fills your lungs... You are confused and bleeding");
    static Room room3 = new Room("Old Warehouse", true, true, " has been entered by an entity, was it you? or something else?... who knows");
    static Room room4 = new Room("Hallway", true, false, "Long hallway stares at you, giant silhouette is lingering in the back, it draws its sword, you prepare to attack");

    // Room map? WIP
    static Room[] roomMap =
            {
                    room1,
                    room2,
                    room3,
                    room4
            };

    // Items that the player is able to get
    static Item[] items =
            {
                    new Item("Amulet", 30,0,true),
                    new Item("Bracelet", 15,10,false),
                    new Item("Hat", 20,0,false),
                    new Item("Sword", 0, 40, true),
            };

    // Inventory of the player
    static ArrayList<Object> inventory = new ArrayList<>();

    // Enemies slain counter
    static int numOfEnemiesSlain = 0;

    // Item drop, if 'true', item drops else no drop
    static boolean itemDrop;

    // if drop chance is 50 or higher, item drops
    static int itemDropChance;

    public static void main(String[] args) {

        // Welcoming message to the game
        System.out.println("=================");
        System.out.println("Sekiro No Shadows");
        System.out.println("=================");
        System.out.println();

        // Room exploration loop?
        for(int i = 0; i < roomMap.length; i++) {
            intro(roomMap[i]);
        }

        scanner.close();
        exit(1);

    }

    // Welcome message and intro to the game
    // Accepting a room variable that determines which room the player wakes up in.
    static void intro(Room room) {
        // room object is used
        System.out.println(room.name + room.description);

        if(room.enemyInside) {
            initiateCombat();
        } else {
            System.out.println("There appear to be no enemies near you right now...");
            System.out.println("What a relief *sigh*");
        }
        if(room.itemInside) {
            itemEncounter();
        }
    }

    // Combat Loop
    static void initiateCombat() {
        System.out.println("Enemy appeared..");
        System.out.println();
        System.out.println(enemy1.enemyInfo());
        System.out.println();
        System.out.println(player.playerInfo());
        System.out.println();
        do {
            sleep(3000);
            System.out.println("Enemy attacked for: " + enemyAttack());
            sleep(3000);
            System.out.println("You got hit...");
            sleep(1000);
            player.hp -= enemyAttack();
            if(player.hp <= 0) {
                System.out.println("You died!!!!");
                System.out.println("Game Over!");
                exit(1);
            }
            System.out.println("Your HP is now: " + player.hp);
            sleep(3000);
            System.out.println("You attack for: " + player.attack);
            sleep(3000);
            System.out.println("Enemy got hit..."); // make this a random boolean if you hit or miss
            sleep(1000);
            enemy1.hp -= player.attack;
            if(enemy1.hp <= 0) {
                System.out.println("Enemy has been slayed");
                itemDropChance = random.nextInt(0, 101);
                if(itemDropChance > 50) {
                    // Adding random item from Items to player's inventory
                    itemDrop = true;
                    itemEncounter();
                }
                else {
                    itemDrop = false;
                    System.out.println("No loot...");
                }
                numOfEnemiesSlain++;
                break;
            }
        } while(player.hp > 0 || enemy1.hp > 0);
    }

    static void itemEncounter() {
        System.out.println("There is an item laying on the ground");
        sleep(1000);
        System.out.print("Do you wish to pick it up? (y/n): ");
        pickItem = scanner.nextLine().toLowerCase();
        if(pickItem.equals("y")) {
            inventory.add(items[random.nextInt(items.length)].type);
            System.out.println("You picked up: " + inventory.get(0));
            if(inventory.get(0).equals("Amulet")) {
                player.hp += items[0].bonusHp;
                System.out.println(inventory.get(0) + " Gave you +30 HP");
                System.out.println("Player HP: " + player.hp);
            }
            if(inventory.get(0).equals("Bracelet")) {
                player.hp += items[1].bonusHp;
                player.attack += items[1].bonusAttack;
                System.out.println(inventory.get(0) + " Gave you +15 HP & +10 Attack");
                System.out.println("Player HP: " + player.hp);
                System.out.println("Player Attack: " + player.attack);
            }
            if(inventory.get(0).equals("Hat")) {
                player.hp += items[2].bonusHp;
                System.out.println(inventory.get(0) + " Gave you +20 HP");
                System.out.println("Player HP: " + player.hp);
            }
            if(inventory.get(0).equals("Sword")) {
                player.hp += items[3].bonusAttack;
                System.out.println(inventory.get(0) + " Gave you +40 Attack");
                System.out.println("Player Attack: " + player.attack);
            }
        }
        if(pickItem.equals("n")) {
            System.out.println("You decided to not pick up the item");
            System.out.println(inventory);
        }
    }

    // returns the attack of an enemy
    static int enemyAttack() {
        return random.nextInt(25, enemy1.attack);
    }

    // Redundant, delete?
    static int enemyHp() {

        return enemy1.hp;
    }

    // sleep method
    static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
