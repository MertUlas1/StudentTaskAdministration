package taskForm;

import model.Rating;
import model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskAdministrationNonStatic {
    //Liste taskData wird erzeugt
    private static final List<Task> taskData = new ArrayList<>();
    //atomarische Generierung der ID, neues Objekt wird generiert und hochgezählt -> initialer Wert 0
    private final AtomicInteger count = new AtomicInteger(0);

    public void run() {
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
                //Case "2" zeige showTask, wenn eine vorhanden ist
                case 2:
                    if (hasTask()) {
                        showTask();
                        break;
                    }
                    System.out.println("Die ist Funktion ist nicht vefügbar");
                    break;
                //Case "3" lösche app.Task
                case 3:
                    if (hasTask()) {
                        removeTask();
                        break;
                    }
                    System.out.println("Die ist Funktion ist nicht vefügbar, gebe einen gültigen Wert ein");
                    break;
                //Case "0"
                case 0:
                    break;
                //Default-Wert "Was möchten Sie machen?"
                default:
                    if (!hasTask()) {
                        System.out.println("Bitte gebe einen gültigen Wert ein");
                        break;
                    }
            }
        }
    }

    private int menu() {
        //Scanner -> User Input
        Scanner keyboard = new Scanner(System.in);
        //Ausgabe in Konsole
        System.out.println("Menü\n");
        System.out.println("0. Verlassse das Programm");
        System.out.println("1. Füge eine app.Task hinzu");

        //IF-Statements um Ausgaben in der Konsole zu managen
        //wenn es eine app.Task gibt..
        if (hasTask()) {
            //.. dann gebe aus
            System.out.println("2. Zeige die Aufgabenliste");
        }

        if (hasTask()) {
            System.out.println("3. Lösche eine app.Task von der Liste\n");
        }
        System.out.print("Was möchten Sie machen?: ");
        return keyboard.nextInt();

    }
    public static List<Task> getAllTasks(){
        return taskData;
    }


    //Methode zum checken ob es eine app.Task gibt
    private boolean hasTask() {
        return !taskData.isEmpty();
    }

    //Methode um TaskListe zu zeigen
    private void showTask() {
        //Auslesen der taskData Liste
        for (Task task : taskData) {
            System.out.println(task);
        }
        if (taskData.isEmpty()) {
            System.out.println("Keine app.Task vorhanden");
        }
    }
    //Static -> objektunabhängigkeit von der Klasse

    //Methode um app.Task anzulegen
    private void addTask() {
        //Konsolenausgabe
        System.out.println("Füge app.Task hinzu:");
        //User input
        Scanner input = new Scanner(System.in);
        //app.Task objekt erstellen
        Task task = new Task();

        //id angelegt in der Konsole
        if (task.getId() == 0) {
            //int id = (int) (Math.random()*1000); -> diese Option hätte zu Duplikaten der IDs geführt
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
    private void addRating(Task task, Scanner input) {
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
    private void removeTask() {

        //app.Task anzeigen
        int choice;
        showTask();

        Scanner input = new Scanner(System.in);
        System.out.println("Was möchtest du löschen? (Gebe bitte die ID ein)");
        //Eingabe zum löschen einer app.Task
        choice = input.nextInt();

        //app.Task löschen
        Task selectedTask = null;
        //app.Task auslesen zum löschen
        for (Task task : taskData) {
            if (task.getId() == choice) {
                selectedTask = task;
            } else {
                System.out.println("Die ID existiert nicht mehr");
            }
        }
        //app.Task wird aus der Liste gelöscht
        if (selectedTask != null) {
            taskData.remove(selectedTask);
            System.out.println("Die app.Task wurde erfolgreich gelöscht");
        }
    }
}

