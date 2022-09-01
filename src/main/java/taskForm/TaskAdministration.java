package taskForm;
import model.Rating;
import model.Task;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class TaskAdministration {
    //Liste für die app.Task
    static List<Task> taskData = new ArrayList<>();
    //atomarische Generierung der ID, neues Objekt wird generiert und hochgezählt -> initialer Wert 0
    private static final AtomicInteger count = new AtomicInteger(0);

    public static void run() {
        int menuTask = -1;
        //solange MenuItem ungleich null ist, also -1 läuft die WhileSchleife
        while (menuTask != 0) {
            menuTask = menu();

            //Switch Anweisung
            switch (menuTask) {
                //Case "1" add app.Task
                case 1:
                    addTask();
                    break;
                //Case "2" show app.Task
                case 2:
                    if (taskData.isEmpty()) {
                        System.out.println("Die ist Funktion ist nicht vefügbar");
                        break;
                    }
                    showTask();
                    break;

                //Case "3" lösche app.Task
                case 3:
                    if (taskData.isEmpty()) {
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
                    if (taskData.isEmpty()) {
                        System.out.println("Bitte gebe einen gültigen Wert ein");
                        break;
                    }
            }
        }
    }

    //Menü
    public static int menu() {
        int choice;
        //Scanner -> User Input
        Scanner keyboard = new Scanner(System.in);
        //Ausgabe in Konsole
        System.out.println("Menü");
        System.out.println();
        System.out.println("0. Verlassse das Programm");
        System.out.println("1. Füge eine app.Task hinzu");

        //Wenn es app.Task gibt
        if (!taskData.isEmpty()) {
            //Ausgabe
            System.out.println("2. Zeige die Aufgabenliste");
        }
        //Wenn es app.Task gibt
        if (!taskData.isEmpty()) {
            //Ausgabe
            System.out.println("3. Lösche eine app.Task von der Liste\n");
        }
        //Ausgabe
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
        if (taskData.isEmpty()) {
            System.out.println("Keine app.Task vorhanden");
        }
    }

    //Methode um app.Task anzulegen
    public static void addTask() {
        //Konsolenausgabe
        System.out.println("Füge app.Task hinzu:");
        //User input
        Scanner input = new Scanner(System.in);
        //app.Task objekt erstellen
        Task task = new Task();

        //id angelegt in der Konsole
        if (task.getId() == 0) {
            //ID wird generiert
            int id = count.incrementAndGet();
            //ID wird gesetzt
            task.setId(id);
        }
        //Wenn Name der app.Task null ist dann
        //schau ich mir nochmal
        if (task.getName() == null) {
            System.out.println("Gebe app.Task ein: ");
            //gebe Namen ein -> nextLine bis Zeilenende / next bis Leerzeichen
            String name = input.nextLine();
            //Namen setten
            task.setName(name);
        }

        if (task.getDescription() == null) {
            System.out.println("Gebe app.Task Beschreibung ein: ");
            String description = input.nextLine();
            task.setDescription(description);
        }

        if (task.getCategory() == null) {
            System.out.println("Gebe app.Task Kategeorie ein: ");
            String category = input.nextLine();
            task.setCategory(category);
        }

        //Solange task  kein app.Rating hat, addRating
        while (task.getRating() == null) {
            addRating(task, input);
        }
        //Eingaben werden der Liste taskData zugewiesen
        taskData.add(task);
    }

    //Methode um app.Rating zu setzen - Enum
    public static void addRating(Task task, Scanner input) {
        System.out.println("Gebe app.Task app.Rating ein:\n 1:easy \n 2:middle \n 3:hard");
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

    //app.Task löschen
    public static void removeTask() {

        //app.Task anzeigen
        int choice;
        showTask();
        //Scanner Objekt erzeugen
        Scanner input = new Scanner(System.in);
        System.out.println("Was möchtest du löschen? (Gebe bitte die ID ein)");
        //Eingabe zum löschen einer app.Task
        choice = input.nextInt();

        //wenn keine Task ausgewählt
        Task selectedTask = null;
        //dann suche nach Task
        for (Task task : taskData) {
            //anhand der ID
            if (task.getId() == choice) {
                selectedTask = task;
            } else {
                System.out.println("Die ID existiert nicht mehr");
            }
        }
        //Wenn Task gefunden
        if (selectedTask != null) {
            //dann lösche Task
            taskData.remove(selectedTask);
            //und gebe aus
            System.out.println("Die app.Task wurde erfolgreich gelöscht");
        }
    }
}
