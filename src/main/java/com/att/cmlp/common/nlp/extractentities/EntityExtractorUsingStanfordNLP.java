/**
 * Copyright (C) 2017, AT&T Inc. All rights reserved. Proprietary materials, property of AT&T. For internal use only,
 * not for disclosure to parties outside of AT&T or its affiliates.
 */
package com.att.cmlp.common.nlp.extractentities;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;

import com.att.cmlp.common.nlp.extractentities.ClassifierClass;
import com.att.cmlp.common.nlp.extractentities.LanguageName;

/**
 * This is the Entity Name Extraction interface using OpenNLP.
 * <p>
 * This service will provide the list of entity names and entities for the given
 * input text using OpenNLP library End User can configure the Language and type
 * of entity classes
 * </p>
 * 
 * @author mn461x
 * @since Jan 6, 2017
 * @version $Id$
 */
public class EntityExtractorUsingStanfordNLP {

	/**
	 * The ClassifierClass for the EntityName classification
	 */
	private ClassifierClass classifierClass;
	/**
	 * The LanguageName for the EntityName classification
	 */
	private LanguageName language;

	/**
	 * Resource File folder path
	 */
	private static final String RESOURCEFILE_PATH = "../../../../../../";

	/**
	 * @param language
	 *            Input text language name
	 * @param classifierClass
	 *            Class for entity name extraction
	 */
	public EntityExtractorUsingStanfordNLP(LanguageName language, ClassifierClass classifierClass) {

		this.language = language;
		this.classifierClass = classifierClass;
	}

	/**
	 * @param inputText
	 *            Input text name for entity extraction
	 */

	public ArrayList<Entity> entityExtract(String inputText) throws EntityExtractorException {
		ArrayList<Entity> aList = new ArrayList<Entity>();
		try {
			Properties props = new Properties();
			InputStream stream = this.getClass().getResourceAsStream(RESOURCEFILE_PATH + "config.properties");

			if (stream != null) {
				props.load(stream);
			} else {
				throw new EntityExtractorException("Property file config.properties not found");
			}

			String propertyname = "stanfordNLP_" + this.language + "_"
					+ this.classifierClass.toString().substring(0, this.classifierClass.toString().indexOf("_"));

			// get the property
			String resourceName = props.getProperty(propertyname);
			String classifierPath = Paths.get(this.getClass().getResource(RESOURCEFILE_PATH + resourceName).toURI())
					.toString();

			CRFClassifier<CoreLabel> classifier = CRFClassifier.getClassifierNoExceptions(classifierPath);

			String classifierOutput = classifier.classifyWithInlineXML(inputText);
			// Filter the XML elements from output
			String searchDoc = classifierOutput;
			int lessthantagIndex = searchDoc.indexOf('<');
			int gtthantagIndex = searchDoc.indexOf('>');

			while (searchDoc.length() > 0) {
				String ename = searchDoc.substring(lessthantagIndex + 1, gtthantagIndex);
				lessthantagIndex = searchDoc.indexOf("</");
				String word = searchDoc.substring(gtthantagIndex + 1, lessthantagIndex);
				searchDoc = searchDoc.substring((ename.length() + word.length() + gtthantagIndex + 4),
						searchDoc.lastIndexOf('>') + 1);
				lessthantagIndex = searchDoc.indexOf('<');
				gtthantagIndex = searchDoc.indexOf('>');

				Entity tmpdata = new Entity(word, ename);

				if (!aList.contains(tmpdata))
					aList.add(tmpdata);
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return aList;
	}

}
