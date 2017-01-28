
/**
 * Copyright (C) 2017, AT&T Inc. All rights reserved. Proprietary materials, property of AT&T. For internal use only,
 * not for disclosure to parties outside of AT&T or its affiliates.
 */
package com.att.cmlp.common.nlp.extractentities;

import java.io.IOException;
import java.util.ArrayList;


/**
 * This is the Entity Name Extraction Service Interface.
 * <p>
 * This Interface will provide the list of entity names and entities for the
 * given input text or input file. End User can configure the Library name,
 * Language and type of entity classes
 * </p>
 * 
 * @author mn461x
 * @since Jan 6, 2017
 * @version $Id$
 */
interface EntityExtractorInterface {
	/**
	 * @param inputFilePath
	 *            Filepath for the input text
	 * @param inputFile
	 *            Input text file name
	 * @throws RuntimeException
	 * @throws IOException
	 * @throws EntityExtractorException
	 * @throws Exception
	 */
	public ArrayList<Entity> extractEntities(String inputFilePath, String inputFile)
			throws EntityExtractorException, IOException, RuntimeException, Exception;

	/**
	 * @param inputText
	 *            inputText for finding EntityNames
	 * @throws RuntimeException
	 * @throws EntityExtractorException
	 * @throws IOException
	 * @throws Exception
	 */
	public ArrayList<Entity>  extractEntities(String inputText)
			throws IOException, EntityExtractorException, RuntimeException, Exception;
}
