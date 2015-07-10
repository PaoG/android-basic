package com.example.pao.wheaterapp.Data;

import org.json.JSONObject;

/**
 * Created by Pao on 7/5/2015.
 */
public class Units implements JSONPopulator{
    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
    temperature=data.optString("temperature");
    }
}
