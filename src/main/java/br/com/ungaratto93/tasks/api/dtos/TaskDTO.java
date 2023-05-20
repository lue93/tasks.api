package br.com.ungaratto93.tasks.api.dtos;

import br.com.ungaratto93.tasks.api.entities.Task;
import br.com.ungaratto93.tasks.api.entities.TaskPrimaryKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class TaskDTO {

    @JsonProperty("task_id")
    private TaskPrimaryKey task_id;

    @JsonProperty("task_priority")
    @NotNull
    @NotBlank
    private String priority;

    @JsonProperty("task_name")
    @NotNull
    @NotBlank
    private String taskName;

    @JsonProperty("task_description")
    @NotNull
    @NotBlank
    private String taskDescription;

    private LocalDateTime updatedAt = LocalDateTime.now();

    @DynamoDBAttribute(attributeName = "task_done")
    @JsonProperty("task_done")
    private Boolean taskDone;

    public static TaskDTO from(Task task) {

        return new TaskDTO();

    }

    public Task to() {
        return new Task(
                new TaskPrimaryKey(),
                this.priority,
                this.taskName,
                this.taskDescription,
                this.updatedAt,
                this.taskDone
        );
    }

    public TaskPrimaryKey getId() {
        return this.task_id;
    }
}
