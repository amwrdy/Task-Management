package com.taskmgmt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such task")
public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(Integer taskId) {
        super("Task " +taskId + " not found");
    }
}
