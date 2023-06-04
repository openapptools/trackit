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

import org.apache.commons.lang3.RandomStringUtils;
import org.opentracktools.trackit.domain.app.service.conversion.UserPayloadConversionService;
import org.opentracktools.trackit.domain.model.UserEntity;
import org.opentracktools.trackit.web.User;
import org.opentracktools.trackit.web.payload.UserPayload;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Arpan Mukhopadhyay
 *
 */
@Service
@Slf4j
public class UserPayloadConversionServiceImpl implements UserPayloadConversionService {

	// TODO check if a better and fast reflection approach is available

	@Override
	public UserEntity fromPayload(UserPayload userPayload) {
		UserEntity userEntity = UserEntity.builder().admin(false).email(userPayload.getEmail())
				.username(userPayload.getUsername()).password(RandomStringUtils.randomAlphanumeric(13)).build();
		return userEntity;
	}

	@Override
	public List<User> fromEntities(List<UserEntity> userEntities) {
		List<User> users = null;
		if (null != userEntities && userEntities.size() > 0) {
			users = userEntities.stream().map(t -> new User(t.getUsername(), t.getEmail()))
					.collect(Collectors.toList());
		}
		return users;
	}
}
