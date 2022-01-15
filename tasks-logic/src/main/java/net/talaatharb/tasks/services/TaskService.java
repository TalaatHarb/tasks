package net.talaatharb.tasks.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.talaatharb.tasks.entities.Task;
/**
 * Service for managing logic of doing operations on tasks
 * @author mharb
 *
 */
public interface TaskService {

	/**
	 * Create a task on the database
	 * @param task
	 * @return
	 */
	public Task createTask(Task task);

	/**
	 * Delete an existing task on the database
	 * @param id
	 */
	public void deleteTaskById(UUID id);

	/**
	 * Find all tasks in the database
	 * @return
	 */
	public List<Task> findAllTasks();

	/**
	 * Return a page of tasks from the database
	 * @param pageable The specification for the page of tasks that is requested from the database
	 * @return
	 */
	public Page<Task> findPageOfTasks(Pageable pageable);

	/**
	 * Get a specific task by id from the database if it exists
	 * @param id
	 * @return
	 */
	public Task findTaskById(UUID id);
}
