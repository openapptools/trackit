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
package org.opentracktools.trackit.domain.app.service.workspace;

import org.opentracktools.trackit.domain.app.service.ServiceResponse;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Arpan Mukhopadhyay
 *
 */
@Getter
@Setter
@ToString
public class WorkspaceServiceResponse extends ServiceResponse {

	/**
	 * 
	 */
	private WorkspaceServiceResponse() {
		super();
	}

	/**
	 * 
	 * @param code
	 * @param success
	 */
	private WorkspaceServiceResponse(int code, boolean success) {
		super(code, success);
	}

	/**
	 * 
	 * @param result
	 * @return
	 */
	public static WorkspaceServiceResponse success(Object result) {
		WorkspaceServiceResponse successResponse = new WorkspaceServiceResponse(200, true);
		successResponse.setResult(result);
		return successResponse;
	}

	/**
	 * 
	 * @param result
	 * @return
	 */
	public static WorkspaceServiceResponse created(Object result) {
		WorkspaceServiceResponse createdResponse = new WorkspaceServiceResponse(302, true);
		createdResponse.setResult(result);
		return createdResponse;
	}

	/**
	 * 
	 * @param result
	 * @return
	 */
	public static WorkspaceServiceResponse error(Object result) {
		WorkspaceServiceResponse errorResponse = new WorkspaceServiceResponse(500, false);
		errorResponse.setResult(result);
		return errorResponse;
	}

}
