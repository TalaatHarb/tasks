package net.talaatharb.tasks.facades.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
import net.talaatharb.tasks.exceptions.TaskNotFoundException;
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
		final TaskDto taskDto = TaskTestUtils.createRandomTaskDto();
		final Task task = new Task();

		Mockito.when(taskMapper.fromDTOToEntity(Mockito.any(TaskDto.class))).thenReturn(task);
		Mockito.when(taskService.createTask(Mockito.any(Task.class))).thenReturn(task);

		taskFacade.createTask(taskDto);

		Mockito.verify(taskMapper).fromDTOToEntity(taskDto);
		Mockito.verify(taskService).createTask(task);
		Mockito.verify(taskMapper).fromEntityToDTO(task);
	}

	@Test
	void testDeleteTaskById() {
		final UUID id = UUID.randomUUID();
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
		final Pageable pageable = Pageable.ofSize(10);

		taskFacade.findPageOfTasks(pageable);

		Mockito.verify(taskService).findPageOfTasks(pageable);
	}

	@Test
	void testFindTaskById() {
		final UUID id = UUID.randomUUID();

		Mockito.when(taskService.findTaskById(Mockito.any(UUID.class))).thenReturn(new Task());

		taskFacade.findTaskById(id);

		Mockito.verify(taskService).findTaskById(Mockito.any(UUID.class));
		Mockito.verify(taskMapper).fromEntityToDTO(Mockito.any(Task.class));
	}

	@Test
	void testFindTaskThatDoesNotExistById() {
		assertThrows(TaskNotFoundException.class, () -> {
			final UUID id = UUID.randomUUID();

			Mockito.when(taskService.findTaskById(Mockito.any(UUID.class))).thenThrow(new TaskNotFoundException(id));

			taskFacade.findTaskById(id);

			Mockito.verify(taskService).findTaskById(Mockito.any(UUID.class));
			Mockito.verify(taskMapper, Mockito.never()).fromEntityToDTO(Mockito.any(Task.class));
		});
	}

}
