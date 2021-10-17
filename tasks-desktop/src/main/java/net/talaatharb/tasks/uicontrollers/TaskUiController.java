package net.talaatharb.tasks.uicontrollers;

import java.util.UUID;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lombok.Setter;
import net.talaatharb.tasks.dtos.TaskDto;
import net.talaatharb.tasks.facades.TaskFacade;

@Component
public class TaskUiController {

	private final TaskFacade taskFacade;

	public TaskUiController(TaskFacade taskFacade) {
		this.taskFacade = taskFacade;
	}

	@FXML
	@Setter
	private TextField taskTextField;

	@FXML
	public void addTaskClicked() {
		String taskText = taskTextField.getText();
		if (taskText != null && !taskText.isBlank()) {
			TaskDto taskDto = new TaskDto(taskText, UUID.randomUUID(), false);
			taskFacade.createTask(taskDto);
			taskTextField.setText(Strings.EMPTY);
		}
	}

	@FXML
	public void chooseTasksClicked() {
	}

}
