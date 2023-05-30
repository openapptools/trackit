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
package org.opentracktools.trackit.domain.app.service.impl.user;

import java.util.List;

import org.opentracktools.trackit.domain.app.service.conversion.UserPayloadConversionService;
import org.opentracktools.trackit.domain.app.service.user.UserManagementService;
import org.opentracktools.trackit.domain.app.service.user.UserServiceResponse;
import org.opentracktools.trackit.domain.model.UserEntity;
import org.opentracktools.trackit.infra.repo.user.UserManagementRepository;
import org.opentracktools.trackit.web.payload.UserPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Arpan Mukhopadhyay
 *
 */
@Service
@Slf4j
public class UserManagementServiceImpl implements UserManagementService {

	@Autowired
	private UserManagementRepository userRepository;

	@Autowired
	private UserPayloadConversionService userConversionService;

	public void noops() {
		System.out.println("No ops method");
	}

	@Override
	public UserServiceResponse list() {
		UserServiceResponse response = null;
		List<UserEntity> activeUsers = userRepository.findAllActiveUsers();
		log.info("{}\n", activeUsers);
		if (activeUsers != null && activeUsers.size() > 0) {
			response = UserServiceResponse.success(userConversionService.fromUserEntities(activeUsers));
			response.setCode(200);
		} else {
			response = UserServiceResponse.error(null);
			response.setCode(404);
		}
		return response;
	}

	@Override
	public UserServiceResponse create(UserPayload payload) {
		// Before proceeding check if the user already exists.
		UserEntity existingUserEntity = userRepository.findByUsernameOrEmail(payload.getUsername(), payload.getEmail());
		if (null != existingUserEntity) {
			UserServiceResponse errorResponse = UserServiceResponse.error(payload);
			errorResponse.setMessage("User already exists");
			return errorResponse;
		}
		UserEntity userEntity = userConversionService.fromPayload(payload);
		UserEntity createdEntity = userRepository.save(userEntity);
		if (createdEntity == null) {
			UserServiceResponse errorResponse = UserServiceResponse.error(payload);
			errorResponse.setMessage("An error occurred while creating user. Try later.");
		}
		return UserServiceResponse.created(createdEntity);
	}
}
