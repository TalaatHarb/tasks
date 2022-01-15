package net.talaatharb.tasks.facades.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.talaatharb.tasks.dtos.TaskDto;
import net.talaatharb.tasks.facades.TaskFacade;
import net.talaatharb.tasks.mappers.TaskMapper;
import net.talaatharb.tasks.services.TaskService;

@RequiredArgsConstructor
@Service
public class TaskFacadeImpl implements TaskFacade {

	private final TaskMapper taskMapper;
	private final TaskService taskService;

	@Override
	public TaskDto createTask(TaskDto task) {
		return taskMapper.fromEntityToDTO(taskService.createTask(taskMapper.fromDTOToEntity(task)));
	}

	@Override
	public void deleteTaskById(UUID id) {
		taskService.deleteTaskById(id);
	}

	@Override
	public List<TaskDto> findAllTasks() {
		return taskMapper.fromEntityToDTO(taskService.findAllTasks());
	}

	@Override
	public Page<TaskDto> findPageOfTasks(Pageable pageable) {
		return taskMapper.fromEntityToDTO(taskService.findPageOfTasks(pageable));
	}

	@Override
	public TaskDto findTaskById(UUID id) {
		return taskMapper.fromEntityToDTO(taskService.findTaskById(id));
	}

}
