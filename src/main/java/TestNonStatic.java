import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

//1. AtomicInteger vs int
//- AtomicInteger zählt ID atomar hoch, nutzbar für Multi-Theards
//- Erhöhung des Zähler „count++“ in Java nicht Threadsicher -> kann zu Problemen innerhalb der verschiednen Threads führen oder zu Performanceproblemen - deshalb stellt AtomicInteger auf die direkt von mehreren Threads zugegriffen werden kann
//In meinem Fall hätte das Hochzählen mit „count++“ vorerst zu keinen Probleme geführt, aber der Performance wegen habe ich das AtomicInteger genutzt, um Synchronisierungsprozesse mit dem „count++“ zu vermeiden. Die Version mit dem normalen primitiven Int ist übrigens in Git gepusht.
//2. Static
//- objektunabhängig von Klassen, keine Instanzzierung notwendig
//- zu nutzen, wenn man keine Objekte erzeugen möchte


public class TestNonStatic {
    private final List<Task> taskData = new ArrayList<>();
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
                //Case "2" füge Task hinzu
                case 2:
                    if (hasTask()) {
                        showTask();
                        break;
                    }
                    System.out.println("Die ist Funktion ist nicht vefügbar");
                    break;
                //Case "3" lösche Task
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
        System.out.println("1. Füge eine Task hinzu");

        if (hasTask()) {
            System.out.println("2. Zeige die Aufgabenliste");
        }

        if (hasTask()) {
            System.out.println("3. Lösche eine Task von der Liste\n");
        }
        System.out.print("Was möchten Sie machen?: ");
        return keyboard.nextInt();

    }

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
            System.out.println("Keine Task vorhanden");
        }
    }
    //Static -> objektunabhängigkeit von der Klasse

    //Methode um Task anzulegen
    private void addTask() {
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
            int id = count.incrementAndGet();
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
        while (task.getRating() == null) {
            addRating(task, input);
        }
        //Eingaben werden der Liste taskData zugewiesen
        taskData.add(task);
    }

    //Methode um Rating zu setzen - Enum
    private void addRating(Task task, Scanner input) {
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
    private void removeTask() {

        //Task anzeigen
        int choice;
        showTask();

        Scanner input = new Scanner(System.in);
        System.out.println("Was möchtest du löschen? (Gebe bitte die ID ein)");
        //Eingabe zum löschen einer Task
        choice = input.nextInt();

        Task selectedTask = null;
        for (Task task : taskData) {
            if (task.getId() == choice) {
                selectedTask = task;
            } else {
                System.out.println("Die ID existiert nicht mehr");
            }
        }
        //z.B. Task mit ID 4
        if (selectedTask != null) {
            taskData.remove(selectedTask);
            System.out.println("Die Task wurde erfolgreich gelöscht");
        }
    }
}

