package com.qa.vigupta.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



public class DataReader {
	
	
	public List<HashMap<String, String>> getJsonDatatoMap() throws IOException
	{
		// Read Json file and convert into String.
	   String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "\\src\\test\\java\\com\\qa\\vigupta\\data\\PurchaseOrder.json"), StandardCharsets.UTF_8);
	   	   
       // String to HashMap using jackson-databind api 
	   
	   ObjectMapper mapper = new ObjectMapper();
	   
	   List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
	   
	   // "List<HashMap<String, String>>" :- It's a List which contains HashMap elements with a key of String type and a value of String type
	   
	   return data;
	   
	}

}
