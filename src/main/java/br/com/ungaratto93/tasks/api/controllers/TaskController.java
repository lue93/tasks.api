package br.com.ungaratto93.tasks.api.controllers;

import br.com.ungaratto93.tasks.api.dtos.TaskDTO;
import br.com.ungaratto93.tasks.api.entities.Task;
import br.com.ungaratto93.tasks.api.entities.TaskPrimaryKey;
import br.com.ungaratto93.tasks.api.service.TaskService;
import br.com.ungaratto93.tasks.api.service.impl.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class TaskController {

    @Autowired
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("task")
    public ResponseEntity<Task> create(@Valid @RequestBody TaskDTO taskDTO) {
        return new ResponseEntity(taskService.saveTask(taskDTO), HttpStatus.OK);
    }

    @GetMapping("task")
    public ResponseEntity<Object> findById(
            @Param("id") String id, @Param("priority") String priority
    ) {
        try {
            return ResponseEntity.ok(taskService.findById(new TaskPrimaryKey(id, priority)));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
    }

    @GetMapping("task/all")
    public ResponseEntity<Iterable<Task>> findAll() {
        return ResponseEntity.ok(taskService.findAll());
    }

    @PutMapping("task")
    public ResponseEntity<Task> update(@Valid @RequestBody TaskDTO taskDTO) throws NotFoundException {
        return ResponseEntity.ok(taskService.updateTask(taskDTO));
    }

}
