package net.talaatharb.tasks.services.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
import net.talaatharb.tasks.exceptions.TaskNotFoundException;
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
		final Task task = TaskTestUtils.createRandomTask();
		task.setId(null);

		taskService.createTask(task);

		Mockito.verify(taskRepository).save(task);
	}

	@Test
	void testDeleteTaskById() {
		final UUID id = UUID.randomUUID();

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
		final Pageable pageable = Pageable.ofSize(10);

		taskService.findPageOfTasks(pageable);

		Mockito.verify(taskRepository).findAll(pageable);
	}

	@Test
	void testFindTaskById() {
		final Task task = TaskTestUtils.createRandomTask();
		final UUID id = task.getId();

		Mockito.when(taskRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(task));

		taskService.findTaskById(id);

		Mockito.verify(taskRepository).findById(id);
	}

	@Test
	void testFindTaskThatDoesNotExistById() {
		final Task task = TaskTestUtils.createRandomTask();
		final UUID id = task.getId();

		Mockito.when(taskRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.empty());

		assertThrows(TaskNotFoundException.class, () -> {
			taskService.findTaskById(id);
		});

	}

}
