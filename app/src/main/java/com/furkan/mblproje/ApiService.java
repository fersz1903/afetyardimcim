package com.furkan.mblproje;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("notifications") // API'nin bildirimler endpoint'i
    Call<List<Notification>> getNotifications();
}
