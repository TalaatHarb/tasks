package net.talaatharb.tasks.exceptions;

import java.util.UUID;

public class TaskNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -828692257673953181L;
	private final UUID id;

	public TaskNotFoundException(UUID id) {
		this.id = id;
	}

	@Override
	public String getMessage() {
		return String.format("Unable to find task with id = %s", id.toString());
	}

}
