package net.talaatharb.tasks.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.talaatharb.tasks.entities.Task;
import net.talaatharb.tasks.exceptions.TaskNotFoundException;
import net.talaatharb.tasks.repositories.TaskRepository;
import net.talaatharb.tasks.services.TaskService;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional(readOnly = true)
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;

	@Override
	@Transactional(readOnly = false)
	public Task createTask(Task task) {
		task.setId(UUID.randomUUID());
		log.debug("Creating task {}", task);
		return taskRepository.save(task);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteTaskById(UUID id) {
		log.debug("Deleting task with id = {}", id);
		taskRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Task> findAllTasks() {
		log.debug("Retrieving all tasks");
		return taskRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Task> findAllTasks(Pageable pageable) {
		log.debug("Retrieving page of tasks with the following specification {}", pageable);
		return taskRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Task findTaskById(UUID id) {
		log.debug("Retrieving task with id = {}", id);
		Optional<Task> taskOptional = taskRepository.findById(id);
		if (taskOptional.isEmpty()) {
			log.debug("Unable to retrive task with id = {}", id);
			throw new TaskNotFoundException(id);
		}
		return taskOptional.get();
	}

}
