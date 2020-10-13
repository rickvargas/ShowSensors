package com.example.showsensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
	ListView listView;
	SensorManager mSensorManager;
	SensorAdapterListView sensorAdapteListView;
	
	final ArrayList<MySensor> SENSORES = new ArrayList<MySensor>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		listView = findViewById(R.id.listView);
		sensorAdapteListView = new SensorAdapterListView(
						getApplicationContext(),
						R.layout.list_sensores,
						SENSORES
				);
		listView.setAdapter(sensorAdapteListView);
		mSensorManager = (SensorManager) getSystemService(getApplicationContext().SENSOR_SERVICE);
	}
	
	protected void onStart() {
		super.onStart();
		listarSensores();
	}
	
	public void listarSensores(){
		SENSORES.clear();
		List<Sensor> listSensores = mSensorManager.getSensorList(Sensor.TYPE_ALL);
		for(Sensor s: listSensores){
			mSensorManager.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
			MySensor meusensor = new MySensor(s, 0);
			SENSORES.add(meusensor);
		}
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		for(MySensor s: SENSORES){
			if(s.sensor == event.sensor){
				s.valor = event.values[0];
			}
		}
		sensorAdapteListView.notifyDataSetChanged();
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
}
