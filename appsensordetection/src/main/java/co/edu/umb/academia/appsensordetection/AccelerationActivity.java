package co.edu.umb.academia.appsensordetection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;

public class AccelerationActivity extends AppCompatActivity implements SensorEventListener {

    private TextView resultView;
    private SensorManager sensorManager;
    private Sensor acceleration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceleration);
        resultView = (TextView) findViewById(R.id.txtResultadoTemp);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        acceleration = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public void opBackMain(View view){
        Intent back = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(back);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        try {
            float valueX =  event.values[0];
            float valueY =  event.values[1];
            float valueZ =  event.values[2];
            String result = "";
            result+="\n" + "X: " + valueX + " m/s^2";
            result+="\n" + "Y: " + valueY + " m/s^2";
            result+="\n" + "Z: " + valueZ + " m/s^2";
            resultView.setText(result);
        }catch (Exception ex){
            StringWriter errors = new StringWriter();
            ex.printStackTrace(new PrintWriter(errors));
            Toast.makeText(null, errors.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        sensorManager.registerListener(this, acceleration, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}