package com.navastud.todo.repositories;

import com.navastud.todo.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("TaskRepository")
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

}
