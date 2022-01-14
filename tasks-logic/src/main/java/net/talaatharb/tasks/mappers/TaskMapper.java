package net.talaatharb.tasks.mappers;

import org.mapstruct.Mapper;

import net.talaatharb.tasks.dtos.TaskDto;
import net.talaatharb.tasks.entities.Task;

/**
 * Mapper interface for mapping between task entities and task dtos
 * @author mharb
 *
 */
@Mapper(componentModel = "spring")
public interface TaskMapper extends DefaultMapper<Task, TaskDto> {
}
