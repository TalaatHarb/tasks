package net.talaatharb.tasks.facades.impl;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import net.talaatharb.tasks.dtos.TaskDto;
import net.talaatharb.tasks.entities.Task;
import net.talaatharb.tasks.mappers.TaskMapper;
import net.talaatharb.tasks.services.TaskService;
import net.talaatharb.tasks.utils.TaskTestUtils;

@ExtendWith(MockitoExtension.class)
class TaskFacadeImplTest {

	@InjectMocks
	private TaskFacadeImpl taskFacade;

	@Mock
	private TaskMapper taskMapper;

	@Mock
	private TaskService taskService;

	@Test
	void testCreateTask() {
		TaskDto taskDto = TaskTestUtils.createRandomTaskDto();
		Task task = new Task();

		Mockito.when(taskMapper.fromDTOToEntity(Mockito.any(TaskDto.class))).thenReturn(task);
		Mockito.when(taskService.createTask(Mockito.any(Task.class))).thenReturn(task);

		taskFacade.createTask(taskDto);

		Mockito.verify(taskMapper).fromDTOToEntity(taskDto);
		Mockito.verify(taskService).createTask(task);
		Mockito.verify(taskMapper).fromEntityToDTO(task);
	}

	@Test
	void testDeleteTaskById() {
		UUID id = UUID.randomUUID();
		taskFacade.deleteTaskById(id);
		Mockito.verify(taskService).deleteTaskById(id);
	}

	@Test
	void testFindAllTasks() {
		taskFacade.findAllTasks();
		Mockito.verify(taskService).findAllTasks();
		Mockito.verify(taskMapper).fromEntityToDTO(Mockito.anyList());
	}

	@Test
	void testFindAllTasksPageable() {
		Pageable pageable = Pageable.ofSize(10);

		taskFacade.findAllTasks(pageable);

		Mockito.verify(taskService).findAllTasks(pageable);
	}

	@Test
	void testFindTaskById() {
		UUID id = UUID.randomUUID();

		Mockito.when(taskService.findTaskById(Mockito.any(UUID.class))).thenReturn(new Task());

		taskFacade.findTaskById(id);

		Mockito.verify(taskService).findTaskById(Mockito.any(UUID.class));
		Mockito.verify(taskMapper).fromEntityToDTO(Mockito.any(Task.class));
	}

}
