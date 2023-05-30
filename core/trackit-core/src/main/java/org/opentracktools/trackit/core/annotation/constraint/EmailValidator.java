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
package org.opentracktools.trackit.core.annotation.constraint;

import org.opentracktools.trackit.core.annotation.Email;

import jakarta.validation.ConstraintValidatorContext;

/**
 * @author Arpan Mukhopadhyay
 *
 */
public class EmailValidator extends AbstractEmailValidator<Email> {

	private final int MAX_LEN = 32; // Default max length can be changed via configuration.
	private final int MIN_LEN = 6; // We are considering 'a@b.cd' as shortest email not '@'. But default min length
									// can be changed via configuration.

	// TODO - Get the default email length from the configuration during
	// installation
	private int minLen = MIN_LEN;
	private int maxLen = MAX_LEN;

	@Override
	public void initialize(Email emailAnnotation) {
		super.initialize(emailAnnotation);
	}

	@Override
	public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
		if (null == value || value.isEmpty()) return false;
		if (value.length() < minLen || value.length() > maxLen) return false;
		
		return false;
	}

}
