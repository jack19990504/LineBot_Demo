package com.example.demo;

import com.example.demo.airtable.entity.Log;
import com.example.demo.util.JsonParserUtil;
import com.example.demo.util.UUIDUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	private final UUIDUtil uuidUtil;
	private final JsonParserUtil jsonParserUtil;

	public DemoApplicationTests(UUIDUtil uuidUtil,JsonParserUtil jsonParserUtil){
		this.uuidUtil = uuidUtil;
		this.jsonParserUtil = jsonParserUtil;
	}

	@Test
	void contextLoads() {
	}


	@Test
	public void testUUID(){
		var uuid = uuidUtil.getRandomUUID();

		Assert.assertEquals(uuid.getClass(),String.class);
	}

	@Test
	public void testJsonParserUtil_toString(){

		String log_String = "{\"user\":\"1\",\"text\":\"2\",\"time\":\"3\",\"returnSucceed\":\"4\"}";
		Log log = new Log("1","2","3","4");

		Assert.assertEquals(log_String,jsonParserUtil.jsonToString(log));

	}

	@Test
	public void testJsonParserUtil_toOBJ(){

		String log_String = "{\"user\":\"1\",\"text\":\"2\",\"time\":\"3\",\"returnSucceed\":\"4\"}";
		Log log = new Log("1","2","3","4");

		Assert.assertEquals(log,jsonParserUtil.stringToJson(log_String,Log.class));

	}
}
