package com.example.pao.wheaterapp.Data;

import org.json.JSONObject;

/**
 * Created by Pao on 7/5/2015.
 */
public class Item implements JSONPopulator{
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject data) {
    condition=new Condition();
        condition.populate(data.optJSONObject("condition"));  // To obtain the  temperature from the JSONObject
    }
}
