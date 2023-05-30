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

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Arpan Mukhopadhyay
 *
 */
@Table(name = "workspace_properties", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "workspace_id"}, name = "uq_properties_on_workspace")})
@Entity
@Getter
@Setter
public class WorkspacePropEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workspace_prop_id_generator")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "type")
	private String propType;

	@Column(name = "val_constrt")
	private String validationContraint;

	@Column(name = "target")
	private String targetType;

	@Column(name = "is_mandatory", nullable = false)
	private boolean mandatory = false;

	@Column(name = "value")
	private String value;

	@Column(name = "default")
	private String defaulValue;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "workspace_id", referencedColumnName = "id")
	private WorkspaceEntity workspace;

	/**
	 * @param name
	 * @param description
	 * @param propType
	 * @param validationContraint
	 * @param targetType
	 * @param mandatory
	 * @param value
	 * @param defaulValue
	 * @param workspace
	 */
	private WorkspacePropEntity(String name, String description, String propType, String validationContraint,
			String targetType, boolean mandatory, String value, String defaulValue, WorkspaceEntity workspace) {
		this.name = name;
		this.description = description;
		this.propType = propType;
		this.validationContraint = validationContraint;
		this.targetType = targetType;
		this.mandatory = mandatory;
		this.value = value;
		this.defaulValue = defaulValue;
		this.workspace = workspace;
	}

	/**
	 * 
	 * @return
	 */
	public static WorkspacePropEntityBuilder builder() {
		return new WorkspacePropEntityBuilder();
	}

	/*
	 * 
	 */
	public static class WorkspacePropEntityBuilder {

		private String name;
		private String description;
		private String propType;
		private String validationContraint;
		private boolean mandatory;
		private String value;
		private String defaulValue;
		private WorkspaceEntity workspace;

		/**
		 * 
		 * @param name
		 * @return
		 */
		public WorkspacePropEntityBuilder name(String name) {
			this.name = name;
			return this;
		}

		/**
		 * 
		 * @param description
		 * @return
		 */
		public WorkspacePropEntityBuilder description(String description) {
			this.description = description;
			return this;
		}

		/**
		 * 
		 * @param propType
		 * @return
		 */
		public WorkspacePropEntityBuilder propType(String propType) {
			this.propType = propType;
			return this;
		}

		/**
		 * 
		 * @param validationContraint
		 * @return
		 */
		public WorkspacePropEntityBuilder validationContraint(String validationContraint) {
			this.propType = validationContraint;
			return this;
		}

		/**
		 * 
		 * @param mandatory
		 * @return
		 */
		public WorkspacePropEntityBuilder mandatory(boolean mandatory) {
			this.mandatory = mandatory;
			return this;
		}

		/**
		 * 
		 * @param value
		 * @return
		 */
		public WorkspacePropEntityBuilder value(String value) {
			this.value = value;
			return this;
		}

		/**
		 * 
		 * @param defaulValue
		 * @return
		 */
		public WorkspacePropEntityBuilder defaulValue(String defaulValue) {
			this.defaulValue = defaulValue;
			return this;
		}

		/**
		 * 
		 * @param workspace
		 * @return
		 */
		public WorkspacePropEntityBuilder workspace(WorkspaceEntity workspace) {
			this.workspace = workspace;
			return this;
		}

		/**
		 * 
		 */
		private WorkspacePropEntityBuilder() {
		}

		public WorkspacePropEntity build() {
			return new WorkspacePropEntity(name, description, propType, validationContraint, description, mandatory,
					value, defaulValue, workspace);
		}
	}
}
