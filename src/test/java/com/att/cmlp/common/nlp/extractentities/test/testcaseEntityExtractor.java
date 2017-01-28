package com.att.cmlp.common.nlp.extractentities.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.att.cmlp.common.nlp.extractentities.ClassifierClass;
import com.att.cmlp.common.nlp.extractentities.LanguageName;
import com.att.cmlp.common.nlp.extractentities.LibraryName;

import junit.framework.Assert;

import com.att.cmlp.common.nlp.extractentities.EntityExtractor;
import com.att.cmlp.common.nlp.extractentities.EntityExtractorException;
import com.att.cmlp.common.nlp.extractentities.Entity;

public class testcaseEntityExtractor {

	@Test // openNLP- InString Empty Validation
	public void testEntityExtractopenNLP_InStringParamEmptyValidation() {
		String inText = "";

		Throwable e = null;
		try {
			// create a instance for openNLP
			EntityExtractor inst = new EntityExtractor(LibraryName.openNLP, LanguageName.english,
					ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime);
			inst.extractEntities(inText);
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof RuntimeException);

	}

	@Test // openNLP- InString Null Validation
	public void testEntityExtractopenNLP_InStringParamNullValidation() {
		String inText = null;

		Throwable e = null;
		try {
			// create a instance for openNLP
			EntityExtractor inst = new EntityExtractor(LibraryName.openNLP, LanguageName.english,
					ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime);
			inst.extractEntities(inText);
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof RuntimeException);

	}

	@Test // openNLP- LibraryName null Validation
	public void testEntityExtractopenNLP_LibararyNameNullValidation() {
		String inText = "Pierre Vinken , 61 years old , will join the board as a nonexecutive director Nov. 29 . Mr . Vinken is chairman of Elsevier N.V. , the Dutch publishing group ."
				+ "Rudolph Agnew , 55 years old and former chairman of Consolidated Gold Fields PLC , was named a director of this British industrial conglomerate .";

		Throwable e = null;
		try {
			// create a instance for openNLP
			EntityExtractor inst = new EntityExtractor(null, LanguageName.english,
					ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime);
			inst.extractEntities(inText);
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof EntityExtractorException);

	}

	@Test // openNLP- LanguageName null Validation
	public void testEntityExtractopenNLP_LanguageNameNullValidation() {
		String inText = "Pierre Vinken , 61 years old , will join the board as a nonexecutive director Nov. 29 . Mr . Vinken is chairman of Elsevier N.V. , the Dutch publishing group ."
				+ "Rudolph Agnew , 55 years old and former chairman of Consolidated Gold Fields PLC , was named a director of this British industrial conglomerate .";

		Throwable e = null;
		try {
			// create a instance for openNLP
			EntityExtractor inst = new EntityExtractor(LibraryName.openNLP, null,
					ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime);
			inst.extractEntities(inText);
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof EntityExtractorException);

	}

	@Test // openNLP- ClassifierClass null Validation
	public void testEntityExtractopenNLP_ClassifierClassNullValidation() {
		String inText = "Pierre Vinken , 61 years old , will join the board as a nonexecutive director Nov. 29 . Mr . Vinken is chairman of Elsevier N.V. , the Dutch publishing group ."
				+ "Rudolph Agnew , 55 years old and former chairman of Consolidated Gold Fields PLC , was named a director of this British industrial conglomerate .";

		Throwable e = null;
		try {
			// create a instance for openNLP
			EntityExtractor inst = new EntityExtractor(LibraryName.openNLP, LanguageName.english, null);
			inst.extractEntities(inText);
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof EntityExtractorException);

	}

	@Test // openNLP- Output Validation
	public void testEntityExtractopenNLP_OutputValidation() {
		String inText = "Pierre Vinken , 61 years old , will join the board as a nonexecutive director Nov. 29 . Mr . Vinken is chairman of Elsevier N.V. , the Dutch publishing group ."
				+ "Rudolph Agnew , 55 years old and former chairman of Consolidated Gold Fields PLC , was named a director of this British industrial conglomerate .";

		ArrayList<Entity> al = null;
		try {
			// create a instance for openNLP
			EntityExtractor inst1 = new EntityExtractor(LibraryName.openNLP, LanguageName.english,
					ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime);
			al = inst1.extractEntities(inText);
		} catch (Throwable ex) {

		}

		assertTrue(al.get(0) instanceof Entity);

	}

