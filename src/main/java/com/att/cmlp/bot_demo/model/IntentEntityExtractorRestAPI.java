/**
 * Copyright (C) 2017, AT&T Inc. All rights reserved. Proprietary materials, property of AT&T. For internal use only,
 * not for disclosure to parties outside of AT&T or its affiliates.
 */
package com.att.cmlp.bot_demo.model;

public class IntentEntityExtractorRestAPI {

	/**
	 * Id of the chatbot developer
	 */
	private String id;
	/**
	 * Text input provided by user
	 */
	private String text;


	/**
	 * Set the id
	 */
    public void setId(String id) {
		this.id = id;
	}
	/**
	 * Set the text
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * Get the id
	 */

    public String getId() {
        return id;
    }
	/**
	 * Get the text
	 */
    public String getText() {
        return text;
    }
}
