package net.talaatharb.tasks.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.talaatharb.tasks.entities.Task;

public interface TaskService {

	public Task createTask(Task task);

	public void deleteTaskById(UUID id);

	public List<Task> findAllTasks();

	public Page<Task> findAllTasks(Pageable pageable);

	public Task findTaskById(UUID id);
}
