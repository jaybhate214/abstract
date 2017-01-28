/**
 * Copyright (C) 2017, AT&T Inc. All rights reserved. Proprietary materials, property of AT&T. For internal use only,
 * not for disclosure to parties outside of AT&T or its affiliates.
 */
package com.att.cmlp.bot_demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.att.cmlp.bot_demo.model.IntentEntityExtractorRestAPI;
import com.att.cmlp.common.nlp.extractentities.ClassifierClass;
import com.att.cmlp.common.nlp.extractentities.Entity;
import com.att.cmlp.common.nlp.extractentities.EntityExtractor;
import com.att.cmlp.common.nlp.extractentities.EntityExtractorException;
import com.att.cmlp.common.nlp.extractentities.EntityExtractorUsingCustomNLP;
import com.att.cmlp.common.nlp.extractentities.LanguageName;
import com.att.cmlp.common.nlp.extractentities.LibraryName;

import opennlp.tools.util.InvalidFormatException;

/**
 * This is the Intent Entity Extractor REST Controller.
 * <p>
 * This controller will provide the rest interface for extraction of entity names and intent for the given
 * input text provided by End User.
 * </p>
 * 
 * @author jb307a
 * @since Jan 6, 2017
 * @version $Id$
 */
@RestController
public class IntentEntityExtractorController {

	/**
	 * This is the Entity micro-service for Entity Extraction.
	 * <p>
	 * The Entity microservice is able to extract entities from the parameters
	 * provided below. This service gets consumed over a POST request with following
	 * url encoded parameters. The endpoint for this service is /extract_entities.  
	 * </p>
	 * 
	 * @param text
	 *            Input text provided over POST request
	 * @param id
	 *            Chatbot developer id
	 */
    @RequestMapping(method =RequestMethod.POST , value="/extractEntities")
    public Map<String,Object> entityExtraction(@RequestParam(value="text") String text,@RequestParam(value="id") String id) {
		List<Object> st = new ArrayList<Object>();
		Map<String,Object> mainmap = new HashMap<String,Object>();
    	try{
    	EntityExtractor inst1 = new EntityExtractor(LibraryName.openNLP, LanguageName.english,
				ClassifierClass.class3_LocationPersonOrganization);
		ArrayList<Entity> alists = inst1.extractEntities(text);
		List<Entity> entities = alists;
		for (Entity item : entities) {
			Map<String,String> newmap = new HashMap<String,String>();
			newmap.put("entityName" , item.getEntityName());
			newmap.put("entityType" , item.getEntityType());
			newmap.put("confidenceInterval" , Double.toString(item.getConfidenceInterval()));
			System.out.println("Entity Name: " + item.getEntityName() + ", Entity Type: " + item.getEntityType()+ ", Confidence Interval: " + item.getConfidenceInterval());
			st.add(newmap);
		}
		mainmap.put("entities",st);

		// create a instance for stanfordNLP
		inst1 = new EntityExtractor(LibraryName.stanfordNLP, LanguageName.english,
				ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime);
		alists = inst1.extractEntities(text);
		entities = alists;
		for (Entity item : entities) {
			System.out.println("Entity Name:" + item.getEntityName() + ", Entity Type:" + item.getEntityType());
		}
		EntityExtractorUsingCustomNLP inst2 = new EntityExtractorUsingCustomNLP(LanguageName.english,
				ClassifierClass.class3_LocationPersonOrganization);
		JSONArray jr = inst2.entityExtract(text);
		System.out.println(jr.toString());

//		// create a instance for openNLP - File name as parameter
//		inst1 = new EntityExtractor(LibraryName.openNLP, LanguageName.english,
//				ClassifierClass.class4_LocationPersonOrganizationMisc);
//		alists = inst1.extractEntities("C:\\Works\\", "textsample.txt");
//		entities = alists;
//		for (Entity item : entities) {
//			System.out.println("Entity Name: " + item.getEntityName() + ", Entity Type: " + item.getEntityType()+ ", Confidence Interval: " + item.getConfidenceInterval());
//		}
//		// create a instance for stanfordNLP
//		inst1 = new EntityExtractor(LibraryName.stanfordNLP, LanguageName.english,
//				ClassifierClass.class4_LocationPersonOrganizationMisc);
//
//		alists = inst1.extractEntities("C:\\Works\\", "textsample.txt");
//		entities = alists;
//		for (Entity item : entities) {
//			System.out.println("Entity Name:" + item.getEntityName() + ", Entity Type:" + item.getEntityType());
//		}

    	
    	
    	
    	
    	
//    	EntityExtractionService inst1 = null;
//    	JSONArray jr_opennlp=null,jr_standfordnlp = null;
//		try {
//			inst1 = new EntityExtractionService(LibraryName.openNLP,LanguageName.english,ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime,text);
//			jr_opennlp= inst1.getEntities();
////			inst1 = new EntityExtractionService(LibraryName.standfordNLP,LanguageName.english,ClassifierClass.class7_LocationPersonOrganizationMoneyPercentDateTime,text);
////			jr_standfordnlp = inst1.getEntities();
//		} catch (InvalidFormatException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//    	
//    	return jr_opennlp.toString();
    	}
		catch (EntityExtractorException ex) {
			System.out.println(ex.getMessage());
		}

		catch (IOException ex) {
			System.out.println(ex.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
    	
    	return mainmap;

    }
    
	/**
	 * This is the Intent micro-service for Intent Extraction.
	 * <p>
	 * The Intent micro-service is able to extract intent from the parameters
	 * provided below. This service gets consumed over a POST request with following
	 * url encoded parameters. The endpoint for this service is /extract_intent.  
	 * </p>
	 * 
	 * @param text
	 *            Input text provided over POST request
	 * @param id
	 *            Chatbot developer id
	 * @param entities
	 * 			  JSON Array of extracted entites from the text 
	 */
    @RequestMapping(method =RequestMethod.POST , value="/extractIntent")
    public String intentExtraction(@RequestParam(value="text") String text,@RequestParam(value="id") String id,@RequestParam(value="entities") JSONArray entities) {
    	System.out.println("ENTITIES:"+entities.toString());
    	IntentExtractor extract=null;
    	try {
    		extract = new IntentExtractor();
			extract.SentenceDetect(text);
			extract.POSTag(text);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return "Intent Extraction called";
    }

	/**
	 * This is the Error endpoint.
	 * <p>
	 * The Error service gets consumed over a POST request with invalid
	 * input parameters or erroneous url path. The endpoint for this service is /error.  
	 * </p>
	 * 
	 */
    @RequestMapping(method =RequestMethod.POST , value="/error")
    public String showError(@RequestParam(value="text") String text,@RequestParam(value="id") String id) {
    	return "Error in Request Body or Invalid Endpoint";

    }
    
}
