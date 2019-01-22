//This creates the id for all entities and have the functions to get the id's created
package io.altar.CinemaTicketSystem.Models;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// Attributes:
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	// ________________________________________________________________________________________________

	// Gets and Setters:
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	// ________________________________________________________________________________________________

}
