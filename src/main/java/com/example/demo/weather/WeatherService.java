package com.example.demo.weather;

import com.example.demo.line.util.JsonParserUtil;
import com.example.demo.line.util.entity.HttpResponse;
import com.example.demo.weather.entity.ReturnMessage;
import com.example.demo.weather.entity.Time;
import com.example.demo.weather.entity.WeatherElement;
import com.example.demo.weather.entity.WeatherWrapper;
import com.example.demo.weather.Service.SendWeatherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherService {

    private final SendWeatherUtil sendWeatherUtil;
    private final JsonParserUtil jsonParserUtil;

    @Autowired
    public WeatherService(SendWeatherUtil sendWeatherUtil, JsonParserUtil jsonParserUtil){
        this.sendWeatherUtil = sendWeatherUtil;
        this.jsonParserUtil = jsonParserUtil;
    }

    public ReturnMessage getMessage(){

        ReturnMessage returnMessage = new ReturnMessage();

        HttpResponse httpResponse = sendWeatherUtil.getResponse();
        WeatherWrapper weatherWrapper = jsonParserUtil.stringToJson(httpResponse.getResponseBody(),WeatherWrapper.class);
        returnMessage.setCity(weatherWrapper.getRecords().getLocations().get(0).getLocationsName() + ":" +
                weatherWrapper.getRecords().getLocations().get(0).getLocation().get(0).getLocationName());
        StringBuilder stringBuilder ;
        List<WeatherElement> weatherElementList = weatherWrapper.getRecords().getLocations().get(0).getLocation().get(0).getWeatherElement();

        for(WeatherElement weatherElement : weatherElementList){
            if(weatherElement.getElementName().equals("PoP12h")) {
                stringBuilder = new StringBuilder();
                for (Time time : weatherElement.getTime()) {
                    stringBuilder.append(time.getStartTime().substring(5)).append(" - ").append(time.getEndTime().substring(5));
                    stringBuilder.append(" 的下雨機率為 ：").append(time.getElementValue().get(0).getValue()).append(" ").append("%\\n");
                }
                returnMessage.setRaining(stringBuilder.toString());

            }
            if(weatherElement.getElementName().equals("T")){
                stringBuilder = new StringBuilder();
                for (Time time : weatherElement.getTime()){
                    stringBuilder.append(time.getDataTime()).append(" 氣溫為 : ")
                            .append(time.getElementValue().get(0).getValue()+time.getElementValue().get(0).getMeasures()).append("\\n");
                }
                returnMessage.setTemperature(stringBuilder.toString());
            }
        }
        System.out.println(jsonParserUtil.jsonToString(returnMessage));
        return returnMessage;
    }

    public String getLineMessage(){
        return getMessage().toString();
    }
}
