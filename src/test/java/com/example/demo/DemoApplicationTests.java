package com.example.demo;

import com.example.demo.util.UUIDUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private UUIDUtil uuidUtil;

	@Test
	void contextLoads() {
	}


	@Test
	public void testUUID(){
		String uuid = uuidUtil.getRandomUUID();

		assertThat(uuid != null);
	}

}
