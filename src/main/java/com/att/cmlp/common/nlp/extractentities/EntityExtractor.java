/**
 * Copyright (C) 2017, AT&T Inc. All rights reserved. Proprietary materials, property of AT&T. For internal use only,
 * not for disclosure to parties outside of AT&T or its affiliates.
 */
package com.att.cmlp.common.nlp.extractentities;

import com.att.cmlp.common.nlp.extractentities.ClassifierClass;
import com.att.cmlp.common.nlp.extractentities.LanguageName;
import com.att.cmlp.common.nlp.extractentities.LibraryName;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the Entity Name Extraction Service.
 * <p>
 * This service will provide the list of entity names and entities for the given
 * input text or input file. End User can configure the Library name, Language
 * and type of entity classes
 * </p>
 * 
 * @author mn461x
 * @since Jan 6, 2017
 * @version $Id$
 */
public class EntityExtractor implements EntityExtractorInterface {

	/**
	 * The ClassifierClass for the EntityName classification
	 */
	private ClassifierClass classifierClass;
	/**
	 * The LanguageName for the EntityName classification
	 */
	private LanguageName language;
	/**
	 * The LibraryName for the EntityName classification
	 */
	private LibraryName libraryName;

	/**
	 * @param libraryName
	 *            Library name for Entity Classification
	 * @param language
	 *            Input text language name
	 * @param classifierClass
	 *            Class for entity name extraction
	 * @throws IOException
	 *             If the language is null
	 * @throws IOException
	 *             If the libraryName is null
	 * @throws EntityExtractorException
	 *             Invoke the custom error
	 */
	public EntityExtractor(LibraryName libraryName, LanguageName language, ClassifierClass classifierClass)
			throws EntityExtractorException {

		if (language == null) {
			throw new EntityExtractorException("Language should not be empty");
		}
		if (libraryName == null) {
			throw new EntityExtractorException("LibraryName should not be empty");
		}
		if (classifierClass == null) {
			throw new EntityExtractorException("ClassifierClass should not be empty");
		}
		this.libraryName = libraryName;
		this.language = language;
		this.classifierClass = classifierClass;
	}

	/**
	 * @param inputFilePath
	 *            Filepath for the input text
	 * @param inputFile
	 *            Input text file name
	 * @throws IOException
	 *             If the input file is empty or null
	 * @throws Exception
	 */
	public ArrayList<Entity> extractEntities(String inputFilePath, String inputFile) throws Exception {

		if (inputFilePath.isEmpty() || inputFilePath == null) {
			throw new RuntimeException("inputFilePath should not be empty");
		}
		if (inputFile.isEmpty() || inputFile == null) {
			throw new RuntimeException("inputFile should not be empty");
		}

		String path = inputFilePath + inputFile;

		List<String> lines = Files.readAllLines(Paths.get(path));
		StringBuilder sb = new StringBuilder();
		for (String line : lines) {
			if (sb.length() > 0) {
				sb.append("\n");
			}
			sb.append(line);
		}
		String inputText = sb.toString();

		ArrayList<Entity> al = null;
		try {
			if (libraryName == LibraryName.openNLP) {
				EntityExtractorUsingOpenNLP inst1 = new EntityExtractorUsingOpenNLP(language, classifierClass);
				al = inst1.entityExtract(inputText);
			} else if (libraryName == LibraryName.stanfordNLP) {
				EntityExtractorUsingStanfordNLP inst1 = new EntityExtractorUsingStanfordNLP(language, classifierClass);
				al = inst1.entityExtract(inputText);
			}
		} catch (Exception ex) {

		}
		return al;
	}

	/**
	 * @param inputText
	 *            inputText for finding EntityNames
	 * @throws Exception
	 */
	public ArrayList<Entity> extractEntities(String inputText) throws Exception {
		if (inputText.isEmpty() || inputText == null) {
			throw new RuntimeException("inString should not be empty");
		}

		ArrayList<Entity> al = null;
		try {
			if (libraryName == LibraryName.openNLP) {
				EntityExtractorUsingOpenNLP inst1 = new EntityExtractorUsingOpenNLP(language, classifierClass);
				al = inst1.entityExtract(inputText);
			} else if (libraryName == LibraryName.stanfordNLP) {
				EntityExtractorUsingStanfordNLP inst1 = new EntityExtractorUsingStanfordNLP(language, classifierClass);
				al = inst1.entityExtract(inputText);
			}
		} catch (Exception ex) {
		}
		return al;
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
//			// create a instance for openNLP
//			EntityExtractor inst1 = new EntityExtractor(LibraryName.openNLP, LanguageName.english,
//					ClassifierClass.class3_LocationPersonOrganization);
//			ArrayList<Entity> alists = inst1.extractEntities(inText);
//			List<Entity> entities = alists;
//			for (Entity item : entities) {
//				System.out.println("Entity Name: " + item.getEntityName() + ", Entity Type: " + item.getEntityType()+ ", Confidence Interval: " + item.getConfidenceInterval());
//			}
//
//			// create a instance for stanfordNLP
//			inst1 = new EntityExtractor(LibraryName.stanfordNLP, LanguageName.english,
//					ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime);
//			alists = inst1.extractEntities(inText);
//			entities = alists;
//			for (Entity item : entities) {
//				System.out.println("Entity Name:" + item.getEntityName() + ", Entity Type:" + item.getEntityType());
//			}
//			// create a instance for openNLP - File name as parameter
//			inst1 = new EntityExtractor(LibraryName.openNLP, LanguageName.english,
//					ClassifierClass.class4_LocationPersonOrganizationMisc);
//			alists = inst1.extractEntities("C:\\Works\\", "textsample.txt");
//			entities = alists;
//			for (Entity item : entities) {
//				System.out.println("Entity Name: " + item.getEntityName() + ", Entity Type: " + item.getEntityType()+ ", Confidence Interval: " + item.getConfidenceInterval());
//			}
//			// create a instance for stanfordNLP
//			inst1 = new EntityExtractor(LibraryName.stanfordNLP, LanguageName.english,
//					ClassifierClass.class4_LocationPersonOrganizationMisc);
//
//			alists = inst1.extractEntities("C:\\Works\\", "textsample.txt");
//			entities = alists;
//			for (Entity item : entities) {
//				System.out.println("Entity Name:" + item.getEntityName() + ", Entity Type:" + item.getEntityType());
//			}
//		}
//
//		catch (EntityExtractorException ex) {
//			System.out.println(ex.getMessage());
//		}
//
//		catch (IOException ex) {
//			System.out.println(ex.getMessage());
//		} catch (Exception ex) {
//			System.out.println(ex.getMessage());
//		}
//	}
}