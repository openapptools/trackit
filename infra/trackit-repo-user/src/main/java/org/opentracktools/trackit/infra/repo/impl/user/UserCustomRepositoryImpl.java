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
package org.opentracktools.trackit.infra.repo.impl.user;

import org.opentracktools.trackit.infra.repo.user.UserCustomRepository;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * @author Arpan Mukhopadhyay
 *
 */
@Repository
public class UserCustomRepositoryImpl implements UserCustomRepository {

	// For custom persistence method we will use criteria instead of jpql

	@PersistenceContext
	private EntityManager entityManager;

}
