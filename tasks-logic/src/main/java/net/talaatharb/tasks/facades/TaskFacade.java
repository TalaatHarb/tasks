package net.talaatharb.tasks.facades;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.talaatharb.tasks.dtos.TaskDto;

/**
 * Facade for tasks service
 * @author mharb
 *
 */
public interface TaskFacade {
	
	/**
	 * Facade for creation of tasks
	 * @param task The task to create
	 * @return The created task
	 */
	public TaskDto createTask(TaskDto task);

	/**
	 * Delete a task given its id
	 * @param id the id of the task to delete
	 */
	public void deleteTaskById(UUID id);

	/**
	 * Facade for retrieving all tasks as a list
	 * @return
	 */
	public List<TaskDto> findAllTasks();

	/**
	 * Facade for retrieving a page of tasks
	 * @param pageable
	 * @return
	 */
	public Page<TaskDto> findAllTasks(Pageable pageable);

	/**
	 * Get a task given its id
	 * @param id
	 * @return
	 */
	public TaskDto findTaskById(UUID id);
}
