package com.taskmgmt.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taskmgmt.exceptions.TaskNotFoundException;
import com.taskmgmt.model.Task;
import com.taskmgmt.model.TaskStatus;
import com.taskmgmt.repo.TaskRepo;

@Service
public class TaskServiceImpl implements TaskService {

    private final static Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);
    @Autowired
    TaskRepo taskRepo;

    @Override
    public void addNewTask(Task task) {
        try {
            taskRepo.save(task);
            log.info("Task {} added successfully", task.getTaskId());
        } catch(Exception ex) {
            log.error("Error adding new task; {}", ex.getMessage());
            throw ex;
        }
    }

    @Transactional
    public void deleteTask(Integer taskId) {
        try {
            taskRepo.delete(taskId);
            log.info("Task {} deleted successfully", taskId);
        } catch(Exception ex) {
            log.error("Unable to delete task {}", taskId);
        }
    }

    @Transactional
    public void editTask(Integer taskId, String taskName) {
        try {
            Task task = taskRepo.findOne(taskId);
            task.setTaskName(taskName);
            taskRepo.save(task);
            log.info("Task {} edited and saved" , taskId);
        }
        catch (Exception ex) {
            log.error("Unable to save task {}", taskId);
            throw ex;
        }
    }

    @Override
    public Task getTask(Integer taskId) {
        Task task = taskRepo.findOne(taskId);
        if (task == null)
            throw new TaskNotFoundException(taskId);

        return task;
    }

    @Override
    public List<Task> findTask(String searchTerm) {
        return taskRepo.findByTaskNameContaining(searchTerm);
    }

    @Override
    public List<Task> findAllTasks() {
        return (List<Task>) taskRepo.findAll();
    }

    @Transactional
    public void markTask(Integer taskId) {
            Task task = taskRepo.findOne(taskId);
            TaskStatus newStatus =
                (task.getTaskStatus() == TaskStatus.COMPLETED) ? TaskStatus.INCOMPLETE : TaskStatus.COMPLETED;
            task.setTaskStatus(newStatus);
            log.info("Task {} marked ", taskId);
            taskRepo.save(task);
    }


}
