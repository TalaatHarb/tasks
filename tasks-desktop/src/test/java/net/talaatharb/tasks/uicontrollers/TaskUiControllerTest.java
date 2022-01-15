package net.talaatharb.tasks.uicontrollers;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.framework.junit5.ApplicationExtension;

import javafx.scene.control.TextField;
import net.talaatharb.tasks.facades.TaskFacade;

@ExtendWith(ApplicationExtension.class)
@ExtendWith(MockitoExtension.class)
class TaskUiControllerTest {
	
	@InjectMocks
	private TaskUiController taskUiController;
	
	@Mock
	private TaskFacade taskFacade;
	
	private TextField taskTextField;
	
	@BeforeEach
	void setup() {
		taskTextField = new TextField();
		taskUiController.setTaskTextField(taskTextField);
	}

	@Test
	void testAddTaskClicked() {
		taskTextField.setText(UUID.randomUUID().toString());
		taskUiController.addTaskClicked();
		Mockito.verify(taskFacade).createTask(Mockito.any());
	}
	
	@Test
	void testAddTaskClickedWithoutText() {
		taskTextField.setText("");
		taskUiController.addTaskClicked();
		Mockito.verifyNoInteractions(taskFacade);
	}
}
