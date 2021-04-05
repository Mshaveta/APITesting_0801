package CustomAPI;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AddNewProduct {
	String createProductRes;
	Response response;
	@Test
	public void getAllProducts() {
		//http://bmstestingportal.000webhostapp.com/customrestapi/product/read.php
		RestAssured.baseURI="http://bmstestingportal.000webhostapp.com/";
		
		response = given().
			/*headers , api keys/token , payload, params*/
				body("{\r\n" + 
						"    \"name\" : \"Test Product - 5th APril \",\r\n" + 
						"    \"price\" : \"25000\",\r\n" + 
						"    \"description\" : \"Test 5th April\",\r\n" + 
						"    \"category_id\" : 1,\r\n" + 
						"    \"created\" : \"\"\r\n" + 
						"}").
		when().
			/*HTTP Method and resource / endPOint*/
			post("customrestapi/product/create.php").
		then().
			/*Response */
			extract().response();
		
		createProductRes =  response.asPrettyString();
		System.out.println(createProductRes);
	}
	
	@Test
	public void chkStatusCode() {
		int statusCode = response.getStatusCode();
		int expStatusCode =201;
		 
		Assert.assertEquals(statusCode, expStatusCode,"Not Matched!");
	}
}
