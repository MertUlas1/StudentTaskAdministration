import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // This means that this class is a Controller
@RequestMapping(path="/task") // This means URL's start with /demo (after Application path)
public class MainController {

    /**
     * fetch all tasks
     * @return
     */
    //Komplette Liste
    @GetMapping("/")


    public @ResponseBody List<Task> fetchAllTasks(){
        return new List<>(TestNonStatic..values());
    }

    /**
     * fetch the tasks -> testData by ID / if null = notfound
     * @param id
     * @return
     */
    //
    @GetMapping("/{id}")
    public ResponseEntity<Task> fetchTaskById(@PathVariable("id") int id){
        Task task = taskData.get(id);
        if (task != null){
            return ResponseEntity.ok().body(task);
        }
        return ResponseEntity.notFound().build();
    }


    /**
     * post the task
     * @param task
     * @return
     */
    @PostMapping(value="/new", consumes="application/json", produces="application/json")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        if (task == null || !task.validate()) {
            return ResponseEntity.badRequest().build();
        }
        testData.store(task);
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

        Task task2 =testData.getAllTasks().get(id);
        if(task!=null){
            task2.setName(task.getName());
            task2.setDescription(task.getDescription());
            task2.setCategory(task.getCategory());
            task2.setRating(task.getRating());
            task2.setYear(task.getYear());
            testData.getAllTasks().put(id, task2);

            return ResponseEntity.ok(task);
        }
        return ResponseEntity.notFound().build();
    }


    /**
     * delete task by ID, if task exist
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") String id ) {
        if (testData.getAllTasks().remove(id) == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
