package com.example.demo.weather.entity.template;


import com.example.demo.line.message.entity.Message;

public class Weather extends Message {

    private String template =
            "{" +
            "\"type\": \"flex\"," +
            "\"altText\": \"This is a Flex Message\"," +
            "\"contents\":"+
            "{\n" +
            "  \"type\": \"bubble\",\n" +
            "  \"header\": {\n" +
            "    \"type\": \"box\",\n" +
            "    \"layout\": \"vertical\",\n" +
            "    \"contents\": [\n" +
            "      {\n" +
            "        \"type\": \"text\",\n" +
            "        \"text\": \"今日天氣\",\n" +
            "        \"size\": \"xl\",\n" +
            "        \"align\": \"center\",\n" +
            "        \"contents\": []\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  \"hero\": {\n" +
            "    \"type\": \"image\",\n" +
            "    \"url\": \"https://i.imgur.com/XZuc9d2.jpg\",\n" +
            "    \"size\": \"full\",\n" +
            "    \"aspectRatio\": \"1.51:1\",\n" +
            "    \"aspectMode\": \"fit\"\n" +
            "  },\n" +
            "  \"body\": {\n" +
            "    \"type\": \"box\",\n" +
            "    \"layout\": \"vertical\",\n" +
            "    \"contents\": [\n" +
            "      {\n" +
            "        \"type\": \"text\",\n" +
            "        \"text\": \"表頭\",\n" +
            "        \"weight\": \"regular\",\n" +
            "        \"size\": \"xl\",\n" +
            "        \"align\": \"center\",\n" +
            "        \"contents\": []\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": \"text\",\n" +
            "        \"text\": \"內文\",\n" +
            "        \"contents\": []\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}}";

    public String getTemplate() {
        return template;
    }

    public String setTemplate(String title,String content){
        this.template.replace("表頭",title);
        this.template.replace("內文",content);
        return getTemplate();
    }
}
