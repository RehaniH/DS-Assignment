package sensor.model;

import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Sensor {
	public int getId() {
		return _id;
	}
	public void setId(int id) {
		this._id = id;
	}
	public int getCo2() {
		return co2_level;
	}
	public void setCo2(int co2) {
		this.co2_level = co2;
	}
	public int getSmoke() {
		return smoke_level;
	}
	public void setSmoke(int smoke) {
		this.smoke_level = smoke;
	}
	public int getRoom_number() {
		return room_number;
	}
	public void setRoom_number(int room_number) {
		this.room_number = room_number;
	}
	public int getFoor_number() {
		return foor_number;
	}
	public void setFoor_number(int foor_number) {
		this.foor_number = foor_number;
	}
	
	public Sensor(int id, int co2, int smoke, int room_number, int foor_number) {
		super();
		this._id = id;
		this.co2_level = co2;
		this.smoke_level = smoke;
		this.room_number = room_number;
		this.foor_number = foor_number;
	}
	public Sensor() {
		super();
		
	}


	private int _id;
	private int co2_level;
	private int smoke_level;
	private int room_number;
	private int foor_number;
	
	public void addSensor(Sensor sensor) {
		Response response;
		ObjectMapper mapper = new ObjectMapper();
		
		String json;
		try {
			json = mapper.writeValueAsString(sensor);
			RestAssured.baseURI="http://localhost:8080";
			RequestSpecification request=RestAssured.given();

			request.header("Content-Type","application/json");
			request.body(json);
			
			System.out.println(json);
			response = request.post("/v1/sensors");
			System.out.println(response.statusCode());
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
	};
	
	

		
	public void updateSensor(Sensor sensor) {
		
		
		for(int i=0;i>=-1;i++) {
		
			
				sensor.setCo2(getrandom(1,10));
				sensor.setSmoke(getrandom(1,10)); 
				
			
				Response response;
				Response response2=null;
				ObjectMapper mapper = new ObjectMapper();
				
				String json;
				try {
					json = mapper.writeValueAsString(sensor);
					RestAssured.baseURI="http://localhost:8080";
					RequestSpecification request=RestAssured.given();
		
					request.header("Content-Type","application/json");
					request.body(json);
					
					response2=RestAssured.given().when().get("/v1/sensors/active");
					JsonPath jsonPathEvaluator = response2.jsonPath();
					List values = jsonPathEvaluator.getList("_id");
					
					for (int k = 0; k < values.size(); k++) {
						
						System.out.println("updated sensor value :"+json);
						response = request.put("/v1/sensors/"+values.get(k)+"/co2/" + getrandom(1,10) + "/smoke/" + getrandom(1,10));
						System.out.println(response.statusCode());
						Thread.sleep(5000);
						
		
					}
					
					
					
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		
		}
		
	};
	
	private static int getrandom(int min,int max) {
		Random r=new Random();
		return r.nextInt((max-min)+1)+min;
		
	};
	
	
}


