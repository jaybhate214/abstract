/**
 * Copyright (C) 2017, AT&T Inc. All rights reserved. Proprietary materials, property of AT&T. For internal use only,
 * not for disclosure to parties outside of AT&T or its affiliates.
 */
package com.att.cmlp.common.nlp.extractentities;

/**
 * This is the Custom Exception.
 * <p>
 * This service will custom exception class features
 * </p>
 * 
 * @author mn461x
 * @since Jan 6, 2017
 * @version $Id$
 */
@SuppressWarnings("serial")
public class EntityExtractorException extends Exception {

	/**
	 * Default constructor
	 */
	public EntityExtractorException() {
		super();
	}

	/**
	 * @param Message
	 *            Custom message is going to display
	 */
	public EntityExtractorException(String Message) {
		super(Message);
	}

}
