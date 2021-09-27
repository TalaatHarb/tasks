package net.talaatharb.tasks.services.impl;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import net.talaatharb.tasks.entities.Task;
import net.talaatharb.tasks.repositories.TaskRepository;
import net.talaatharb.tasks.utils.TaskTestUtils;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

	@Mock
	private TaskRepository taskRepository;

	@InjectMocks
	private TaskServiceImpl taskService;

	@Test
	void testCreateTask() {
		Task task = TaskTestUtils.createRandomTask();

		taskService.createTask(task);

		Mockito.verify(taskRepository).save(task);
	}

	@Test
	void testDeleteTaskById() {
		UUID id = UUID.randomUUID();

		taskService.deleteTaskById(id);

		Mockito.verify(taskRepository).deleteById(id);
	}

	@Test
	void testFindAllTasks() {
		taskService.findAllTasks();

		Mockito.verify(taskRepository).findAll();
	}

	@Test
	void testFindAllTasksPageable() {
		Pageable pageable = Pageable.ofSize(10);

		taskService.findAllTasks(pageable);

		Mockito.verify(taskRepository).findAll(pageable);
	}

	@Test
	void testFindTaskById() {
		Task task = TaskTestUtils.createRandomTask();
		UUID id = task.getId();

		Mockito.when(taskRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(task));

		taskService.findTaskById(id);

		Mockito.verify(taskRepository).findById(id);
	}

}
