package com.furkan.mblproje;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

import java.net.URLEncoder;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.furkan.mblproje.databinding.ActivityMainBinding;
import com.furkan.mblproje.databinding.HavaBinding;

public class hava extends AppCompatActivity {
    HavaBinding binding;

    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.hava);

        binding = HavaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.buttonGetWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = binding.editTextCity.getText().toString();
                if (!city.isEmpty()) {
                    getWeatherDataByCity(city);
                }
            }
        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                getWeatherDataByCoordinates(latitude, longitude);
            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }
                @Override
                public void onProviderDisabled(String provider) {
                }
            };

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 100, locationListener);
            }
        }

        private void getWeatherDataByCity(String city) {
            // Ağ isteği arka planda gerçekleştirilmek için thread oluşturuldu
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String temperatureJson = getTemperatureFromAPIByCity(city);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updateTemperature(temperatureJson);
                        }
                    });
                }
            }).start();
        }

        private void getWeatherDataByCoordinates(double latitude, double longitude) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String temperatureJson = getTemperatureFromAPIByCoordinates(latitude, longitude);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updateTemperature(temperatureJson);
                        }
                    });
                }
            }).start();
        }

        private String getTemperatureFromAPIByCity(String city) {
            String temperatureJson = "";
            try {
                String apiKey = "6b38a2cd92680c16f7af7015222fd161";
                String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + URLEncoder.encode(city, "UTF-8") + "&appid=" + apiKey;

                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                // Veri okuma işlemleri
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                temperatureJson = response.toString();
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return temperatureJson;
        }

        private String getTemperatureFromAPIByCoordinates(double latitude, double longitude) {
            String temperatureJson = "";

            try {
                String apiKey = "6b38a2cd92680c16f7af7015222fd161";
                String urlString = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid=" + apiKey;

                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                // Veri okuma işlemleri
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                temperatureJson = response.toString();

                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return temperatureJson;
        }

        private void updateTemperature(String temperatureJson) {
            try {
                JSONObject jsonObject = new JSONObject(temperatureJson);
                JSONArray weatherArray = jsonObject.getJSONArray("weather");

                //hava durumu öğesi
                JSONObject weatherObject = weatherArray.getJSONObject(0);

                // Hava durumu ikonu
                String iconCode = weatherObject.getString("icon");

                // Hava durumu ikonuna göre resim ayarlama
                int weatherImageResource = getWeatherImageResource(iconCode);
                binding.weatherImageView.setImageResource(weatherImageResource);

                // Sıcaklık değerini alıp ve temperatureTextView içine yazdırılması
                JSONObject mainObject = jsonObject.getJSONObject("main");
                double temperatureKelvin = mainObject.getDouble("temp");
                double temperatureCelsius = temperatureKelvin - 273.15;
                String temperatureText = String.format("%.1f °C", temperatureCelsius);
                binding.temperatureTextView.setText(temperatureText);
                if (temperatureJson.isEmpty()) {
                    // Hava durumu verisi alınamadıysa kullanıcıya hata mesajı gösterilir
                    Toast.makeText(hava.this, "Hava durumu verisi alınamadı", Toast.LENGTH_SHORT).show();
                    return;
                }

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(hava.this, "JSON ayrıştırma hatası: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

        private int getWeatherImageResource(String iconCode) {
            int weatherImageResource;
            switch (iconCode) {
                case "01d":
                    weatherImageResource = R.drawable.clear_sky_day;
                    break;
                case "01n":
                    weatherImageResource = R.drawable.clear_sky_night;
                    break;
                case "02d":
                case "03d":
                    weatherImageResource = R.drawable.few_clouds;
                    break;
                case "02n":
                case "03n":
                    weatherImageResource = R.drawable.few_clouds_night;
                    break;
                case "04d":
                case "04n":
                    weatherImageResource = R.drawable.overcast_clouds;
                    break;
                case "09d":
                case "09n":
                case "10d":
                case "10n":
                    weatherImageResource = R.drawable.rainy;
                    break;
                case "11d":
                case "11n":
                    weatherImageResource = R.drawable.thunderstorm;
                    break;
                case "13d":
                case "13n":
                    weatherImageResource = R.drawable.snow;
                    break;
                case "50d":
                case "50n":
                    weatherImageResource = R.drawable.mist;
                    break;
                default:
                    weatherImageResource = R.drawable.unknown;
                    break;
            }
            return weatherImageResource;
        }
    }