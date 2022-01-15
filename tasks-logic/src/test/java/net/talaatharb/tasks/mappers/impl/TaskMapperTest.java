package net.talaatharb.tasks.mappers.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import net.talaatharb.tasks.dtos.TaskDto;
import net.talaatharb.tasks.entities.Task;
import net.talaatharb.tasks.mappers.TaskMapper;
import net.talaatharb.tasks.utils.TaskTestUtils;

class TaskMapperTest {

	private TaskMapper taskMapper = Mappers.getMapper(TaskMapper.class);

	private void assertEqualEntityAndDto(Task task, TaskDto taskDto) {
		assertEquals(taskDto.getDetails(), task.getDetails());
		assertEquals(taskDto.getId(), task.getId());
		assertEquals(taskDto.getStatus(), task.getStatus());
	}

	private void assertEqualTaskListAndDtoList(List<Task> tasks, List<TaskDto> taskDtos) {
		int dtosSize = taskDtos.size();
		assertEquals(dtosSize, tasks.size());

		for (int i = 0; i < dtosSize; i++) {
			assertEqualEntityAndDto(tasks.get(i), taskDtos.get(i));
		}
	}

	@Test
	void testFromDTOToEntityTaskDto() {
		TaskDto dto = TaskTestUtils.createRandomTaskDto();
		Task entity = taskMapper.fromDTOToEntity(dto);
		assertEqualEntityAndDto(entity, dto);
	}

	@Test
	void testFromEntityToDTOListOfTask() {
		List<Task> entities = Arrays
				.asList(new Task[] { TaskTestUtils.createRandomTask(), TaskTestUtils.createRandomTask() });
		List<TaskDto> dtos = taskMapper.fromEntityToDTO(entities);

		assertEqualTaskListAndDtoList(entities, dtos);
	}

	@Test
	void testFromEntityToDTOPageOfTask() {
		Page<Task> entityPage = new PageImpl<>(
				Arrays.asList(new Task[] { TaskTestUtils.createRandomTask(), TaskTestUtils.createRandomTask() }));
		Page<TaskDto> dtoPage = taskMapper.fromEntityToDTO(entityPage);

		assertEquals(entityPage.getNumber(), dtoPage.getNumber());
		assertEquals(entityPage.getNumberOfElements(), dtoPage.getNumberOfElements());

		assertEqualTaskListAndDtoList(entityPage.getContent(), dtoPage.getContent());
	}

	@Test
	void testFromEntityToDTOTask() {
		Task entity = TaskTestUtils.createRandomTask();
		TaskDto dto = taskMapper.fromEntityToDTO(entity);
		assertEqualEntityAndDto(entity, dto);
	}

}
