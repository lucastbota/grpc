package org.open.ticket.ato.dois;

import java.io.Serializable;

public class TicketDTO implements Serializable {
	private static final long serialVersionUID = -5622589359426001912L;
	private String description;
	private String where;

	public TicketDTO() {
	}

	public TicketDTO(String description, String where) {
		super();
		this.description = description;
		this.where = where;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((where == null) ? 0 : where.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TicketDTO other = (TicketDTO) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (where == null) {
			if (other.where != null)
				return false;
		} else if (!where.equals(other.where))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TicketDTO [description=" + description + ", where=" + where + "]";
	}
}
