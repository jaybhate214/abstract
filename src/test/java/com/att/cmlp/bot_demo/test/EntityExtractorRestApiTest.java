//package com.att.cmlp.bot_demo.test;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class EntityExtractorRestApiTest {
//	/**Autowired MockMvc object
//	 */
//	@Autowired
//	private MockMvc mvc;
//	
//	
//	@Test
//	public void test() throws Exception{
//		String jsonBody="United States";
//		mvc.perform(MockMvcRequestBuilder.post("/extractEntities"))
//		.accept(org.springframework.http.MediaType.APPLICATION_JSON)
//		.content(jsonBody))
//		.andExpect(status().isOk())
//		.andExpect(MockMvcResultMatchers.content().contentType(org.springframework.http.MediaType.TEXT_PLAIN))
//		.andExpect(MockMvcResultMatchers.content().string("{\"entities\":"))
//	}
//	
//
//}
