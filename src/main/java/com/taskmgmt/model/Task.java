package com.taskmgmt.model;

import javax.persistence.*;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskId;
    private String taskName;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    public Task() {
        this.taskStatus = TaskStatus.INCOMPLETE;
    }

    public Task (String taskName) {
        this.taskName = taskName;
        this.taskStatus = TaskStatus.INCOMPLETE;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(final Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(final String taskName) {
        this.taskName = taskName;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
