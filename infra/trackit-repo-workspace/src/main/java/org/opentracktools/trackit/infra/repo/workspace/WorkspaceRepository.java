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
package org.opentracktools.trackit.infra.repo.workspace;

import java.util.List;

import org.opentracktools.trackit.domain.model.WorkspaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Arpan Mukhopadhyay
 *
 */
public interface WorkspaceRepository extends JpaRepository<WorkspaceEntity, Long>, WorkspaceCustomRepository {

	/**
	 * 
	 * @return
	 */
	@Query("from WorkspaceEntity w where w.deleted = false")
	List<WorkspaceEntity> findAllActiveWorkspaces();

	/**
	 * 
	 * @param name
	 * @return
	 */
	@Query("from WorkspaceEntity w where w.deleted = false and w.name = :name ")
	WorkspaceEntity findOneByName(@Param("name") String name);
}
