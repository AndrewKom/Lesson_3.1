package com.mirea.komkovaa.ls3_kr;

import static android.content.Context.SENSOR_SERVICE;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

public  class accelerometer extends Fragment implements SensorEventListener {
        private SensorManager sensorManager;
        private Sensor accelerometer;
        private TextView azimuthTextView;
        private TextView pitchTextView;
        private TextView rollTextView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_accelerometer, container, false);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        azimuthTextView = view.findViewById(R.id.textViewAzimuth);
        pitchTextView = view.findViewById(R.id.textViewPitch);
        rollTextView = view.findViewById(R.id.textViewRoll);
        return view;
    }


        @Override
        public void onPause() {
            super.onPause();
            sensorManager.unregisterListener(this);
        }

        @Override
        public void onResume() {
            super.onResume();
            if (accelerometer != null) {
                sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            }
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                float valueAzimuth = event.values[0];
                float valuePitch = event.values[1];
                float valueRoll = event.values[2];


                azimuthTextView.setText("Accelerometer 1: " + valueAzimuth);
                pitchTextView.setText("Accelerometer 2: " + valuePitch);
                rollTextView.setText("Accelerometer 3: " + valueRoll);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }
