package net.talaatharb.tasks.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import net.talaatharb.tasks.dtos.TaskDto;
import net.talaatharb.tasks.entities.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {

	public abstract Task fromDTOToEntity(TaskDto dto);

	public abstract TaskDto fromEntityToDTO(Task entity);

	public abstract List<TaskDto> fromEntityToDTO(List<Task> entity);

	public default Page<TaskDto> fromEntityToDTO(Page<Task> entityPage) {
		List<TaskDto> dtos = this.fromEntityToDTO(entityPage.getContent());
		return new PageImpl<>(dtos, entityPage.getPageable(), entityPage.getTotalElements());
	}
}
