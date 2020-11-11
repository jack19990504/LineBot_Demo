package com.example.demo.weather.entity;

public class WeatherWrapper {

    private String success;
    private Result result;
    private Records records;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Records getRecords() {
        return records;
    }

    public void setRecords(Records record) {
        this.records = record;
    }
}
