package net.talaatharb.tasks.entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity for representing a task in the tasks table in the database
 * @author mharb
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {

	private String details;

	@Id
	private UUID id;

	private Boolean status;

}
