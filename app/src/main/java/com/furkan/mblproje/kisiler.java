package com.furkan.mblproje;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.furkan.mblproje.databinding.ActivityMainBinding;
import com.furkan.mblproje.databinding.KisilerBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class kisiler extends AppCompatActivity {
    KisilerBinding binding;

    // wp işlemleri
    private static final int REQUEST_CODE = 1;
    private String[] phoneNumbers ; // Göndermek istediğiniz telefon numaralarını dizi olarak belirtin
    private String message;
    private int currentIndex = 0; // Şu anki indeks
    //
    SharedPreferences sharedPreferences;
    private ArrayList<Kisi> kisiListesi;
    private ArrayList<String> phoneNumbersList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.kisiler);

        binding = KisilerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        sharedPreferences = this.getSharedPreferences("com.furkan.mblproje",Context.MODE_PRIVATE);

        kisiListesi = new ArrayList<>();
        phoneNumbersList = new ArrayList<>();

        Map<String, ?> allEntries = sharedPreferences.getAll();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue().toString();
            kisiListesi.add(new Kisi(key,value));
            phoneNumbersList.add(value);
        }

        binding.BtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveContact(view);
            }
        });

        KisilerAdapter adapter = new KisilerAdapter(this,kisiListesi);
        binding.KisiListView.setAdapter(adapter);

        binding.KisiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Kisi secilenKisi = kisiListesi.get(i);

                AlertDialog.Builder builder = new AlertDialog.Builder(kisiler.this,R.style.MyAlertDialogStyle);
                builder.setTitle("Sil");
                builder.setMessage("Bu kişiyi silmek istediğinizden emin misiniz?");

                builder.setPositiveButton("Sil", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Silme işlemi
                        deleteContact(secilenKisi.getAd());
                    }
                });

                builder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // İptal işlemi
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        binding.BtnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(kisiler.this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    // Konum sağlayıcısını başlatma
                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                   /*boolean isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);  // gpsi zorunlu hale getirme
                   if (!isGpsEnabled) {
                       Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                       startActivity(intent);
                   }*/
                    LocationListener locationListener = new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            // Konum değiştiğinde yapılacak işlemler
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();

                            message = "Konumum: http://maps.google.com/maps?q=" + latitude + "," + longitude;
                            startNextWhatsAppIntent();

                            // Konum bilgisini almayı durdurma
                            locationManager.removeUpdates(this);
                        }
                    };

                    // Konum güncellemelerini başlatma
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                } else {
                    // Konum izni alınmamışsa kullanıcıdan izin isteme
                    ActivityCompat.requestPermissions(kisiler.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                }
            }
        });
    }
    private void startNextWhatsAppIntent() {
        if (currentIndex < phoneNumbersList.size()) {
            String phoneNumber = phoneNumbersList.get(currentIndex) ;

            Uri uri = Uri.parse("https://api.whatsapp.com/send?phone=+9" + phoneNumber + "&text=" + message);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.whatsapp"); // Sadece WhatsApp uygulamasını kullanmasını sağlar
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Yeni bir görev olarak WhatsApp'ı başlatır

            startActivityForResult(intent, REQUEST_CODE);

            currentIndex++;
        } else {
            // Tüm numaralar için Intent başlatıldı
            currentIndex = 0;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // İşlem başarıyla tamamlandı
                // Bir sonraki numara için yeni Intent'i başlat
                startNextWhatsAppIntent();
            } else if (resultCode == RESULT_CANCELED) {
                // Kullanıcı Intent'i iptal etti veya başarısız oldu
                // Bir sonraki numara için yeni Intent'i başlat
                startNextWhatsAppIntent();
            }
        }
    }

    public void saveContact(View view){
        String name = binding.EdtName.getText().toString();
        String number = binding.EdtNumber.getText().toString();

        sharedPreferences.edit().putString(name,number).apply();
        Toast.makeText(this,"Kişi Kaydedildi",Toast.LENGTH_SHORT).show();
    }

    public void deleteContact(String key){
        String name = key;
        sharedPreferences.edit().remove(name).apply();
        Toast.makeText(this,name+" silindi",Toast.LENGTH_SHORT).show();
    }
}
