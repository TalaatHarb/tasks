package net.talaatharb.tasks.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import net.talaatharb.tasks.entities.Task;
import net.talaatharb.tasks.exceptions.TaskNotFoundException;
import net.talaatharb.tasks.repositories.TaskRepository;
import net.talaatharb.tasks.services.TaskService;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;

	@Override
	@Transactional(readOnly = false)
	public Task createTask(Task task) {
		if (task.getId() == null) {
			task.setId(UUID.randomUUID());
		}
		return taskRepository.save(task);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteTaskById(UUID id) {
		taskRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Task> findAllTasks() {
		return taskRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Task> findAllTasks(Pageable pageable) {
		return taskRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Task findTaskById(UUID id) {
		Optional<Task> taskOptional = taskRepository.findById(id);
		if (taskOptional.isEmpty()) {
			throw new TaskNotFoundException(id);
		}
		return taskOptional.get();
	}

}
