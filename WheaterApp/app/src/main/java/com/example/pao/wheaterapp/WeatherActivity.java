package com.example.pao.wheaterapp;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pao.wheaterapp.Data.Channel;
import com.example.pao.wheaterapp.Data.Item;
import com.example.pao.wheaterapp.Service.WeatherService;
import com.example.pao.wheaterapp.Service.WeatherServiceCallback;


public class WeatherActivity extends ActionBarActivity implements WeatherServiceCallback {
    private ImageView weatherIconImageView;
    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;
    private WeatherService service;
    private ProgressDialog  dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        weatherIconImageView= (ImageView) findViewById(R.id.wheatherIconImageView);
        temperatureTextView=(TextView) findViewById(R.id.temperatureTexView);
        conditionTextView=(TextView) findViewById(R.id.conditionTextView);
        locationTextView=(TextView) findViewById(R.id.locationTextView);
        service= new WeatherService(this);
        dialog= new ProgressDialog(this);
        dialog.setMessage("Cargando..");
        dialog.show();
        service.refreshWeather("Sydney, Australia");
    }


    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();
        Item item = channel.getItem();
        int resourceId=getResources().getIdentifier("drawable/icon" + item.getCondition().getCode(), null, getPackageName());

       @SuppressWarnings("deprecation")
       Drawable weatherIconDrawble=getResources().getDrawable(resourceId);
       weatherIconImageView.setImageDrawable(weatherIconDrawble);
        temperatureTextView.setText(item.getCondition().getTemperature()+"\u00B0"+channel.getUnits().getTemperature());
        conditionTextView.setText(item.getCondition().getDescription());
        locationTextView.setText(service.getLocation());

    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(),Toast.LENGTH_LONG).show();

    }
}
