package com.repin.service;

import com.repin.dao.TaskDAO;
import com.repin.domain.Status;
import com.repin.domain.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {
    private final TaskDAO taskDAO;

    public TaskService(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    public List<Task> getAll(int offset, int limit) {
        return taskDAO.getAll(offset, limit);
    }

    public int getAllCount() {
        return taskDAO.getAllCount();
    }

    public Task create(String description, Status status) {
        Task task = new Task();
        task.setDescription(description);
        task.setStatus(status);
        taskDAO.saveOrUpdate(task);
        return task;
    }

    @Transactional
    public Task edit(int id, String description, Status status) {
        Task task = taskDAO.getById(id);
        if (task == null) {
            throw new RuntimeException("Task not found!");
        }
        task.setDescription(description);
        task.setStatus(status);
        taskDAO.saveOrUpdate(task);
        return task;
    }

    @Transactional
    public void delete(int id) {
        Task task = taskDAO.getById(id);
        if (task == null) {
            throw new RuntimeException("Task not found!");
        }
        taskDAO.delete(task);
    }
}