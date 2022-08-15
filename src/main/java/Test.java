import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {

        int menuTask = -1;

        //solange MenuItem ungleich null ist, also -1 läuft die WhileSchleife
        while (menuTask != 0) {
            menuTask = menu();

            //Switch Anweisung
            switch (menuTask) {

                //Case "1" zeige Liste
                case 1:
                    showTask();
                    break;

                //Case "2" füge Task hinzu
                case 2:
                    addTask();
                    break;

                //Case "3" lösche Task
                case 3:
                    removeTask();
                    break;

                //Case "0" lösche Task
                case 0:
                    break;

                //Default-Wert "Was möchten Sie machen?"
                default:
                    System.out.println("Was möchten Sie machen?: ");
            }

        }
    }

    public static int menu() {

        int choice;

        //Scanner -> User Input
        Scanner keyboard = new Scanner(System.in);
        //Ausgabe in Konsole
        System.out.println("Menü");
        System.out.println();
        System.out.println("0. Verlassse das Programm");
        System.out.println("1. Zeige die Aufgabenliste");
        System.out.println("2. Füge eine Task hinzu");
        System.out.println("3. Lösche eine Task von der Liste");
        System.out.println();
        System.out.print("Was möchten Sie machen?: ");
        choice = keyboard.nextInt();
        return choice;
    }
    //Methode um TaskListe zu zeigen
    public static void showTask() {

        System.out.println("To-Do List");

        //User Input
        Scanner input = new Scanner(System.in);
        String line;
        int number = 1;

        //"GUI" für "showList"
        while (input.hasNextLine()) {
            line = input.nextLine();
            System.out.println(number + " ");
            System.out.println(line);
            ++number;
        }
        System.out.println();
    }

    //Methode um Task anzulegen
    public static void addTask() {
        //Konsolenausgabe
        System.out.println("Füge Task hinzu:");
        //User input
        Scanner input = new Scanner(System.in);
        //Task objekt erstellen
        Task task = new Task();

        //Wenn Name der Task null ist dann
        if (task.getName() == null) {
            System.out.println("Gebe Task ein: ");
            //gebe Namen ein -> nextLine bis Zeilenende / next bis Leerzeichen
            String name = input.nextLine();
            //Namen setten
            task.setName(name);
        }

        if (task.getDescription() == null) {
            System.out.println("Gebe Task Beschreibung ein: ");
            String description = input.nextLine();
            task.setName(description);
        }

        if (task.getCategory() == null) {
            System.out.println("Gebe Task Kategeorie ein: ");
            String category = input.nextLine();
            task.setCategory(category);
        }

        if (task.getCategory() == null) {
            System.out.println("Gebe Task Kategeorie ein: ");
            String category = input.nextLine();
            task.setCategory(category);
        }
        //Solange task  kein Rating hat, addRating
        while (task.getRating()==null){
            addRating(task, input);
        }

    }

    //Methode um Rating zu setzen - Enum
    public static void addRating (Task task, Scanner input){
            System.out.println("Gebe Task Rating ein:\n 1:easy \n 2:middle \n 3:hard");
            String rating = input.nextLine();
            if (Objects.equals(rating, "1")) {
                task.setRating(Rating.easy);
            }
            if (Objects.equals(rating, "2")) {
                task.setRating(Rating.middle);
            }
            if (Objects.equals(rating, "3")) {
                task.setRating(Rating.hard);
            }
    }

    //Task löschen
    public static void removeTask() {

        //Task anzeigen
        int choice;
        showTask();

        Scanner input = new Scanner(System.in);
        System.out.println("Was möchtest du löschen?");
        //Eingabe zum löschen einer Task
        choice = input.nextInt();

        //neue Liste erstellt, um "gelöschte Task" in der neuen Liste auszugrenzen
        ArrayList<String> items = new ArrayList<>();
        int number = 1;
        Scanner input2 = new Scanner(System.in);
        String item;

        while (input2.hasNextLine()) {
            item = input2.nextLine();

            if (number != choice)
                items.add(item);

            ++number;
        }

        //auslesen
        for (int i = 0; i < items.size(); i++)
            System.out.println(items.get(i));

    }
}
