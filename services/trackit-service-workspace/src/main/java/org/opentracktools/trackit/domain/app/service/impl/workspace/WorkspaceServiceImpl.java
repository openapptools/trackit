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
package org.opentracktools.trackit.domain.app.service.impl.workspace;

import java.util.List;

import org.opentracktools.trackit.domain.app.service.conversion.WorkspacePayloadConversionService;
import org.opentracktools.trackit.domain.app.service.workspace.WorkspaceService;
import org.opentracktools.trackit.domain.app.service.workspace.WorkspaceServiceResponse;
import org.opentracktools.trackit.domain.model.UserEntity;
import org.opentracktools.trackit.domain.model.WorkspaceEntity;
import org.opentracktools.trackit.infra.repo.user.UserManagementRepository;
import org.opentracktools.trackit.infra.repo.workspace.WorkspaceRepository;
import org.opentracktools.trackit.web.payload.WorkspacePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Arpan Mukhopadhyay
 *
 */
@Service
@Slf4j
public class WorkspaceServiceImpl implements WorkspaceService {

	@Autowired
	private WorkspaceRepository workspaceRepository;

	@Autowired
	private UserManagementRepository userRepository;

	@Autowired
	private WorkspacePayloadConversionService workspaceConversionService;

	@Override
	public WorkspaceServiceResponse list() {
		List<WorkspaceEntity> workspaces = workspaceRepository.findAllActiveWorkspaces();

		WorkspaceServiceResponse response = WorkspaceServiceResponse.error(null);
		response.setCode(404);
		logger.info("{}", workspaces);
		if (null != workspaces && workspaces.size() > 0) {
			response.setResult(workspaceConversionService.fromEntities(workspaces));
			response.setSuccess(true);
			response.setCode(200);
		} else {
			response = WorkspaceServiceResponse.error(null);
			response.setCode(404);
		}
		return response;
	}

	@Transactional
	@Override
	public WorkspaceServiceResponse create(WorkspacePayload payload) {
		WorkspaceServiceResponse response = WorkspaceServiceResponse.error(null);
		response.setCode(500);

		WorkspaceEntity existingEntity = workspaceRepository.findOneByName(payload.getName());
		if (null != existingEntity) {
			response = WorkspaceServiceResponse.error(payload);
			response.setCode(409);
			response.setMessage("Workspace with same name already exist.");
			return response;
		}

		String owner = payload.getOwner();
		if (null != owner) {
			UserEntity ownerEntity = userRepository.findByUsername(owner);
			if (null != ownerEntity) {
				WorkspaceEntity workspaceEntity = workspaceConversionService.fromPayload(payload);
				workspaceEntity.setOwner(ownerEntity);
				try {
					workspaceRepository.save(workspaceEntity);
					response.setSuccess(true);
					response.setCode(302);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				response.setCode(400);
				response.setMessage("Invalid owner");
			}
		} else {
			response.setCode(400);
			response.setMessage("Invalid owner " + owner);
		}
		return response;
	}

}
