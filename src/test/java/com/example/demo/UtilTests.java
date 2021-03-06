package com.example.demo;

import com.example.demo.airtable.entity.Log;
import com.example.demo.util.JsonParserUtil;
import com.example.demo.util.UUIDUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilTests {

    @Autowired
    private UUIDUtil uuidUtil;
    @Autowired
    private JsonParserUtil jsonParserUtil;

    @Test
    public void testUUID(){
        var uuid = uuidUtil.getRandomUUID();

        Assertions.assertEquals(uuid.getClass(),String.class);
    }

    @Test
    public void testJsonParserUtil_toString(){

        String log_String = "{\"user\":\"1\",\"text\":\"2\",\"time\":\"3\",\"returnSucceed\":\"4\"}";
        Log log = new Log("1","2","3","4");

        Assertions.assertEquals(log_String,jsonParserUtil.jsonToString(log));

    }

    @Test
    public void testJsonParserUtil_toOBJ(){

        String log_String = "{\"user\":\"1\",\"text\":\"2\",\"time\":\"3\",\"returnSucceed\":\"4\"}";
        Log log = new Log("1","2","3","4");

        Assertions.assertEquals(log,jsonParserUtil.stringToJson(log_String,Log.class));

    }
}
