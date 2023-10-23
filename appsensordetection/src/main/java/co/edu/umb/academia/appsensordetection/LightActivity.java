package co.edu.umb.academia.appsensordetection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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

public class LightActivity extends AppCompatActivity implements SensorEventListener {

    private TextView resultLight;
    private SensorManager sensorManagerLight;
    private Sensor light;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
        resultLight = (TextView) findViewById(R.id.txtResultLight);
        sensorManagerLight = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        light = sensorManagerLight.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    public void opBackMainLight(View view){
        Intent back = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(back);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        try {
            float lx =  event.values[0];
            if( (int)lx > 0 && (int) lx <= 33 ){
                resultLight.setBackgroundColor(Color.YELLOW);
            }
            if( (int)lx > 33 && (int) lx <= 66 ){
                resultLight.setBackgroundColor(Color.BLUE);
            }
            if( (int)lx > 66 ){
                resultLight.setBackgroundColor(Color.RED);
            }
            resultLight.setText(""+lx+" lx");

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
        sensorManagerLight.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        sensorManagerLight.unregisterListener(this);
    }

}