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

import java.io.Serial;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username", name = "uq_username_on_users"),
		@UniqueConstraint(columnNames = "email", name = "uq_email_in_users") })
@Getter
@Setter
public class UserEntity extends BaseEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_generator")
	private Long id;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "location")
	private String location;

	@Column(name = "status_text", length = 50)
	private String statusText = "";

	@Column(name = "notes_html", length = 2000)
	private String notesHtml;

	@Column(name = "is_admin", nullable = false)
	private Boolean admin = false;

	@OneToMany
	@Getter
	private Set<RoleEntity> roles = new HashSet<>();

	public void addRole(RoleEntity... roles) {
		if (null == roles || roles.length == 0)
			return;

	}

	/**
	 * @param username
	 * @param email
	 * @param password
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param location
	 * @param statusText
	 * @param notesHtml
	 * @param admin
	 * @param roles
	 */
	private UserEntity(String username, String email, String password, String firstName, String middleName,
			String lastName, String location, String statusText, String notesHtml, Boolean admin) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.location = location;
		this.statusText = statusText;
		this.notesHtml = notesHtml;
		this.admin = admin;
		this.setCreatedAt(new Date());
	}

	/**
	 * 
	 */
	UserEntity() {
	}

	/**
	 * 
	 * @return
	 */
	public static UserEntityBuilder builder() {
		return new UserEntityBuilder();
	}

	public static class UserEntityBuilder {

		/**
		 * 
		 */
		private UserEntityBuilder() {
		}

		private String username;
		private String email;
		private String password;
		private String firstName;
		private String middleName;
		private String lastName;
		private String location;
		private String statusText;
		private String notesHtml;
		private boolean admin;

		/**
		 * 
		 * @param username
		 * @return
		 */
		public UserEntityBuilder username(String username) {
			this.username = username;
			return this;
		}

		/**
		 * 
		 * @param email
		 * @return
		 */
		public UserEntityBuilder email(String email) {
			this.email = email;
			return this;
		}

		/**
		 * 
		 * @param password
		 * @return
		 */
		public UserEntityBuilder password(String password) {
			this.password = password;
			return this;
		}

		/**
		 * 
		 * @param firstName
		 * @return
		 */
		public UserEntityBuilder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		/**
		 * 
		 * @param middleName
		 * @return
		 */
		public UserEntityBuilder middleName(String middleName) {
			this.middleName = middleName;
			return this;
		}

		/**
		 * 
		 * @param lastName
		 * @return
		 */
		public UserEntityBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		/**
		 * 
		 * @param location
		 * @return
		 */
		public UserEntityBuilder location(String location) {
			this.location = location;
			return this;
		}

		/**
		 * 
		 * @param statusText
		 * @return
		 */
		public UserEntityBuilder statusText(String statusText) {
			this.statusText = statusText;
			return this;
		}

		/**
		 * 
		 * @param notesHtml
		 * @return
		 */
		public UserEntityBuilder notesHtml(String notesHtml) {
			this.notesHtml = notesHtml;
			return this;
		}

		/**
		 * 
		 * @param admin
		 * @return
		 */
		public UserEntityBuilder admin(boolean admin) {
			this.admin = admin;
			return this;
		}

		public UserEntity build() {
			return new UserEntity(username, email, password, firstName, middleName, lastName, location, statusText,
					notesHtml, admin);
		}
	}
}