	@Test // openNLP- inputFilePath Empty Validation
	public void testEntityExtractopenNLPUsingInputFile_inputFilePathParamEmptyValidation() {

		Throwable e = null;
		try {
			// create a instance for openNLP
			EntityExtractor inst = new EntityExtractor(LibraryName.openNLP, LanguageName.english,
					ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime);
			inst.extractEntities("", "textsample.txt");
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof RuntimeException);

	}

	@Test // openNLP- inputFilePath Null Validation
	public void testEntityExtractopenNLPUsingInputFile_inputFilePathParamNullValidation() {

		Throwable e = null;
		try {
			// create a instance for openNLP
			EntityExtractor inst = new EntityExtractor(LibraryName.openNLP, LanguageName.english,
					ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime);
			inst.extractEntities(null, "textsample.txt");
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof RuntimeException);

	}

	@Test // openNLP- inputFile Empty Validation
	public void testEntityExtractopenNLPUsingInputFile_inputFileParamEmptyValidation() {

		Throwable e = null;
		try {
			// create a instance for openNLP
			EntityExtractor inst = new EntityExtractor(LibraryName.openNLP, LanguageName.english,
					ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime);
			inst.extractEntities("C:\\Works\\", "");
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof RuntimeException);

	}

	@Test // openNLP- inputFile Null Validation
	public void testEntityExtractopenNLPUsingInputFile_inputFileParamNullValidation() {

		Throwable e = null;
		try {
			// create a instance for openNLP
			EntityExtractor inst = new EntityExtractor(LibraryName.openNLP, LanguageName.english,
					ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime);
			inst.extractEntities("C:\\Works\\", null);
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof RuntimeException);
	}

	@Test // openNLP- LibraryName null Validation
	public void testEntityExtractopenNLPUsingInputFile_LibararyNameNullValidation() {
		Throwable e = null;
		try {
			// create a instance for openNLP
			EntityExtractor inst = new EntityExtractor(null, LanguageName.english,
					ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime);
			inst.extractEntities("C:\\Works\\", "textsample.txt");
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof EntityExtractorException);

	}

	@Test // openNLP- LanguageName null Validation
	public void testEntityExtractopenNLPUsingInputFile_LanguageNameNullValidation() {

		Throwable e = null;
		try {
			// create a instance for openNLP
			EntityExtractor inst = new EntityExtractor(LibraryName.openNLP, null,
					ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime);
			inst.extractEntities("C:\\Works\\", "textsample.txt");
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof EntityExtractorException);

	}

	@Test // openNLP- ClassifierClass null Validation
	public void testEntityExtractopenNLPUsingInputFile_ClassifierClassNullValidation() {

		Throwable e = null;
		try {
			// create a instance for openNLP
			EntityExtractor inst = new EntityExtractor(LibraryName.openNLP, LanguageName.english, null);
			inst.extractEntities("C:\\Works\\", "textsample.txt");
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof EntityExtractorException);

	}

	@Test // openNLP- Validate the output is JSON - Class 7 classifier
	public void testEntityExtractopenNLPUsingInputFile_Class7OutputValidation() {

		ArrayList<Entity> al = null;
		try {
			// create a instance for openNLP
			EntityExtractor inst1 = new EntityExtractor(LibraryName.openNLP, LanguageName.english,
					ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime);
			al = inst1.extractEntities("C:\\Works\\", "textsample.txt");
		} catch (Throwable ex) {
		}

		assertTrue(al.get(0) instanceof Entity);

	}

	@Test // openNLP- Validate the output is JSON - Class 4 classifier
	public void testEntityExtractopenNLPUsingInputFile_Class4OutputValidation() {

		ArrayList<Entity> al = null;
		try {
			// create a instance for openNLP
			EntityExtractor inst1 = new EntityExtractor(LibraryName.openNLP, LanguageName.english,
					ClassifierClass.class4_LocationPersonOrganizationMisc);
			al = inst1.extractEntities("C:\\Works\\", "textsample.txt");
		} catch (Throwable ex) {
		}

		assertTrue(al.get(0) instanceof Entity);

	}

