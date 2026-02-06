import java.util.Random;
import java.util.Scanner;

public class FirstPersonDungeon {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    
    static int hp = 1312;
    static int gold =1312;

    public static void main(String[] args) {
        printSlow("🏰 Willkommen im First-Person-Dungeon! 🏰\n\n", 40);
        storyStart();
    }

    // --- Story Beginn ---
    static void storyStart() {
        printSlow("Du betrittst einen dunklen Gang. Vor dir teilt sich der Weg.\n", 40);
        firstChoice();
    }

    // --- Erste Entscheidung ---
    static void firstChoice() {
        printSlow("Links siehst du ein schwaches Licht, rechts hörst du ein Knurren.\n", 40);
        printSlow("Wähle: (l) links oder (r) rechts\n", 40);
        char choice = scanner.next().charAt(0);

        if (choice == 'l') {
            printSlow("\nDu gehst den linken Gang.\n", 40);
            showFirstPerson("Treasure");
            findTreasure();
        } else if (choice == 'r') {
            printSlow("\nDu gehst den rechten Gang.\n", 40);
            showFirstPerson("Monster");
            encounterMonster();
        } else {
            printSlow("Ungültige Wahl! Versuche es erneut.\n", 40);
            firstChoice();
        }
    }

    // --- Nächste Wahl ---
    static void nextChoice() {
        printSlow("\nDer Gang teilt sich erneut.\n", 40);
        printSlow("Wähle: (l) links oder (r) rechts\n", 40);
        char choice = scanner.next().charAt(0);

        if (choice == 'l') {
            printSlow("\nEin riesiger Drache taucht auf!\n", 40);
            showFirstPerson("Dragon");
            encounterDragon();
        } else if (choice == 'r') {
            printSlow("\nDu siehst das Licht des Ausgangs!\n", 40);
            showFirstPerson("Exit");
            printSlow("🎉 Glückwunsch! Du entkommst mit " + gold + " Gold!\n", 40);
        } else {
            printSlow("Ungültige Wahl! Versuche es erneut.\n", 40);
            nextChoice();
        }
    }

    // --- Monster-Kampf ---
    static void encounterMonster() {
        int monsterHP = 12;
        printSlow("\n👹 Ein Monster erscheint!\n", 40);
        while (monsterHP > 0 && hp > 0) {
            printSlow("Aktion wählen: (a) Angriff  (h) Heilung\n", 40);
            char action = scanner.next().charAt(0);

            if (action == 'a') {
                int dmg = random.nextInt(6) + 1;
                monsterHP -= dmg;
                animateText("Du schlägst das Monster für " + dmg + " Schaden!", 50);
            } else if (action == 'h') {
                int heal = random.nextInt(4) + 1;
                hp += heal;
                animateText("Du heilst dich um " + heal + " HP!", 50);
            }

            if (monsterHP > 0) {
                int dmg = random.nextInt(4) + 1;
                hp -= dmg;
                animateText("Monster greift an! Du erleidest " + dmg + " Schaden!", 50);
            }

            animateText("Monster HP: " + Math.max(0, monsterHP) + " | Deine HP: " + hpBar(), 50);
        }

        if (hp > 0) {
            int loot = random.nextInt(15) + 5;
            gold += loot;
            printSlow("\nMonster besiegt! 💰 Gold +" + loot + "\n", 40);
            nextChoice();
        } else {
            printSlow("\n💀 Du wurdest vom Monster besiegt! Game Over.\n", 40);
        }
    }

    // --- Drachenkampf ---
    static void encounterDragon() {
        int dragonHP = 25;
        printSlow("\n🐉 Ein mächtiger Drache erscheint!\n", 40);
        while (dragonHP > 0 && hp > 0) {
            printSlow("Aktion wählen: (a) Angriff  (h) Heilung\n", 40);
            char action = scanner.next().charAt(0);

            if (action == 'a') {
                int dmg = random.nextInt(8) + 1;
                dragonHP -= dmg;
                animateText("Du triffst den Drachen für " + dmg + " Schaden!", 50);
            } else if (action == 'h') {
                int heal = random.nextInt(5) + 1;
                hp += heal;
                animateText("Du heilst dich um " + heal + " HP!", 50);
            }

            if (dragonHP > 0) {
                int dmg = random.nextInt(6) + 1;
                hp -= dmg;
                animateText("Drache schlägt zurück! Du erleidest " + dmg + " Schaden!", 50);
            }

            animateText("Drache HP: " + Math.max(0, dragonHP) + " | Deine HP: " + hpBar(), 50);
        }

        if (hp > 0) {
            gold += 50;
            printSlow("\nDu besiegst den Drachen! 💰 Gold +50\n", 40);
            printSlow("🎉 Du hast das Dungeon-Abenteuer überlebt mit " + gold + " Gold!\n", 40);
        } else {
            printSlow("\n💀 Du wurdest vom Drachen besiegt! Game Over.\n", 40);
        }
    }

    // --- Schatz finden ---
    static void findTreasure() {
        int loot = random.nextInt(20) + 10;
        gold += loot;
        printSlow("\n💰 Du findest einen Schatz! Gold +" + loot + "\n", 40);
        nextChoice();
    }

    // --- HP-Balken ---
    static String hpBar() {
        int total = 20;
        int filled = Math.max(0, hp);
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < total; i++) {
            bar.append(i < filled ? "█" : " ");
        }
        return bar + "]";
    }

    // --- Text langsam ausgeben ---
    static void printSlow(String text, int delayMillis) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try { Thread.sleep(delayMillis); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
        System.out.println();
    }

    static void animateText(String text, int delayMillis) {
        printSlow(text, delayMillis);
    }

    // --- First-Person-Visualisierung ---
    static void showFirstPerson(String object) {
        System.out.println("\n");
        System.out.println("   ╔═════╗");
        System.out.println("   ║     ║");
        switch (object) {
            case "Monster" -> System.out.println("   ║  👹  ║");
            case "Dragon"  -> System.out.println("   ║  🐉  ║");
            case "Treasure"-> System.out.println("   ║  💰  ║");
            case "Exit"    -> System.out.println("   ║  🚪  ║");
            default       -> System.out.println("   ║     ║");
        }
        System.out.println("   ║     ║");
        System.out.println("   ╚═════╝");
        System.out.println();
    }
}
