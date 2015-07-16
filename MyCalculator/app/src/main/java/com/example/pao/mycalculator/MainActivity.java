package com.example.pao.mycalculator;

import android.app.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;


public class MainActivity extends Activity implements View.OnClickListener {
    private TextView txtResult;//textbox
    private float NumberBf;
    private String operation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResult = (TextView) findViewById(R.id.txt_result);

        int idList[] = {R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6,R.id.btn_7, R.id.btn_8, R.id.btn_9,
                R.id.btn_dot,R.id.btn_divition, R.id.btn_equal, R.id.btn_minus, R.id.btn_multiplication};
        for(int id: idList)
        {
            View v = (View) findViewById(id);
            v.setOnClickListener(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void mMath(String str)
    {

        NumberBf=Float.parseFloat(txtResult.getText().toString());
        operation = str;
        txtResult.setText("0");
    }
    public void getKeyboard(String str)
    {
        String SrcCurrent= txtResult.getText().toString();
        if (SrcCurrent.equals("0"))
            SrcCurrent="";
        SrcCurrent+=str;
        txtResult.setText(SrcCurrent);
    }
    public void mResult()
    {
        float NumAf=Float.parseFloat(txtResult.getText().toString());
        float result=0;
        if(operation.equals("+"))
        {
        result= NumAf+NumberBf;
        }
        if(operation.equals("-"))
        {
            result= NumAf-NumberBf;
        }
        if(operation.equals("*"))
        {
            result= NumAf*NumberBf;
        }
        if(operation.equals("/"))
        {
            result= NumAf/NumberBf;
        }

        txtResult.setText(String.valueOf(result));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    switch (v.getId())
    {
        case R.id.btn_plus:
            mMath("+");
            break;
        case R.id.btn_minus:
            mMath("-");
            break;
        case R.id.btn_multiplication:
            mMath("*");
            break;
        case R.id.btn_divition:
            mMath("/");
            break;
        case R.id.btn_equal:
            mResult();
        case R.id.btn_c:
            txtResult.setText("0");
            NumberBf=0;
            operation="";
            break;
        default:
            String numb=((Button)v).getText().toString();
            getKeyboard(numb);
    }

    }
}