	@Test // openNLP- Validate the output is JSON - Class 3 classifier
	public void testEntityExtractopenNLPUsingInputFile_Class3OutputValidation() {

		ArrayList<Entity> al = null;
		try {
			// create a instance for openNLP
			EntityExtractor inst1 = new EntityExtractor(LibraryName.openNLP, LanguageName.english,
					ClassifierClass.class3_LocationPersonOrganization);
			al = inst1.extractEntities("C:\\Works\\", "textsample.txt");
		} catch (Throwable ex) {
		}

		assertTrue(al.get(0) instanceof Entity);

	}

	@Test // StanfordNLP InString Empty Validation
	public void testEntityExtractstanfordNLP_InStringParamEmptyValidation() {
		String inText = "";

		Throwable e = null;
		try {
			// create a instance for stanfordNLP
			EntityExtractor inst = new EntityExtractor(LibraryName.stanfordNLP, LanguageName.english,
					ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime);
			inst.extractEntities(inText);
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof RuntimeException);

	}

	@Test // StanfordNLP InString Null Validation
	public void testEntityExtractstanfordNLP_InStringParamNullValidation() {
		String inText = null;

		Throwable e = null;
		try {
			// create a instance for stanfordNLP
			EntityExtractor inst = new EntityExtractor(LibraryName.stanfordNLP, LanguageName.english,
					ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime);
			inst.extractEntities(inText);
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof RuntimeException);

	}

	@Test // StanfordNLP LibraryName null Validation
	public void testEntityExtractstanfordNLP_LibararyNameNullValidation() {
		String inText = "Pierre Vinken , 61 years old , will join the board as a nonexecutive director Nov. 29 . Mr . Vinken is chairman of Elsevier N.V. , the Dutch publishing group ."
				+ "Rudolph Agnew , 55 years old and former chairman of Consolidated Gold Fields PLC , was named a director of this British industrial conglomerate .";

		Throwable e = null;
		try {
			// create a instance for stanfordNLP
			EntityExtractor inst = new EntityExtractor(null, LanguageName.english,
					ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime);
			inst.extractEntities(inText);
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof EntityExtractorException);

	}

	@Test // StanfordNLP LanguageName null Validation
	public void testEntityExtractstanfordNLP_LanguageNameNullValidation() {
		String inText = "Pierre Vinken , 61 years old , will join the board as a nonexecutive director Nov. 29 . Mr . Vinken is chairman of Elsevier N.V. , the Dutch publishing group ."
				+ "Rudolph Agnew , 55 years old and former chairman of Consolidated Gold Fields PLC , was named a director of this British industrial conglomerate .";

		Throwable e = null;
		try {
			// create a instance for stanfordNLP
			EntityExtractor inst = new EntityExtractor(LibraryName.stanfordNLP, null,
					ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime);
			inst.extractEntities(inText);
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof EntityExtractorException);

	}

	@Test // StanfordNLP ClassifierClass null Validation
	public void testEntityExtractstanfordNLP_ClassifierClassNullValidation() {
		String inText = "Pierre Vinken , 61 years old , will join the board as a nonexecutive director Nov. 29 . Mr . Vinken is chairman of Elsevier N.V. , the Dutch publishing group ."
				+ "Rudolph Agnew , 55 years old and former chairman of Consolidated Gold Fields PLC , was named a director of this British industrial conglomerate .";

		Throwable e = null;
		try {
			// create a instance for stanfordNLP
			EntityExtractor inst = new EntityExtractor(LibraryName.stanfordNLP, LanguageName.english, null);
			inst.extractEntities(inText);
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof EntityExtractorException);

	}

	@Test // StanfordNLP ClassifierClass null Validation
	public void testEntityExtractstanfordNLP_OutputValidation() {
		String inText = "Pierre Vinken , 61 years old , will join the board as a nonexecutive director Nov. 29 . Mr . Vinken is chairman of Elsevier N.V. , the Dutch publishing group ."
				+ "Rudolph Agnew , 55 years old and former chairman of Consolidated Gold Fields PLC , was named a director of this British industrial conglomerate .";

		ArrayList<Entity> al = null;
		try {
			// create a instance for stanfordNLP
			EntityExtractor inst1 = new EntityExtractor(LibraryName.stanfordNLP, LanguageName.english,
					ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime);
			al = inst1.extractEntities(inText);
		} catch (Throwable ex) {

		}

		assertTrue(al.get(0) instanceof Entity);

	}

