package br.com.ungaratto93.tasks.api.service.impl;

import br.com.ungaratto93.tasks.api.dtos.TaskDTO;
import br.com.ungaratto93.tasks.api.entities.Task;
import br.com.ungaratto93.tasks.api.entities.TaskPrimaryKey;
import br.com.ungaratto93.tasks.api.repositories.TaskRepository;
import br.com.ungaratto93.tasks.api.service.TaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task saveTask(TaskDTO taskDTO) {
        return taskRepository.save(taskDTO.to());
    }

    @Override
    public Iterable<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(TaskPrimaryKey id) throws NotFoundException {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            return optionalTask.get();
        } else {
            throw new NotFoundException("TaskNotFound");
        }
    }

    @Override
    public Task updateTask(TaskDTO taskDTO) throws NotFoundException {

        Optional<Task> optionalTask = taskRepository.findById(taskDTO.getId());
        if(optionalTask.isEmpty()) {
            throw new NotFoundException("There is no Task with this id number");
        }

        Task task = optionalTask.get();
        BeanUtils.copyProperties(taskDTO, task, "id");

        return taskRepository.save(task);
    }

    @Override
    public Task doneTask(TaskPrimaryKey id, Boolean isDone) throws NotFoundException {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if(optionalTask.isEmpty()) {
            throw new NotFoundException("There is no Task with this id number");
        }
        Task task = optionalTask.get();
        task.setTaskDone(isDone);

        return taskRepository.save(task);
    }
}
