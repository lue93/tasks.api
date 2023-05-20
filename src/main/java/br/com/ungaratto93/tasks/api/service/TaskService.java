package br.com.ungaratto93.tasks.api.service;

import br.com.ungaratto93.tasks.api.dtos.TaskDTO;
import br.com.ungaratto93.tasks.api.entities.Task;
import br.com.ungaratto93.tasks.api.entities.TaskPrimaryKey;
import br.com.ungaratto93.tasks.api.service.impl.NotFoundException;

import java.util.List;

public interface TaskService {
    Task saveTask(TaskDTO taskDTO);
    Iterable<Task> findAll();
    Task findById(TaskPrimaryKey id) throws NotFoundException;
    Task updateTask(TaskDTO taskDTO) throws NotFoundException;
    Task doneTask(TaskPrimaryKey id, Boolean isDone) throws NotFoundException;

}
