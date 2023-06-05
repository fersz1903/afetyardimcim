package com.furkan.mblproje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.furkan.mblproje.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.buttonDisasters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.buttonDisasters) {
                    Intent intent = new Intent(MainActivity.this, afetler.class);
                    startActivity(intent);
                }
            }
        });

        binding.buttonWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.buttonWeather) {
                    Intent intent = new Intent(MainActivity.this, hava.class);
                    startActivity(intent);
                }
            }
        });

        binding.buttonContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.buttonContact) {
                    Intent intent = new Intent(MainActivity.this, kisiler.class);
                    startActivity(intent);
                }
            }
        });
    }
}

