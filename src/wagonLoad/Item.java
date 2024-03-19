import java.util.ArrayList;
import java.util.Scanner;

class Item {
    private String name;
    private static int totalWeight = 0;
    private int itemWeight = 0;
    private int cost = 0;

    public Item(String name) {
        this.name = name;
    }

    public Item(int itemWeight) {
        this.itemWeight = itemWeight;
    }

    public String getName() {
        return name;
    }

    public int getItemWeight() {
        return this.itemWeight;
    }

    public void setItemWeight(int itemWeight) {
        this.itemWeight = itemWeight;
    }

    public static int getTotalWeight() {
        return totalWeight;
    }
}

public class MP3 {
    public static void main(String[] args) {
        ArrayList<Item> items = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        items.add(new Item("Item 1"));
        if (choice.equals) ("y") 
        System.out.println("+Weight")
        
        items.add(new Item("Item 2"));
        items.add(new Item("Item 3"));

        for (Item item : items) {
            System.out.print("Would you like to take " + item.getName() + " with you? (y/n): ");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("y")) {
                System.out.println("You took " + item.getName() + " with you.");
            } else if (choice.equals("n")) {
                System.out.println("You chose not to take " + item.getName() + " with you.");
            } else {
                System.out.println("Invalid choice. Please enter 'y' for Yes or 'n' for No.");
            }
        }

        scanner.close();
    }
}
