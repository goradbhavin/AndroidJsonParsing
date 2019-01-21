package com.b.om.androidjsonparsingdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.b.om.androidjsonparsingdemo.firstdemo.FirstJsonDemo;
import com.b.om.androidjsonparsingdemo.seconddemo.SecondJsonDemo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void process(View view){

        if (view.getId() == R.id.btnFirstDemo){

            Intent intent = new Intent(this,FirstJsonDemo.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.btnSecondDemo){

            Intent intent = new Intent(this,SecondJsonDemo.class);
            startActivity(intent);
        }
    }


}
