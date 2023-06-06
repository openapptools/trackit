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
package org.opentracktools.trackit.domain.app.service.impl.conversion;

import java.util.List;
import java.util.stream.Collectors;

import org.opentracktools.trackit.domain.app.service.conversion.WorkspacePayloadConversionService;
import org.opentracktools.trackit.domain.model.WorkspaceEntity;
import org.opentracktools.trackit.web.Workspace;
import org.opentracktools.trackit.web.payload.WorkspacePayload;
import org.springframework.stereotype.Service;

/**
 * @author Arpan Mukhopadhyay
 *
 */
@Service
public class WorkspacePayloadConversionServiceImpl implements WorkspacePayloadConversionService {

	@Override
	public WorkspaceEntity fromPayload(WorkspacePayload workspacePayload) {
		WorkspaceEntity workspaceEntity = WorkspaceEntity.builder().name(workspacePayload.getName())
				.description(workspacePayload.getDescription()).type(workspacePayload.getType().toUpperCase()).build();
		return workspaceEntity;
	}

	@Override
	public List<Workspace> fromEntities(List<WorkspaceEntity> workspaceEntities) {
		List<Workspace> workspaces = null;
		if (null != workspaceEntities && workspaceEntities.size() > 0) {
			workspaces = workspaceEntities.stream().map(t -> new Workspace(t.getName(), t.getDescription(),
					t.getOwner().getUsername(), t.getType().name().toLowerCase())).collect(Collectors.toList());
		}
		return workspaces;
	}
}
