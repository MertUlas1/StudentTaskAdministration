import java.util.Scanner;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {


        int menuTask = -1;

        //solange MenuItem ungleich null ist, also -1 läuft die WhileSchleife
        while(menuTask !=0) {
            menuTask = menu();

            //Switch Anweisung
            switch(menuTask) {

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
        while (input.hasNextLine()){
            line = input.nextLine();
            System.out.println(number + " ");
            System.out.println(line);
            ++number;
        }

        System.out.println();


    }

    //Methode um Task anzulegen
    public static void addTask() {

        System.out.println("Füge Task hinzu:");

        Scanner input = new Scanner(System.in);
        System.out.println("Gebe Task ein: ");
        String item = input.nextLine();
        System.out.println(item);


    }

    //Task löschen
    public static void removeTask() {

        int choice;
        showTask();

        Scanner input = new Scanner(System.in);
        System.out.println("Was möchtest du löschen?");
        choice = input.nextInt();

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

        for(int i = 0; i < items.size(); i++)
            System.out.println(items.get(i));

    }
}
