package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import model.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // This means that this class is a Controller
@RequestMapping(path="/task") // This means URL's start with /demo (after Application path)
public class MainController {

    private static final List<Task> taskData = new ArrayList<>();
    private final AtomicInteger count = new AtomicInteger(0);

    /**
     * fetch all tasks
     * @return
     */
    //Komplette Liste
    @GetMapping("/all")
    public @ResponseBody List<Task> fetchAllTasks(){
        //taskData
        return taskData;
    }

    /**
     * fetch the tasks -> testData by ID / if null = notfound
     * @param id
     * @return
     */
    //GET-Methode, per ID "getten"
    @GetMapping("/{id}")
    public ResponseEntity<Task> fetchTaskById(@PathVariable("id") int id){
        //Task gesucht
        Task task = findTask(id);
        //Task gefunden
        if (task != null){
            //wurde Task gefunden
            return ResponseEntity.ok().body(task);
        }
        //wenn keine keine Task da ist dann Fehlermeldung 404
        return ResponseEntity.notFound().build();
    }
    /**
     * post the task
     * @param task
     * @return
     */
    //POST
    @PostMapping(value="/new", consumes="application/json", produces="application/json")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        //Checkt Task gültig bzw. valide,
        if (task == null || !task.validate()) {
            //badRequest wenn nicht valide Eingaben übergeben wurden
            return ResponseEntity.badRequest().build();
        }
        //ID erstellt
        int id = count.incrementAndGet();
        //id wird gesetzt
        task.setId(id);
        //Task angelegt in der taskData Liste
        taskData.add(task);
        return ResponseEntity.ok(task);
    }
    /**
     * update the task by ID, if task exists
     * @param id
     * @param task
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody Task task) {
        if (task == null || !task.validate()) {
            return ResponseEntity.badRequest().build();
        }
        Task selectedTask = findTask(id);
        if(selectedTask!=null){
            selectedTask.setName(task.getName());
            selectedTask.setDescription(task.getDescription());
            selectedTask.setCategory(task.getCategory());
            selectedTask.setRating(task.getRating());
            selectedTask.setYear(task.getYear());

            return ResponseEntity.ok(selectedTask);
        }
        return ResponseEntity.notFound().build();
    }

    private Task findTask(int id) {
        Task selectedTask = null;
        //app.Task auslesen zum löschen
        for (Task searchTask : taskData) {
            if (searchTask.getId() == id) {
                selectedTask = searchTask;
                break;
            }
        }
        return selectedTask;
    }


    /**
     * delete task by ID, if task exist
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") int id ) {
        Task selectedTask = findTask(id);
        if(selectedTask == null){
            return ResponseEntity.notFound().build();
        }
        taskData.remove(selectedTask);
        return ResponseEntity.ok().build();
    }
}
