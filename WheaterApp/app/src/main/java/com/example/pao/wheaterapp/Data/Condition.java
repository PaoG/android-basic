package com.example.pao.wheaterapp.Data;

import org.json.JSONObject;

/**
 * Created by Pao on 7/5/2015.
 */
public class Condition implements JSONPopulator {
    private int code;
    private int temperature;
    private String description;

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public int getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        code=data.optInt("code");   // To obtain the  code from the JSONObject
        temperature=data.optInt("temp"); // To obtain the  temp from the JSONObject
        description=data.optString("text"); // To obtain the  text from the JSONObject

    }
}
