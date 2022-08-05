import java.util.Scanner;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {

        int menuItem = -1;

        while(menuItem !=0) {
            menuItem = menu();

            switch(menuItem) {

                case 1:
                    showList();
                    break;

                case 2:
                    addItem();
                    break;

                case 3:
                    removeItem();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Enter a valid option");
            }

        }
    }

    public static int menu() {

        int choice;

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Main Menu");
        System.out.println();
        System.out.println("0. Exit the program");
        System.out.println("1. Display to-do list");
        System.out.println("2. Add item to list");
        System.out.println("3. Remove item from list");
        System.out.println();
        System.out.print("Enter choice: ");
        choice = keyboard.nextInt();

        return choice;
    }

    public static void showList() {

        System.out.println("To-Do List");

        Scanner input = new Scanner(System.in);
        String line;
        int number = 1;

        while (input.hasNextLine()){
            line = input.nextLine();
            System.out.println(number + " ");
            System.out.println(line);
            ++number;
        }

        System.out.println();


    }

    public static void addItem() {

        System.out.println("Add Item");

        Scanner input = new Scanner(System.in);
        System.out.println("Enter an item: ");
        String item = input.nextLine();
        System.out.println(item);


    }

    public static void removeItem() {

        int choice;
        showList();

        Scanner input = new Scanner(System.in);
        System.out.println("What do you want to remove?");
        choice = input.nextInt();

        ArrayList<String> items = new ArrayList<String>();
        int number = 1;

        Scanner input2 = new Scanner(System.in);
        String item;

        while (input2.hasNextLine()) {
            item = input2.nextLine();

            if (number != choice)
                items.add(item);

            ++number;
        }

        for(int i = 0; i < items.size(); i++)
            System.out.println(items.get(i));

    }
}
