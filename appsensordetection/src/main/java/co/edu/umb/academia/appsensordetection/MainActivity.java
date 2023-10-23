package co.edu.umb.academia.appsensordetection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void opAccelerationActivity(View view){
        Intent acc = new Intent(getApplicationContext(), AccelerationActivity.class);
        startActivity(acc);
    }

    public void opLightActivity(View view){
        Intent light = new Intent(getApplicationContext(), LightActivity.class);
        startActivity(light);
    }
}