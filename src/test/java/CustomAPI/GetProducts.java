package CustomAPI;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetProducts {
	String allProductsData;
	Response response;
	@BeforeClass
	public void getAllProducts() {
		//http://bmstestingportal.000webhostapp.com/customrestapi/product/read.php
		RestAssured.baseURI="http://bmstestingportal.000webhostapp.com";
		
		response = given().
			/*headers , api keys/token , payload, params*/
		when().
			/*HTTP Method and resource / endPOint*/
			get("customrestapi/product/read.php").
		then().
			/*Response */
			extract().response();
		
		allProductsData =  response.asPrettyString();
		
	}
	
	@Test
	public void printAllProdsName() {
		JsonPath jp = new JsonPath(allProductsData);
		//String count = jp.getMap("$").size();
		int count = jp.get("records.size"); //"189"
		
		 
		 
		for(int i=0;i<count;i++) {
			String pid = jp.get("records["+i+"].id").toString();
			String prod_name = jp.get("records["+i+"].name").toString();//records[0].name
			 System.out.println(pid + " - "+prod_name);
		}
	}
	
	
	@Test
	public void chkStatusCode() {
		int statusCode = response.getStatusCode();
		int expStatusCode =200 ;
		 
		Assert.assertEquals(statusCode, expStatusCode,"Not Matched!");
	}
}
