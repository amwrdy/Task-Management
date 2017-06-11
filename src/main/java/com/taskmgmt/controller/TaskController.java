package com.taskmgmt.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.taskmgmt.model.Task;
import com.taskmgmt.service.TaskService;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class TaskController {
    private static final String CONST_REDIRECT = "redirect:";
    private static final String CONST_REFERRER = "referer";
    private static final String KEY_TASK_ID    = "taskId";
    private static final String KEY_TASK_LIST  = "taskList";
    private static final String KEY_TASK_NAME  = "taskName";
    private static final String VIEW_TASK      = "task";
    private static final String ROOT_REDIRECT  = CONST_REDIRECT + "/";

    private final static Logger log = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @GetMapping(value = {"", "/"})
    public String getAllTask(Model model){
        List<Task> all = taskService.findAllTasks();
        model.addAttribute(KEY_TASK_LIST, all);
        return VIEW_TASK;
    }

    @PostMapping("/add")
    public ModelAndView addTask(@ModelAttribute Task task) {
        if(!task.getTaskName().isEmpty()) {
            taskService.addNewTask(task);
        }
        return new ModelAndView(ROOT_REDIRECT);
    }

    @GetMapping("/edit")
    public ModelAndView editTask(ModelAndView mav, @RequestParam(value = KEY_TASK_ID) Integer taskId) {
        if(taskId == null)
            return new ModelAndView(ROOT_REDIRECT);

        Task task = taskService.getTask(taskId);
        mav.addObject(KEY_TASK_ID, task.getTaskId());
        mav.addObject(KEY_TASK_NAME, task.getTaskName());
        mav.setViewName("edit");
        return mav;
    }

   @PostMapping("/save")
    public ModelAndView saveTask(@RequestParam Integer taskId, @RequestParam String taskName) {
        if(!taskName.isEmpty())
            taskService.editTask(taskId, taskName);
        return new ModelAndView(ROOT_REDIRECT);
    }

    @PostMapping("/mark")
    public ModelAndView markTask(@RequestHeader(value = CONST_REFERRER, required = false) final String referer,
                                @RequestParam Integer taskId) {
        taskService.markTask(taskId);
        return new ModelAndView(CONST_REDIRECT + referer);
    }

    @PostMapping("/delete")
    public ModelAndView deleteTask(@RequestHeader(value = CONST_REFERRER, required = false) final String referer,
                                   @RequestParam Integer taskId) {
        taskService.deleteTask(taskId);
        return new ModelAndView(new RedirectView(referer));
    }

    @GetMapping("/search")
    public ModelAndView searchTask(ModelAndView mav, @RequestParam(value = "q") String searchTerm){
        if(searchTerm.isEmpty())
            return new ModelAndView(ROOT_REDIRECT);

        List<Task> results = taskService.findTask(searchTerm);
        mav.addObject(KEY_TASK_LIST, results);
        mav.setViewName(VIEW_TASK);
        return mav;
    }
}
