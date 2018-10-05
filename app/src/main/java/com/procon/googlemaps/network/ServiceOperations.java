package com.procon.googlemaps.network;

import com.procon.googlemaps.models.AddressResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceOperations {
    @GET("json")
    Call<AddressResponseModel> getAddressResponse(@Query("address") String address, @Query("sensor") boolean sensor,
                                                  @Query("key") String key);
}