	@Test // StanfordNLP inputFilePath Empty Validation
	public void testEntityExtractstanfordNLPUsingInputFile_inputFilePathParamEmptyValidation() {

		Throwable e = null;
		try {
			// create a instance for stanfordNLP
			EntityExtractor inst = new EntityExtractor(LibraryName.stanfordNLP, LanguageName.english,
					ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime);
			inst.extractEntities("", "textsample.txt");
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof RuntimeException);

	}

	@Test // StanfordNLP inputFilePath Null Validation
	public void testEntityExtractstanfordNLPUsingInputFile_inputFilePathParamNullValidation() {

		Throwable e = null;
		try {
			// create a instance for stanfordNLP
			EntityExtractor inst = new EntityExtractor(LibraryName.stanfordNLP, LanguageName.english,
					ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime);
			inst.extractEntities(null, "textsample.txt");
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof RuntimeException);

	}

	@Test // StanfordNLP inputFile Empty Validation
	public void testEntityExtractstanfordNLPUsingInputFile_inputFileParamEmptyValidation() {

		Throwable e = null;
		try {
			// create a instance for stanfordNLP
			EntityExtractor inst = new EntityExtractor(LibraryName.stanfordNLP, LanguageName.english,
					ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime);
			inst.extractEntities("C:\\Works\\", "");
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof RuntimeException);

	}

	@Test // StanfordNLP inputFile Null Validation
	public void testEntityExtractstanfordNLPUsingInputFile_inputFileParamNullValidation() {

		Throwable e = null;
		try {
			// create a instance for stanfordNLP
			EntityExtractor inst = new EntityExtractor(LibraryName.stanfordNLP, LanguageName.english,
					ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime);
			inst.extractEntities("C:\\Works\\", null);
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof RuntimeException);
	}

	@Test // StanfordNLP LibraryName null Validation
	public void testEntityExtractstanfordNLPUsingInputFile_LibararyNameNullValidation() {
		Throwable e = null;
		try {
			// create a instance for stanfordNLP
			EntityExtractor inst = new EntityExtractor(null, LanguageName.english,
					ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime);
			inst.extractEntities("C:\\Works\\", "textsample.txt");
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof EntityExtractorException);

	}

	@Test // StanfordNLP LanguageName null Validation
	public void testEntityExtractstanfordNLPUsingInputFile_LanguageNameNullValidation() {

		Throwable e = null;
		try {
			// create a instance for stanfordNLP
			EntityExtractor inst = new EntityExtractor(LibraryName.stanfordNLP, null,
					ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime);
			inst.extractEntities("C:\\Works\\", "textsample.txt");
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof EntityExtractorException);

	}

	@Test // StanfordNLP ClassifierClass null Validation
	public void testEntityExtractstanfordNLPUsingInputFile_ClassifierClassNullValidation() {

		Throwable e = null;
		try {
			// create a instance for stanfordNLP
			EntityExtractor inst = new EntityExtractor(LibraryName.stanfordNLP, LanguageName.english, null);
			inst.extractEntities("C:\\Works\\", "textsample.txt");
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof EntityExtractorException);

	}

	@Test // openNLP- Validate the output is JSON - Class 7 classifier
	public void testEntityExtractstanfordNLPUsingInputFile_Class7OutputValidation() {

		ArrayList<Entity> al = null;
		try {
			// create a instance for stanfordNLP
			EntityExtractor inst1 = new EntityExtractor(LibraryName.stanfordNLP, LanguageName.english,
					ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime);
			al = inst1.extractEntities("C:\\Works\\", "textsample.txt");
		} catch (Throwable ex) {
		}

		assertTrue(al.get(0) instanceof Entity);

	}

