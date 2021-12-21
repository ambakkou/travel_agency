package fr.lernejo.travelsite;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface PredictionEngineClient {
    @GET("api/temperature")
    @Headers("Accept:application/json")
    Call<Temperatures> listTemperatures(@Query("country") String country);
}

