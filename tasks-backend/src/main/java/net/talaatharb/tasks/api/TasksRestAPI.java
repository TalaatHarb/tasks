package net.talaatharb.tasks.api;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import net.talaatharb.tasks.dtos.TaskDto;

@CrossOrigin
@RequestMapping(path = "/api/v1/tasks")
public interface TasksRestAPI {

	/**
	 * API for creating tasks
	 * @param task The task to be created
	 * @return
	 */
	@PostMapping(path = "")
	@ResponseStatus(code = HttpStatus.CREATED)
	public abstract TaskDto createTask(@RequestBody TaskDto task);
	
	/**
	 * Delete a task by id
	 * @param id
	 */
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public abstract void deleteTask(@PathVariable (name = "id") final UUID id) ;
	
	/**
	 * Return a page of tasks
	 * @param pagable
	 * @return
	 */
	@GetMapping(path = "")
	@ResponseStatus(code = HttpStatus.OK)
	public abstract Page<TaskDto> getTasks(@Nullable @RequestParam final Pageable pagable);
	
	/**
	 * Returns a task given its id
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public abstract TaskDto getTaskById(@PathVariable (name = "id") final UUID id) ;
}
