package ReqRes;

 
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GetUsersList {
	@Test
	public void getUsers() {
		RestAssured.baseURI="https://reqres.in/";
		
		Response resp = given().
			queryParam("page",2).
		when().
			get("api/users/").
		then().
			extract().response();
		
		//to convert JSOn response tinto String
		String resStr = resp.asString();
		String resStrPretty = resp.asPrettyString();
		
		System.out.println(resStrPretty);//io.restassured.internal.RestAssuredResponseImpl@1b58ff9e
		
	}

	
	@Test
	public void getSingleUser() {
		
	}
	
}
