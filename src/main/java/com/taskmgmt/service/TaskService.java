package com.taskmgmt.service;

import java.util.List;

import com.taskmgmt.model.Task;

public interface TaskService {
    void addNewTask(Task task);
    void deleteTask(Integer taskId);
    void editTask(Integer taskId, String taskName);
    List<Task> findAllTasks();
    List<Task> findTask(String searchTerm);
    Task getTask(Integer taskId);
    void markTask(Integer taskId);
}
