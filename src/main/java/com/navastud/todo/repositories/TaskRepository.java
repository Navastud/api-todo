package com.navastud.todo.repositories;

import com.navastud.todo.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("TaskRepository")
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

}
