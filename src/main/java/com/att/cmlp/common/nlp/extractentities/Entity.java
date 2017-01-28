/**
 * Copyright (C) 2017, AT&T Inc. All rights reserved. Proprietary materials, property of AT&T. For internal use only,
 * not for disclosure to parties outside of AT&T or its affiliates.
 */
package com.att.cmlp.common.nlp.extractentities;

public class Entity {

	/**
	 * Name of the entity
	 */
	private String EntityName;
	/**
	 * Type of the entity
	 */
	private String EntityType;
	/**
	 * The library classification confidence interval
	 */
	private double ConfidenceInterval;

	/**
	 * Get the EntityName
	 */
	public String getEntityName() {
		return this.EntityName;
	}

	/**
	 * Get the EntityType
	 */
	public String getEntityType() {
		return this.EntityType;
	}

	/**
	 * Get the ConfidenceInterval
	 */
	public double getConfidenceInterval() {
		return this.ConfidenceInterval;
	}

	/**
	 * @param Name
	 *            Entity Name
	 * @param Type
	 *            Entity Type
	 * @param Confidence
	 *            Interval Entity Type
	 */
	public Entity(String EntityName, String EntityType, double ConfidenceInterval) throws EntityExtractorException {

		if (EntityName == null || EntityName.isEmpty()) {
			throw new EntityExtractorException("Name should not be empty");
		}
		if (EntityType == null || EntityType.isEmpty()) {
			throw new EntityExtractorException("Type should not be empty");
		}
		if (ConfidenceInterval <=0) {
			throw new EntityExtractorException("ConfidenceInterval should not be empty");
		}
		this.EntityName = EntityName;
		this.EntityType = EntityType;
		this.ConfidenceInterval = ConfidenceInterval;
	}

	/**
	 * @param Name
	 *            Entity Name
	 * @param Type
	 *            Entity Type
	 */
	public Entity(String EntityName, String EntityType) throws EntityExtractorException {

		if (EntityName == null || EntityName.isEmpty()) {
			throw new EntityExtractorException("Name should not be empty");
		}
		if (EntityType == null || EntityType.isEmpty()) {
			throw new EntityExtractorException("Type should not be empty");
		}
		this.EntityName = EntityName;
		this.EntityType = EntityType;
	}
}
