import org.w3c.dom.ls.LSOutput;

import java.util.*;

import static java.lang.System.exit;

public class Game {

    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);

    private HashMap<String, Room> roomsMap = new HashMap<>();
    private Player player;
    private Enemy enemy1;

    String pickItem;
    String moveChoice;
    int numOfEnemiesSlain = 0;
    String UIchoice;

    Item[] items =
            {
                    new Item("Amulet", 30,0,true),
                    new Item("Bracelet", 15,10,false),
                    new Item("Hat", 20,0,false),
                    new Item("Sword", 0, 40, true),
            };

    ArrayList<Object> inventory = new ArrayList<>();

    // Game class Constructor
    public Game() {
        createRooms();
        enemy1 = new Enemy(36, 80);
        player = new Player("lone-wolf", 40, 50, 65, 0, 0);
    }

    private void createRooms() {

        Room dungeon = new Room("Dungeon",", a cold unwelcoming place crawling & festering insect, might be dangerous");
        Room throneRoom = new Room("Throne Room"," welcomes you, a strange aura fills your lungs... You are confused and bleeding");
        Room warehouse = new Room("Old Warehouse"," has been entered by an entity, was it you? or something else?... who knows");
        Room hallway = new Room("Hallway"," stares at you, giant silhouette is lingering in the back, it draws its sword, you prepare to attack");

        roomsMap.put("dungeon", dungeon);
        roomsMap.put("throneRoom", throneRoom);
        roomsMap.put("warehouse", warehouse);
        roomsMap.put("hallway", hallway);
    }

    public void playersLocation(Room room) {
        // room object is used
        System.out.println(room.name + room.description);
        System.out.println(player.playerInfo());

        if(room.enemyInside) {
            System.out.println("Enemy appeared..");
            System.out.println();
            System.out.println(enemy1.enemyInfo());
            System.out.println();
            String menuChoice = displayUI(UIchoice);
            if(menuChoice.equals("a")) {
                do {
                    initiateCombat();

                } while (enemy1.hp > 0);
                // Todo if enemy is defeated set enemyInside to false, this is workaround for now
            } else if(UIchoice.equals("i")) {
                System.out.println(inventory);
                scanner.nextLine();
                displayUI(UIchoice);
            }
        } else {
            System.out.println("There appear to be no enemies near you right now...");
            System.out.println("What a relief *sigh*");
        }
        if(room.itemInside) {
            itemEncounter();
        } else {
            System.out.println("No loot... ");
        }
    }

    public void initiateCombat() {
        // Assigning the value returned fromm the enemyAttacks() method to the new variable
        int enemyAttackNumber;

        // Combat stats overview

        // Todo game fighting mechanics
        do {
            enemyAttackNumber = enemyAttacks();
            sleep(3000);
            System.out.println("Enemy attacked for: " + enemyAttackNumber);
            sleep(3000);
            System.out.println("You got hit...");
            sleep(1000);
            player.hp -= enemyAttackNumber;
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
                //itemDropChance = random.nextInt(0, 101);
                if(enemy1.itemDrop) {
                    // Adding random item from Items to player's inventory
                    itemEncounter();
                }
                numOfEnemiesSlain++;
                break;
            }
        } while(player.hp > 0); // && enemy1.hp > 0
    }

    public void itemEncounter() {
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
    public int enemyAttacks() {
        int attackNumber = random.nextInt(25, enemy1.attack);
        return attackNumber;
    }

    // resets the enemy health
    public void resetEnemyHealth() {
        enemy1.hp = 80;
    };

    // sleep method
    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        // Todo make this a loop?
        playersLocation(roomsMap.get("dungeon"));
        System.out.print("Do you wish to proceed to the next room? (y/n): ");
        moveChoice = scanner.nextLine().toLowerCase();
        resetEnemyHealth();
        if(moveChoice.equals("y")) {
            playersLocation(roomsMap.get("throneRoom"));
            System.out.print("Do you wish to proceed to the next room? (y/n): ");
            moveChoice = scanner.nextLine().toLowerCase();
            if(moveChoice.equals("y")) {
                playersLocation(roomsMap.get("hallway"));
            } else if(moveChoice.equals("n")) {
                playersLocation(roomsMap.get("throneRoom"));
            }
        } else if(moveChoice.equals("n")) {
            playersLocation(roomsMap.get("dungeon"));
        }
        scanner.close();
    }

    //Todo Implement!!!!
    public String displayUI(String levelInput) {
        System.out.println("======================");
        System.out.println(
                """
                'A' - Attack
                'I' - Show Inventory """);
        System.out.println("======================");
        System.out.print("Please choose your next move: ");
        do {
            return UIchoice = scanner.nextLine().toLowerCase();
        } while(!UIchoice.equals("a") && !UIchoice.equals("i"));

//        switch(levelInput) {
//            case "a" -> initiateCombat();
//            case "i" -> System.out.println(inventory);
//            default -> System.out.println("invalid input");
//        }

        //return levelInput;
    }

}
