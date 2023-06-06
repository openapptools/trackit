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
package org.opentracktools.trackit.web.routes.user;

import java.util.List;

import org.opentracktools.trackit.domain.app.service.user.UserManagementService;
import org.opentracktools.trackit.domain.app.service.user.UserServiceResponse;
import org.opentracktools.trackit.web.User;
import org.opentracktools.trackit.web.payload.UserPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

/**
 * @author Arpan Mukhopadhyay
 *
 */
@Controller
@RequestMapping(path = { "/users" })
public class UserController {

	@Autowired
	private UserManagementService userManagementService;

	@GetMapping(path = "/new")
	public String create(Model model) {
		model.addAttribute("user", new UserPayload());
		return "users/new";
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(path = {"/", ""})
	public String index(Model model) {
		UserServiceResponse response = userManagementService.list();
		if (response.isSuccess()) {
			model.addAttribute("users", (List<User>) response.getResult());
		}
		return "users/index";
	}

	@PostMapping(path = "/new")
	public String create(@Valid @ModelAttribute(name = "user") UserPayload user, BindingResult result, Model model) {
		if (result.hasFieldErrors()) {
			model.addAttribute("user", user);
			return "users/new";
		}
		UserServiceResponse response = userManagementService.create(user);
		if (!response.isSuccess()) {
			String errorMsg = response.getMessage();
			// TODO find if we can update the result object and show it without a field.
			model.addAttribute("appError", errorMsg);
			return "users/new";
		}
		return "redirect:";
	}
}