	@Test // openNLP- Validate the output is JSON - Class 4 classifier
	public void testEntityExtractstanfordNLPUsingInputFile_Class4OutputValidation() {

		ArrayList<Entity> al = null;
		try {
			// create a instance for stanfordNLP
			EntityExtractor inst1 = new EntityExtractor(LibraryName.stanfordNLP, LanguageName.english,
					ClassifierClass.class4_LocationPersonOrganizationMisc);
			al = inst1.extractEntities("C:\\Works\\", "textsample.txt");
		} catch (Throwable ex) {
		}

		assertTrue(al.get(0) instanceof Entity);

	}

	@Test // openNLP- Validate the output is JSON - Class 3 classifier
	public void testEntityExtractstanfordNLPUsingInputFile_Class3OutputValidation() {

		ArrayList<Entity> al = null;
		try {
			// create a instance for stanfordNLP
			EntityExtractor inst1 = new EntityExtractor(LibraryName.stanfordNLP, LanguageName.english,
					ClassifierClass.class3_LocationPersonOrganization);
			al = inst1.extractEntities("C:\\Works\\", "textsample.txt");
		} catch (Throwable ex) {
		}

		assertTrue(al.get(0) instanceof Entity);
	}

	@Test // Custom exception handling - validation
	public void testEntityExtractopen_ExceptionValidation() {

		Throwable e = null;
		try {
			// create a instance for EntityExtactor
			EntityExtractor inst = null;
			if (inst instanceof EntityExtractor == false)
				throw new EntityExtractorException();
		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof EntityExtractorException);
	}

	@Test // Entity Class - Name null validation
	public void testEntity_EntityNamenullValidation() {

		Throwable e = null;
		try {
			// create a instance for Entity
			new Entity(null, "ORG");

		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof EntityExtractorException);
	}

	@Test // Entity Class - Name Empty validation
	public void testEntity_EntityNameEmptyValidation() {

		Throwable e = null;
		try {
			// create a instance for Entity
			new Entity("", "ORG");

		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof EntityExtractorException);
	}

	@Test // Entity Class - Name null validation
	public void testEntity_EntityNamenullValidation2() {

		Throwable e = null;
		try {
			// create a instance for Entity
			new Entity(null, "ORG",90.00);

		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof EntityExtractorException);
	}

	@Test // Entity Class - Name Empty validation
	public void testEntity_EntityNameEmptyValidation2() {

		Throwable e = null;
		try {
			// create a instance for Entity
			new Entity("", "ORG",90.00);

		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof EntityExtractorException);
	}
	
	@Test // Entity Class - Type null validation
	public void testEntity_EntityTypenullValidation() {

		Throwable e = null;
		try {
			// create a instance for Entity
			new Entity("ATT", null);

		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof EntityExtractorException);
	}

	@Test // Entity Class - Name Empty validation
	public void testEntity_EntityTypeEmptyValidation() {

		Throwable e = null;
		try {
			// create a instance for Entity
			new Entity("ATT", "");

		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof EntityExtractorException);
	}

	@Test // Entity Class - Type null validation
	public void testEntity_EntityTypenullValidation2() {

		Throwable e = null;
		try {
			// create a instance for Entity
			new Entity("ATT", null,90.00);

		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof EntityExtractorException);
	}

	@Test // Entity Class - Name Empty validation
	public void testEntity_EntityTypeEmptyValidation2() {

		Throwable e = null;
		try {
			// create a instance for Entity
			new Entity("ATT", "",90.00);

		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof EntityExtractorException);
	}
	

	@Test // Entity Class - ConfidenceInterval Empty validation
	public void testEntity_ConfidenceIntervalEmptyValidation() {

		Throwable e = null;
		try {
			// create a instance for Entity
			new Entity("ATT", "ORG", -1);

		} catch (Throwable ex) {
			e = ex;
		}

		assertTrue(e instanceof EntityExtractorException);
	}

	@Test // Entity Class - Constructor Empty validation
	public void testEntity_ConstructorValidation() {

		Entity e1=null;
		try {
			// create a instance for Entity
			e1= new Entity("ATT", "ORG", 89.90);

		} catch (Throwable ex) {

		}

		assertEquals("ATT", e1.getEntityName());
		assertEquals("ORG", e1.getEntityType());
		assertEquals("89.9", Double.toString(e1.getConfidenceInterval()));
	}

}
