/*
 * Trackit (https://github.com/opentraktools/trackit) is a free tool which
 * helps you track your daily work progress with a flavor of agile.
 *
 * Copyright (C) 2023-present  Arpan Mukhopadhyay. All rights reserved.
 * 
 * DO NOT DELETE OR MODIFY THIS LICENSE INFORMATION OR THIS FILE HEADER.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package org.opentracktools.trackit.domain.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Arpan Mukhopadhyay
 *
 */
@Entity
@Table(name = "workspaces", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "uq_name_on_projects")})
@Getter
@Setter
public class WorkspaceEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workspace_id_generator")
	private Long id;

	@Column(name = "name", length = 32, nullable = false)
	private String name = "";

	@Column(name = "description", length = 255)
	private String description;

	@Column(name = "notes")
	@Lob
	private String notes;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "id", name = "owner_id")
	private UserEntity ownerId;

	@Column(name = "type")
	private AccessType type = AccessType.PRIVATE;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "workspace")
	private List<WorkspacePropEntity> properties;

	/**
	 * @param name
	 * @param description
	 * @param notes
	 * @param ownerId
	 * @param type
	 * @param properties
	 */
	private WorkspaceEntity(String name, String description, String notes, UserEntity ownerId, AccessType type,
			List<WorkspacePropEntity> properties) {
		this.name = name;
		this.description = description;
		this.notes = notes;
		this.ownerId = ownerId;
		this.type = type;
		this.properties = properties;
	}

	/**
	 * 
	 * @return
	 */
	public static WorkspaceEntityBuilder builder() {
		return new WorkspaceEntityBuilder();
	}

	/**
	 * 
	 * @author Arpan Mukhopadhyay
	 *
	 */
	public static class WorkspaceEntityBuilder {

		/**
		 * 
		 */
		private WorkspaceEntityBuilder() {
		}

		private String name;
		private String description;
		private String notes;
		private AccessType type;
		private UserEntity owner;
		private List<WorkspacePropEntity> properties;

		/**
		 * 
		 * @param name
		 * @return
		 */
		public WorkspaceEntityBuilder name(String name) {
			this.name = name;
			return this;
		}

		/**
		 * 
		 * @param description
		 * @return
		 */
		public WorkspaceEntityBuilder description(String description) {
			this.description = description;
			return this;
		}

		/**
		 * 
		 * @param notes
		 * @return
		 */
		public WorkspaceEntityBuilder notes(String notes) {
			this.notes = notes;
			return this;
		}

		/**
		 * 
		 * @param owner
		 * @return
		 */
		public WorkspaceEntityBuilder owner(UserEntity owner) {
			this.owner = owner;
			return this;
		}

		/**
		 * 
		 * @param properties
		 * @return
		 */
		public WorkspaceEntityBuilder properites(List<WorkspacePropEntity> properties) {
			this.properties = properties;
			return this;
		}

		/**
		 * 
		 * @return
		 */
		public WorkspaceEntity build() {
			return new WorkspaceEntity(name, description, notes, owner, type, properties);
		}
	}
}
