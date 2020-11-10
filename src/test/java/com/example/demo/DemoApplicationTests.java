package com.example.demo;

import com.example.demo.line.util.JsonParserUtil;
import com.example.demo.line.util.UUIDUtil;
import com.example.demo.mybatis.entity.Member;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private JsonParserUtil jsonParser;

	@Autowired
	private UUIDUtil uuidUtil;

	@Test
	void contextLoads() {
	}

	@Test
	public void testObjectMapper() throws JsonProcessingException {
		Member member = new Member();
		member.setMemberName("jack");

		String jsonString = "{\"memberEmail\":null,\"memberName\":\"jack\",\"memberGender\":null,\"memberBirthday\":null,\"memberAddress\":null,\"memberLineUserId\":null}";

		Member member1 = jsonParser.stringToJson(jsonString,Member.class);

		assertThat(member).isEqualToComparingOnlyGivenFields(member1);

	}

	@Test
	public void testUUID(){
		String uuid = uuidUtil.getRandomUUID();

		assertThat(uuid instanceof String);
	}
}
