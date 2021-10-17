package net.talaatharb.tasks.uicontrollers;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javafx.scene.control.TextField;
import net.talaatharb.tasks.facades.TaskFacade;

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
		fail("Not yet implemented");
	}

	@Test
	void testChooseTasksClicked() {
		fail("Not yet implemented");
	}

}
