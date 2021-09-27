package net.talaatharb.tasks.uicontrollers;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import net.talaatharb.tasks.facades.TaskFacade;

@Component
@AllArgsConstructor
public class TaskUiController {
	
	private final TaskFacade taskFacade;

}
