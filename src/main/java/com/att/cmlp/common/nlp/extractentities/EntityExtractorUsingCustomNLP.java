/**
 * Copyright (C) 2017, AT&T Inc. All rights reserved. Proprietary materials, property of AT&T. For internal use only,
 * not for disclosure to parties outside of AT&T or its affiliates.
 */
package com.att.cmlp.common.nlp.extractentities;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import org.json.JSONArray;

import com.att.cmlp.common.nlp.extractentities.ClassifierClass;
import com.att.cmlp.common.nlp.extractentities.LanguageName;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

/**
 * This is the Entity Name Extraction interface using ATT Resource file.
 * <p>
 * This service will provide the list of entity names and entities for the given
 * input text using ATT Resource files End User can configure the Language and
 * type of entity classes
 * </p>
 * 
 * @author mn461x
 * @since Jan 6, 2017
 * @version $Id$
 */
public class EntityExtractorUsingCustomNLP {
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
	public EntityExtractorUsingCustomNLP(LanguageName language, ClassifierClass classifierClass) {
		this.language = language;
		this.classifierClass = classifierClass;
	}

	/**
	 * @param inputText
	 *            Input text name for entity extraction
	 */
	@SuppressWarnings("static-access")
	public JSONArray entityExtract(String inputText) throws EntityExtractorException {
		JSONArray jarray = null;

		try {

			Properties props = new Properties();
			InputStream stream = this.getClass().getResourceAsStream(RESOURCEFILE_PATH + "config.properties");

			if (stream != null) {
				props.load(stream);
			} else {
				throw new EntityExtractorException("Property file config.properties not found");
			}

			String propertyname = "customNLP_" + this.language + "_token";
			String resourceName = props.getProperty(propertyname);

			TokenizerModel tm = null;
			if (tm == null) {
				// Word tokenizer to split the words.
				// String resourcefile = RESOURCEFILE_PATH + "en-token.bin";
				stream = this.getClass().getResourceAsStream(RESOURCEFILE_PATH + resourceName);
				tm = new TokenizerModel(stream);

			}
			TokenizerME wordBreaker = new TokenizerME(tm);
			/*
			 * propertyname = "customNLP_" + this.language + "_" +
			 * this.classifierClass.toString().substring(0,
			 * this.classifierClass.toString().indexOf("_"));
			 * 
			 * // get the property resourceName =
			 * props.getProperty(propertyname); String[] resourcefiles =
			 * resourceName.split(",");
			 * 
			 * 
			 * TokenNameFinderModel[] findModel = new
			 * TokenNameFinderModel[resourcefiles.length]; // Load all the
			 * resource files for the language for (int j = 0; j <
			 * findModel.length; j++) { findModel[j] = new TokenNameFinderModel(
			 * this.getClass().getResourceAsStream(RESOURCEFILE_PATH +
			 * resourcefiles[j])); }
			 */

			String tokens[] = wordBreaker.tokenize(inputText);
			for (int i = 0; i < 25; i++)
				System.out.println(tokens[i]);
			/*
			 * ArrayList<Map<String, String>> aList = new ArrayList<Map<String,
			 * String>>();
			 * 
			 * for (int j = 0; j < findModel.length; j++) { NameFinderME
			 * myentityFinder = new NameFinderME(findModel[j]); Span nameSpans[]
			 * = myentityFinder.find(tokens);
			 * 
			 * for (Span s : nameSpans) { int start = s.getStart(); int end =
			 * s.getEnd();
			 * 
			 * String Name = "";
			 * 
			 * for (int i = start; i < end; i++) { Name = Name.length() > 0 ?
			 * Name + " " + tokens[i] : tokens[i]; }
			 * 
			 * Map<String, String> tmpdata = new Hashtable<String, String>();
			 * String ename = s.getType().toString().toUpperCase().trim();
			 * 
			 * if (classifierClass.equals(classifierClass.
			 * class4_LocationPersonOrganizationMisc) &&
			 * ename.matches("MONEY|PERCENT|DATE|TIME")) { ename = "MISC"; }
			 * tmpdata.put(Name, ename);
			 * 
			 * if (!aList.contains(tmpdata)) aList.add(tmpdata);
			 * 
			 * } }
			 * 
			 * if (!aList.isEmpty()) { jarray = new JSONArray(aList); return
			 * jarray; }
			 */
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return jarray;
	}

//	public static void main(String[] args) {
//		try {
//
//			String inText = "One year ago, several hours before cities across the United States started their annual fireworks displays, a different type of fireworks were set off at the "
//					+ "European Center for Nuclear Research (CERN) in Switzerland. At 9:00 a.m., physicists announced to the world that they had found something they had been searching for "
//					+ "nearly 50 years: the elusive Higgs boson. Today, on the anniversary of its discovery, are we any closer to figuring out what that particle's true identity is? The Higgs "
//					+ "boson is popularly referred to as the God particle, perhaps because of its role in giving other particles their mass. However, it's not the boson itself that gives mass."
//					+ " Back in 1964, Peter Higgs proposed a theory that described a universal field (similar to an electric or a magnetic field) that particles interacted with.";
//
//			// create a instance for CustomNLP
//			EntityExtractorUsingCustomNLP inst1 = new EntityExtractorUsingCustomNLP(LanguageName.english,
//					ClassifierClass.class3_LocationPersonOrganization);
//			JSONArray jr = inst1.entityExtract(inText);
//
//		}
//
//		catch (EntityExtractorException ex) {
//			System.out.println(ex.getMessage());
//		} catch (Exception ex) {
//			System.out.println(ex.getMessage());
//		}
//	}

}
