package br.com.ungaratto93.tasks.api.repositories;

import br.com.ungaratto93.tasks.api.entities.Task;
import br.com.ungaratto93.tasks.api.entities.TaskPrimaryKey;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
public interface TaskRepository extends CrudRepository<Task, TaskPrimaryKey> {


}
