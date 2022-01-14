package net.talaatharb.tasks.uicontrollers;

import java.util.UUID;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.talaatharb.tasks.dtos.TaskDto;
import net.talaatharb.tasks.facades.TaskFacade;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaskUiController {

	private final TaskFacade taskFacade;

	@FXML
	@Setter
	private TextField taskTextField;

	@FXML
	public void addTaskClicked() {
		log.debug("Add task clicked");
		String taskText = taskTextField.getText();
		if (taskText != null && !taskText.isBlank()) {
			TaskDto taskDto = new TaskDto(taskText, UUID.randomUUID(), false);
			taskFacade.createTask(taskDto);
			taskTextField.setText(Strings.EMPTY);
			taskTextField.requestFocus();
		}
	}
}
