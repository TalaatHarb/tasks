package net.talaatharb.tasks.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.talaatharb.tasks.api.TasksRestAPI;
import net.talaatharb.tasks.dtos.TaskDto;
import net.talaatharb.tasks.facades.TaskFacade;

@RestController
@RequiredArgsConstructor
public class TasksRestController implements TasksRestAPI {

	private final TaskFacade taskFacade;

	@Override
	public TaskDto createTask(final TaskDto task) {
		return taskFacade.createTask(task);
	}

	@Override
	public void deleteTask(final UUID id) {
		taskFacade.deleteTaskById(id);
	}

	@Override
	public Page<TaskDto> getTasks(final Pageable pagable) {
		if(pagable != null) {			
			return taskFacade.findPageOfTasks(pagable);
		}else {
			final List<TaskDto> tasks = taskFacade.findAllTasks(); 
			return new PageImpl<>(tasks);
		}
	}

	@Override
	public TaskDto getTaskById(final UUID id) {
		return taskFacade.findTaskById(id);
	}

}
