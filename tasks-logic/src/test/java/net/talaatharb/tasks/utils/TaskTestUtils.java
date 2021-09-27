package net.talaatharb.tasks.utils;

import java.util.Random;
import java.util.UUID;

import net.talaatharb.tasks.dtos.TaskDto;
import net.talaatharb.tasks.entities.Task;

public class TaskTestUtils {

	private static final Random RANDOM = new Random();

	public static final Task createRandomTask() {
		UUID id = UUID.randomUUID();
		return new Task(id.toString(), id, RANDOM.nextFloat() > 0.5);
	}

	public static final TaskDto createRandomTaskDto() {
		UUID id = UUID.randomUUID();
		return new TaskDto(id.toString(), id, RANDOM.nextFloat() > 0.5);
	}

	private TaskTestUtils() {
	}

}
