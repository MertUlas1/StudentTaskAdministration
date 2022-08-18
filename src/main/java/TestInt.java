import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class TestInt {
    //umsortieren und ausgeblenete Menüpunkte Zugriff verrweigern
    static ArrayList<Task> taskData = new ArrayList<>();
    static int taskDataId;
    //atomarische Generierung der ID, neues Objekt wird generiert und hochgezählt -> initialer Wert 0
    private static int count = 0;

    public static void main(String[] args) {

        int menuTask = -1;

        //solange MenuItem ungleich null ist, also -1 läuft die WhileSchleife
        while (menuTask != 0) {
            menuTask = menu();

            //Switch Anweisung
            switch (menuTask) {

                //Case "1" zeige Liste
                case 1:
                    addTask();
                    break;

                //Case "2" füge Task hinzu

                case 2:
                    if (taskData.isEmpty()){
                        System.out.println("Die ist Funktion ist nicht vefügbar");
                        break;
                    }
                    showTask();
                    break;

                //Case "3" lösche Task
                case 3:
                    if (taskData.isEmpty()){
                        System.out.println("Die ist Funktion ist nicht vefügbar, gebe einen gültigen Wert ein");
                        break;
                    }
                    removeTask();
                    break;

                //Case "0"
                case 0:
                    break;

                //Default-Wert "Was möchten Sie machen?"
                default:
                    if (taskData.isEmpty()){
                        System.out.println("Bitte gebe einen gültigen Wert ein");
                        break;
                    }
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
        System.out.println("1. Füge eine Task hinzu");

        if (!taskData.isEmpty()){
            System.out.println("2. Zeige die Aufgabenliste");
        }

        if (!taskData.isEmpty()){
            System.out.println("3. Lösche eine Task von der Liste");
        }

        System.out.println();
        System.out.print("Was möchten Sie machen?: ");
        choice = keyboard.nextInt();
        return choice;
    }

    //Methode um TaskListe zu zeigen
    public static void showTask() {
        //Auslesen der taskData Liste
        for (Task task : taskData) {
            System.out.println(task);
        }
        if (taskData.isEmpty()){
            System.out.println("Keine Task vorhanden");
        }
    }

    //Methode um Task anzulegen
    public static void addTask() {
        //Konsolenausgabe
        System.out.println("Füge Task hinzu:");
        //User input
        Scanner input = new Scanner(System.in);
        //Task objekt erstellen
        Task task = new Task();

        //id angelegt in der Konsole
        if (task.getId() == 0) {
            //int id = (int) (Math.random()*1000); -> diese Option hätte zu Duplikaten der IDs geführt
            //ID wird generiert
            int id = count++;
            //ID wird gesetzt
            task.setId(id);
        }
        //Wenn Name der Task null ist dann
        //schau ich mir nochmal
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
            task.setDescription(description);
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
        //Eingaben werden der Liste taskData zugewiesen
        taskData.add(task);
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
        System.out.println("Was möchtest du löschen? (Gebe bitte die ID ein)");
        //Eingabe zum löschen einer Task
        choice = input.nextInt();

        Task selectedTask = null;
        for (Task task : taskData){
            if (task.getId() == choice){
                selectedTask=task;
            }
            else {
                System.out.println("Die ID existiert nicht mehr");
            }
        }
        //z.B. Task mit ID 4
        if(selectedTask != null ){
            taskData.remove(selectedTask);
            System.out.println("Die Task wurde erfolgreich gelöscht");
        }
    }
}
