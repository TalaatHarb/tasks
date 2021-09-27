package net.talaatharb.tasks.facades;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.talaatharb.tasks.dtos.TaskDto;

public interface TaskFacade {
	public TaskDto createTask(TaskDto task);

	public void deleteTaskById(UUID id);

	public List<TaskDto> findAllTasks();

	public Page<TaskDto> findAllTasks(Pageable pageable);

	public TaskDto findTaskById(UUID id);
}
