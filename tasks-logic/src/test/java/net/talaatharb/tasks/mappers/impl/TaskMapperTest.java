package net.talaatharb.tasks.mappers.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import net.talaatharb.tasks.dtos.TaskDto;
import net.talaatharb.tasks.entities.Task;
import net.talaatharb.tasks.mappers.TaskMapperImpl;
import net.talaatharb.tasks.utils.TaskTestUtils;

@ExtendWith(MockitoExtension.class)
class TaskMapperTest {

	@InjectMocks
	private TaskMapperImpl taskMapper;

	private void assertEqualTaskAndDto(Task task, TaskDto taskDto) {
		assertEquals(taskDto.getDetails(), task.getDetails());
		assertEquals(taskDto.getId(), task.getId());
		assertEquals(taskDto.getStatus(), task.getStatus());
	}

	private void assertEqualTaskListAndDtoList(List<Task> tasks, List<TaskDto> taskDtos) {
		int dtosSize = taskDtos.size();
		assertEquals(dtosSize, tasks.size());

		for (int i = 0; i < dtosSize; i++) {
			assertEqualTaskAndDto(tasks.get(i), taskDtos.get(i));
		}
	}

	@Test
	void testFromDTOToEntityTaskDto() {
		TaskDto dto = TaskTestUtils.createRandomTaskDto();
		Task entity = taskMapper.fromDTOToEntity(dto);
		assertEqualTaskAndDto(entity, dto);
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
		assertEqualTaskAndDto(entity, dto);
	}

}
