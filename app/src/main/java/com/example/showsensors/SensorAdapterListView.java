package com.example.showsensors;

import android.content.Context;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SensorAdapterListView extends ArrayAdapter<MySensor> {
	Context mContext;
	int mResource;
	
	public SensorAdapterListView(Context context, int resource, ArrayList<MySensor> objects) {
		super(context, resource, objects);
		mContext = context;
		mResource = resource;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		LayoutInflater layoutInflater = LayoutInflater.from(mContext);
		convertView = layoutInflater.inflate(mResource, parent, false);
		
		TextView nome = convertView.findViewById(R.id.listTextViewNome);
		TextView tipo = convertView.findViewById(R.id.listTextViewTipo);
		TextView valor = convertView.findViewById(R.id.listTextViewValor);
		
		MySensor meusensor = getItem(position);
		nome.setText(meusensor.sensor.getName());
		tipo.setText(meusensor.sensor.getStringType());
		valor.setText(""+meusensor.valor);
		
		return convertView;
	}
}
