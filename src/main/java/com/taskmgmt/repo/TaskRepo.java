package com.taskmgmt.repo;


import java.util.List;

import com.taskmgmt.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends CrudRepository<Task, Integer> {
    List<Task> findByTaskNameContaining(String taskName);
}
