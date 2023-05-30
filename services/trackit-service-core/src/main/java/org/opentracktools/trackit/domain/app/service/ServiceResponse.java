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
package org.opentracktools.trackit.domain.app.service;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Arpan Mukhopadhyay
 *
 */
@Getter
@Setter
public abstract class ServiceResponse {

	private int code; // Equivalent HTTP code
	private boolean success;
	private String message;
	private Object result;

	public ServiceResponse() {
		this.code = -1;
		this.success = false;
	}

	/**
	 * 
	 * @param code
	 * @param success
	 */
	public ServiceResponse(int code, boolean success) {
		this.code = code;
		this.success = success;
	}

	/**
	 * 
	 * @param code
	 * @param success
	 * @param message
	 */
	public ServiceResponse(int code, boolean success, String message) {
		this.code = code;
		this.success = success;
		this.message = message;
	}

}
