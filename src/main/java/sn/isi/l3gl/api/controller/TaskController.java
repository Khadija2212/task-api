package sn.isi.l3gl.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.isi.l3gl.core.entity.Task;
import sn.isi.l3gl.core.enums.TaskStatus;
import sn.isi.l3gl.core.service.TaskService;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Map<String, String> body) {
        return ResponseEntity.ok(taskService.createTask(
            body.get("title"), body.get("description")));
    }

    @GetMapping
    public ResponseEntity<List<Task>> listTasks() {
        return ResponseEntity.ok(taskService.listTasks());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Task> updateStatus(@PathVariable Long id,
            @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(taskService.updateStatus(
            id, TaskStatus.valueOf(body.get("status"))));
    }

    @GetMapping("/done/count")
    public ResponseEntity<Long> countCompletedTasks() {
        return ResponseEntity.ok(taskService.countCompletedTasks());
    }
}
