/**
 * Copyright (C) 2017, AT&T Inc. All rights reserved. Proprietary materials, property of AT&T. For internal use only,
 * not for disclosure to parties outside of AT&T or its affiliates.
 */
package com.att.cmlp.common.nlp.extractentities;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;
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
public class EntityExtractorUsingOpenNLP {

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
	public EntityExtractorUsingOpenNLP(LanguageName language, ClassifierClass classifierClass) {
		this.language = language;
		this.classifierClass = classifierClass;
	}

	/**
	 * @param inputText
	 *            Input text name for entity extraction
	 */
	@SuppressWarnings("static-access")
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

			String propertyname = "opennlp_" + this.language + "_token";
			String resourceName = props.getProperty(propertyname);

			TokenizerModel tm = null;
			if (tm == null) {
				// Word tokenizer to split the words.
				// String resourcefile = RESOURCEFILE_PATH + "en-token.bin";
				stream = this.getClass().getResourceAsStream(RESOURCEFILE_PATH + resourceName);
				tm = new TokenizerModel(stream);

			}
			propertyname = "opennlp_" + this.language + "_"
					+ this.classifierClass.toString().substring(0, this.classifierClass.toString().indexOf("_"));

			// get the property
			resourceName = props.getProperty(propertyname);
			String[] resourcefiles = resourceName.split(",");
			TokenizerME wordBreaker = new TokenizerME(tm);
			TokenNameFinderModel[] findModel = new TokenNameFinderModel[resourcefiles.length];
			// Load all the resource files for the language
			for (int j = 0; j < findModel.length; j++) {
				findModel[j] = new TokenNameFinderModel(
						this.getClass().getResourceAsStream(RESOURCEFILE_PATH + resourcefiles[j]));
			}

			String tokens[] = wordBreaker.tokenize(inputText);

			for (int j = 0; j < findModel.length; j++) {
				NameFinderME myentityFinder = new NameFinderME(findModel[j]);
				
				Span nameSpans[] = myentityFinder.find(tokens);
				double spanProbs[]=myentityFinder.probs(nameSpans);
				int counter=0;

				for (Span s : nameSpans) {
					int start = s.getStart();
					int end = s.getEnd();

					String Name = "";

					for (int i = start; i < end; i++) {
						Name = Name.length() > 0 ? Name + " " + tokens[i] : tokens[i];
					}

					String ename = s.getType().toString().toUpperCase().trim();

					if (classifierClass.equals(classifierClass.class4_LocationPersonOrganizationMisc)
							&& ename.matches("MONEY|PERCENT|DATE|TIME")) {
						ename = "MISC";
					}

					Entity tmpdata = new Entity(Name, ename,spanProbs[counter]);
					if (!aList.contains(tmpdata))
						aList.add(tmpdata);

				}
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return aList;
	}

}