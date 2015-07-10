package com.example.pao.wheaterapp.Service;

import com.example.pao.wheaterapp.Data.Channel;

/**
 * Created by Pao on 7/5/2015.
 */
public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);
}
