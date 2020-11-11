package com.example.demo.weather.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Time {

    private String startTime;

    private String endTime;

    private String dataTime;

    private List<ElementValue> elementValue;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<ElementValue> getElementValue() {
        return elementValue;
    }

    public void setElementValue(List<ElementValue> elementValue) {
        this.elementValue = elementValue;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }
}
